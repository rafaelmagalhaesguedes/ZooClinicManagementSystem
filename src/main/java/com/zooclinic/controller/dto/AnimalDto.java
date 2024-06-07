package com.zooclinic.controller.dto;

import com.zooclinic.entity.Animal;

public record AnimalDto(
    Long id,
    String name,
    String specie,
    String breed,
    Integer age,
    String sex,
    Double weight
) {
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
