package com.inTest.pet.repository;

import com.inTest.pet.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для питомца
 */
@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    /**
     * Метод обработки запроса на изменение данных питомца
     * @param name - имя
     * @param type - тип (кот, собака, птица и т.д.)
     * @param ownerId - id пользователя - владельца
     * @param id - id питомца
     */
    @Modifying
    @Query("UPDATE PetEntity pet SET pet.name = ?1, pet.type = ?2, pet.ownerId = ?3 WHERE pet.id = ?4")
    void updateById(String name, String type, Long ownerId, Long id);
}
