package com.example.brandstoftracker.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
}
