package com.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Entity
@Data
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, length = 25)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 20)
    private String email;

    @Column(name = "mobile", nullable = false, unique = true, length = 20)
    private String mobile;

}