package com.example.App.Unitarios;


import com.example.App.Services.ClientServiceImpl;
import com.example.App.model.Client;
import com.example.App.repository.ClientRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks private ClientServiceImpl service;

    @Mock private ClientRepository repository;

    @Test
    public void testCreateClientWithCorrectInputArgsAndReturnsNotEmptyOptionalOfClient()
    {
        // Given
        Client inputClient = new Client(1, "juan", "3344NN");
        Client outputClient = new Client(1, "juan", "3344NN");

        // WHEN

        Mockito.when(repository.findById(inputClient.getIdClient())).thenReturn(Optional.empty());
        Mockito.when(repository.save(inputClient)).thenReturn(outputClient);


        Optional<Client> finalClient = service.create(inputClient);

        //THEN

        Assert.assertNotNull(finalClient.get());

    }

    @Test
    public void testFindAllByNameContainingAndReturnsNotEmptyListOfClientThatItsNameContainsThanName()
    {
        // GIVEN

        String inputName = "luc";
        String dni = "22333DDAA";
        List<Client> clientList = new ArrayList<>();

        for(int i=0 ; i<5 ; ++i)
            clientList.add(new Client(i, (inputName+ " "+i), dni));

        // WHEN

        Mockito.when(repository.findAllByNameContaining(inputName)).thenReturn(clientList);
        List<Client> finalClientList = service.findAllByNameContaining(inputName);

        Assert.assertNotNull(finalClientList);
    }
}
