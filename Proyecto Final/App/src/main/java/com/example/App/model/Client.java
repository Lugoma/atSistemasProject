package com.example.App.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idClient;

    private String name;

    private String dni;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Rental> rents = new HashSet<>();

    public Client(Integer id, String name, String dni)
    {
        this.idClient = id;
        this.name = name;
        this.dni = dni;
    }

}
