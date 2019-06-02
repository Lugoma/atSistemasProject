package com.example.App.Unitarios;

import com.example.App.Services.CarServiceImpl;
import com.example.App.Services.ClientServiceImpl;
import com.example.App.Services.RentalServiceImpl;
import com.example.App.model.Car;
import com.example.App.model.Client;
import com.example.App.model.Rate;
import com.example.App.model.Rental;
import com.example.App.repository.RentalRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)

public class RentalServiceTest {

    @InjectMocks private RentalServiceImpl service;

    @Mock RentalRepository repository;

    @Mock ClientServiceImpl clientService;

    @Mock CarServiceImpl carService;


    @Test
    public void testCreateRentalWorksAndReturnAnNonEmptyOptional()
    {
        // GIVEN

        Car car = new Car(1, "asdfas", LocalDate.now());
        Client client = new Client(1, "juan","SSDD33");
        Rental inputRental = new Rental(1,LocalDate.now(), LocalDate.now().plusMonths(2), 20.0, client, car);
        Rate internalRate = new Rate(1, LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(3), 10.0);

        car.getRates().add(internalRate);

        // WHEN

        Mockito.when(repository.findById(inputRental.getIdRent())).thenReturn(Optional.empty());
        Mockito.when(clientService.findById(inputRental.getClient().getIdClient())).thenReturn(Optional.of(client));
        Mockito.when(carService.findById(inputRental.getCar().getIdCar())).thenReturn(Optional.of(car));
        Mockito.when(repository.save(inputRental)).thenReturn(inputRental);


        Optional<Rental> finalRental = service.create(inputRental);

        // THEN
        Assert.assertNotNull(finalRental);

    }


}
