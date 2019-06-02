package com.example.App.repository;

import com.example.App.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findAllByDni(String dni);

    List<Client> findAllByNameContaining(String name);

    List<Client> findAllByNameContainingAndDni(String name, String dni);

}
