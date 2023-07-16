package com.inTest.pet.api;

import com.inTest.pet.dto.CreatePet;
import com.inTest.pet.service.PetService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с питомцем
 */
@RestController
@Data
@RequestMapping(value = "api/pet/")
public class PetController {

    private final PetService service;

    /**
     * Создание питомца
     * @param pet - данные питомца
     * @return - результат регистрации
     */
    @PostMapping("create-pet")
    public ResponseEntity<?> createPet(@RequestBody CreatePet pet) {
        return service.createPet(pet);
    }

    /**
     * Изменение данных питомца
     * @param id - id питомца
     * @param pet - данные питомца
     * @return - результат изменения
     */
    @PutMapping("edit-pet/{id}")
    public ResponseEntity<?> editPet(@PathVariable Long id, @RequestBody CreatePet pet) {
        return service.editPet(id, pet);
    }
}
