package com.example.App.Components;

import com.example.App.DTO.ClientDto;
import com.example.App.model.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MapperServiceClient implements MapperService<ClientDto, Client>{

    @Override
    public Optional<Client> mapDtoToDao(ClientDto clientDto)
    {
        if(Optional.ofNullable(clientDto).isPresent())
        {
            Client client = new Client(clientDto.getId(), clientDto.getName(), clientDto.getDni());

            return Optional.of(client);
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<ClientDto> mapDaoToDto(Client client)
    {
        if(Optional.ofNullable(client).isPresent())
            return Optional.of(new ClientDto(client.getIdClient(), client.getDni(), client.getName()));

        return Optional.empty();
    }

    @Override
    public List<Client> mapListDtoToDao(List<ClientDto> list)
    {
        List<Client> clients = new ArrayList<>();
        for(ClientDto clientDto : list)
            if(mapDtoToDao(clientDto).isPresent())
                clients.add(mapDtoToDao(clientDto).get());

        return clients;
    }

    @Override
    public List<ClientDto> mapListDaoToDto(List<Client> list)
    {
        List<ClientDto> clients = new ArrayList<>();
        for(Client client : list)
            if(mapDaoToDto(client).isPresent())
                clients.add(mapDaoToDto(client).get());

        return clients;
    }
}
