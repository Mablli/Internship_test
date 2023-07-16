package com.inTest.pet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Entity для питомца
 */
@Entity
@Table(schema = "mab", name = "pet")
@Data
@Accessors(chain = true)
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

   @Column(name = "owner_id")
    private Long ownerId;
}
