package com.nilesh.PatientManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; //autogenerate ID need not manually generate

    @NotNull
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String address;

    @NotNull
    private LocalDate dob;

    @NotNull
    private LocalDate regestrationDate;


}
