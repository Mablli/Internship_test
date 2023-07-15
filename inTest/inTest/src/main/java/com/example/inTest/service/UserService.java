package com.example.inTest.service;

import com.example.inTest.dto.CreateUser;
import com.example.inTest.entity.UserEntity;
import com.example.inTest.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Сервис для пользователя
 */
@Service
@Data
public class UserService {

    private final UserRepository repository;

    /**
     * Создание нового пользователя
     * @param user - данные пользователя
     * @return - результат регистрации
     */
    public ResponseEntity<?> createNewUser(CreateUser user) {
        if (check(user).getBody().toString().contains("!")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(check(user).getBody());
        }

        UserEntity newUser = new UserEntity()
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setMiddleName(user.getMiddleName())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone())
                .setPassword(user.getPassword());

        repository.save(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Вы зарегистрированы!");
    }

    /**
     * Получение информации о пользователе по id
     * @param id - id пользователя
     * @return - информация и пользователе
     */
    public ResponseEntity<?> getUserById(Long id) {
        if (!repository.findById(id).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Пользователя с таким id не существует!");
        }
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(repository.findById(id));

    }

    /**
     * Удаление пользователя по id
     * @param id - id пользователя
     * @return - результат удаления
     */
    public ResponseEntity<?> deleteUserById(Long id) {
        if (!repository.findById(id).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Пользователя с таким id не существует!");
        }

        repository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Пользователь был успешно удален!");
    }

    /**
     * Изменение ФИО пользователя
     * @param id - id пользователя
     * @param user - данные пользователя
     * @return - результат
     */
    @Transactional
    public ResponseEntity<?> updateUserFullName(Long id, CreateUser user) {
        if (check(user).getBody().toString().contains("!")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(check(user).getBody());
        }

        repository.updateById(user.getName(), user.getSurname(), user.getMiddleName(), id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Имя, фамилия и отчество пользователя были изменены.");
    }

    /**
     * Получение всех пользователей
     * @return - информация о каждом из пользователей
     */
    public ResponseEntity<?> getAllUsers() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("К сожалению, в приложении не зарегистрировано ни одного пользователя.");
        }
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(repository.findAll());
    }

    /**
     * Основные проверки пользователя
     * @param user - проверяемые данные пользователя
     * @return - результат проверок
     */
    private ResponseEntity<?> check(CreateUser user) {

        // Уникальность почты и телефона
        if (repository.findByPhone(user.getPhone()).isPresent() || repository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Человек с такими данными уже существует. Пожалуйста, проверьте свою контактную информацию!");
        }

        // Пустота полей
        if (user.getName().equals("") || user.getSurname().equals("") || user.getMiddleName().equals("") || user.getEmail().equals("")
                || user.getPhone().equals("") || user.getPassword().equals("")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ни одно из полей не должно быть пустым!");
        }

        // Пробелы в полях
        if (user.getName().contains(" ") || user.getSurname().contains(" ") || user.getMiddleName().contains(" ")
                || user.getEmail().contains(" ") || user.getPhone().contains(" ") || user.getPassword().contains(" ")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ни одно из полей не должно содержать пробелы!");
        }
        // Корректность почты
        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Неверная эл. почта!");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Успешно");
    }
}
