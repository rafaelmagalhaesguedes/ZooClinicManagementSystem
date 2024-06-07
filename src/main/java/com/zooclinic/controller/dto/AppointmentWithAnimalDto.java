package com.zooclinic.controller.dto;

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
    Long animalId = appointment.getAnimal() != null ? appointment.getAnimal().getId() : null;
    String animalName = appointment.getAnimal() != null ? appointment.getAnimal().getName() : null;
    String breed = appointment.getAnimal() != null ? appointment.getAnimal().getBreed() : null;
    String specie = appointment.getAnimal() != null ? appointment.getAnimal().getSpecie() : null;
    Integer age = appointment.getAnimal() != null ? appointment.getAnimal().getAge() : null;

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
