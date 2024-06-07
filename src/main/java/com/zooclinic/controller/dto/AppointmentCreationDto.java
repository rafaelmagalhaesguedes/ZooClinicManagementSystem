package com.zooclinic.controller.dto;

import com.zooclinic.entity.Appointment;
import java.time.LocalDateTime;

public record AppointmentCreationDto(
    String date,
    String time,
    String reason
) {
  public Appointment toEntity() {
    return new Appointment(date, time, reason);
  }
}
