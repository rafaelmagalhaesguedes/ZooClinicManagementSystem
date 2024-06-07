package com.zooclinic.controller.dto;

import com.zooclinic.entity.Appointment;

public record AppointmentUpdateDto(
    Long id,
    String date,
    String time,
    String reason
) {
  public Appointment toEntity() {
    return new Appointment(id, date, time, reason);
  }
}
