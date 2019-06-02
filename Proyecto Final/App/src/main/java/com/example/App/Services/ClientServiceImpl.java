package com.example.App.Services;

import com.example.App.model.Client;
import com.example.App.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired private ClientRepository repository;

    @Override
    public Optional<Client> create(Client client)
    {
        if(!repository.findById(client.getIdClient()).isPresent())
            return Optional.ofNullable(repository.save(client));

        return Optional.empty();
    }

    @Override
    public ResponseEntity<Client> update(Client client)
    {
        if(repository.findById(client.getIdClient()).isPresent())
        {
            repository.save(client);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Client> delete(Client client)
    {
        if (repository.findById(client.getIdClient()).isPresent()) {
            repository.delete(client);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<Client> findById(Integer id)
    {
        return repository.findById(id);
    }

    @Override
    public List<Client> findAllByDni(String dni)
    {
        return repository.findAllByDni(dni);
    }

    @Override
    public List<Client> findAll()
    {
        return repository.findAll();
    }

    @Override
    public List<Client> findAllByNameContaining(String name)
    {
        return repository.findAllByNameContaining(name);
    }

    @Override
    public List<Client> findAllByNameContainingAndDni(String name, String dni) {
        return repository.findAllByNameContainingAndDni(name, dni);
    }
}
