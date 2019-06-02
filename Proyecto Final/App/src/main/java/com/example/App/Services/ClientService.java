package com.example.App.Services;

import com.example.App.model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {


    /**
     * Create a new Client
     * @param client
     * @return
     */
    Optional<Client> create(Client client);

    /**
     * Modify an existing Client
     * @param client
     * @return
     */
    ResponseEntity<Client> update(Client client);

    /**
     * Delete an existing Client
     * @param client
     * @return
     */
    ResponseEntity<Client> delete(Client client);

    /**
     * Find a Client by Id
     * @param id
     * @return
     */
    Optional<Client> findById(Integer id);

    /**
     * Find a list of client by dni. It is assumed that dni is not an unique field
     * @param dni
     * @return
     */
    List<Client> findAllByDni(String dni);

    /**
     * Find all Clients
     * @return
     */
    List<Client> findAll();

    /**
     * Find a list of client by name containing param
     * @param name
     * @return
     */
    List<Client> findAllByNameContaining(String name);

    /**
     * Find a list fo client by name containing and dni
     * @param name
     * @param dni
     * @return
     */
    List<Client> findAllByNameContainingAndDni(String name, String dni);

}
