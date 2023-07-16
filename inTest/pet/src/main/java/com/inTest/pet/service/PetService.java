package com.inTest.pet.service;

import com.inTest.pet.dto.CreatePet;
import com.inTest.pet.entity.PetEntity;
import com.inTest.pet.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Сервис для питомца
 */
@Service
@Data
public class PetService {
    private final PetRepository repository;

    /**
     * Создание нового питомца
     * @param pet - данные питомца
     * @return - результат регистрации
     */
    public ResponseEntity<?> createPet(CreatePet pet) {
        if (checkPet(pet).getBody().toString().contains("!")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(checkPet(pet).getBody());
        }

        PetEntity newPet = new PetEntity()
                .setName(pet.getName())
                .setType(pet.getType())
                .setOwnerId(pet.getOwnerId());

        repository.save(newPet);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Питомец создан!");
    }

    /**
     * Изменение данных питомца
     * @param id - id питомца
     * @param pet - данные питомца
     * @return - результат изменения
     */
    @Transactional
    public ResponseEntity<?> editPet(Long id, CreatePet pet) {
        if (checkPet(pet).getBody().toString().contains("!")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(checkPet(pet).getBody());
        }

        repository.updateById(pet.getName(), pet.getType(), pet.getOwnerId(), id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Данные питомца были изменены");
    }

    /**
     * Основные проверки данных питомца
     * @param pet - проверяемый питомец
     * @return - результат проверок
     */
    private ResponseEntity<?> checkPet(CreatePet pet) {
        // Проверка на пустоту полей
        if (pet.getName().equals("") || pet.getType().equals(""))
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ни одно из полей не должно быть пустым!");
        // Проверка на пробелы в полях
        if (pet.getName().contains(" ") || pet.getType().contains(" "))
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ни одно из полей не должно содержать пробелы!");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Успешно");
    }
}