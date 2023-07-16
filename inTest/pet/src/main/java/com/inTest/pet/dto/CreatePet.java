package com.inTest.pet.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс запроса на создание питомца
 */
@Data
@Accessors(chain = true)
public class CreatePet {
    private String name;
    private String type;
    private Long ownerId;
}
