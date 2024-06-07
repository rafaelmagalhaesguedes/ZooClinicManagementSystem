package com.zooclinic.controller.dto;

import com.zooclinic.entity.Animal;

public record AnimalCreationDto(
    String name,
    String specie,
    String breed,
    Integer age,
    String sex,
    Double weight
) {
  public Animal toEntity() {
    return new Animal(name, specie, breed, age, sex, weight);
  }
}
