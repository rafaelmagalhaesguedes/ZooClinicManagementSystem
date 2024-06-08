package com.zooclinic.dto;

import com.zooclinic.entity.Animal;

/**
 * The type Animal dto.
 */
public record AnimalDto(
    Long id,
    String name,
    String specie,
    String breed,
    Integer age,
    String sex,
    Double weight
) {

  /**
   * From entity animal dto.
   *
   * @param animal the animal
   * @return the animal dto
   */
  public static AnimalDto fromEntity(Animal animal) {
    return new AnimalDto(
        animal.getId(),
        animal.getName(),
        animal.getSpecie(),
        animal.getBreed(),
        animal.getAge(),
        animal.getSex(),
        animal.getWeight()
    );
  }
}
