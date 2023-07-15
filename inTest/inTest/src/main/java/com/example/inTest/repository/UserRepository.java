package com.example.inTest.repository;

import com.example.inTest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для пользователя
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

        Optional<UserEntity> findByPhone(String phone);
        Optional<UserEntity> findByEmail(String email);

    /**
     * Метод обработки запроса на изменение ФИО пользователя
     * @param name - имя
     * @param surname - фамилия
     * @param middleName - отчество
     * @param id - id пользователя
     */
    @Modifying
    @Query("UPDATE UserEntity user SET user.name = ?1, user.surname = ?2, user.middleName = ?3 WHERE user.id = ?4")
    void updateById(String name, String surname, String middleName, Long id);
}
