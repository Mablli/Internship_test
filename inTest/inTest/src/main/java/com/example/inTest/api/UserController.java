package com.example.inTest.api;

import com.example.inTest.dto.CreateUser;
import com.example.inTest.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с пользователем
 */
@RestController
@Data
@RequestMapping(value = "api/user/")
public class UserController {

    private final UserService service;

    /**
     * Создание пользователя
     * @param user - данные пользователя
     * @return - результат регистрации
     */
    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUser user) {
        return service.createNewUser(user);
    }

    /**
     * Поиск пользователя по id
     * @param id - id пользователя
     * @return - искомый пользователь
     */
    @GetMapping("get-user-by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    /**
     * Удаление пользователя
     * @param id - id пользователя
     * @return - результат удаления
     */
    @GetMapping("delete-user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return service.deleteUserById(id);
    }

    /**
     * Изменение ФИО пользователя
     * @param id - id пользователя
     * @param user - данные пользователя
     * @return - результат изменения
     */
    @PutMapping("update-user-full-name/{id}")
    public ResponseEntity<?> updateUserFullName(@PathVariable Long id, @RequestBody CreateUser user) {
        return service.updateUserFullName(id, user);
    }

    /**
     * Получение всех пользователей
     * @return - информация о всех пользователях
     */
    @GetMapping("get-all-users")
    public ResponseEntity<?> getAllUsers() {
        return service.getAllUsers();
    }
}
