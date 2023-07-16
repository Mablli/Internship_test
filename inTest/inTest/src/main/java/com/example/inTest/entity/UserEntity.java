package com.example.inTest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Entity для пользователя
 */
@Entity
@Table(schema = "mab", name = "user")
@Data
@Accessors(chain = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

}
