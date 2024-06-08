package com.zooclinic.dto;

import com.zooclinic.entity.Animal;
import com.zooclinic.entity.Appointment;

public record AppointmentWithAnimalDto(
    Long id,
    String date,
    String time,
    String reason,
    Long animalId,
    String animalName,
    String breed,
    String specie,
    Integer age
) {
  public static AppointmentWithAnimalDto fromEntity(Appointment appointment) {
    Animal animal = appointment.getAnimal();
    Long animalId = animal != null ? animal.getId() : null;
    String animalName = animal != null ? animal.getName() : null;
    String breed = animal != null ? animal.getBreed() : null;
    String specie = animal != null ? animal.getSpecie() : null;
    Integer age = animal != null ? animal.getAge() : null;

    return new AppointmentWithAnimalDto(
        appointment.getId(),
        appointment.getDate(),
        appointment.getTime(),
        appointment.getReason(),
        animalId,
        animalName,
        breed,
        specie,
        age
    );
  }
}
