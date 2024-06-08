package com.zooclinic.dto;

import com.zooclinic.entity.Animal;

/**
 * The type Animal creation dto.
 */
public record AnimalCreationDto(
    String name,
    String specie,
    String breed,
    Integer age,
    String sex,
    Double weight
) {

  /**
   * To entity animal.
   *
   * @return the animal
   */
  public Animal toEntity() {
    return new Animal(name, specie, breed, age, sex, weight);
  }
}
