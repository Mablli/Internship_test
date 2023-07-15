package com.example.inTest.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс запроса на создание пользователя
 */
@Data
@Accessors(chain = true)
public class CreateUser {

    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String phone;
    private String password;
}