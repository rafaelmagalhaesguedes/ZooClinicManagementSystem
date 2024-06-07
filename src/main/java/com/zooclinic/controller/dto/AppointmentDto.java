package com.zooclinic.controller.dto;

import com.zooclinic.entity.Appointment;
import java.time.LocalDateTime;

/**
 * The type Appointment dto.
 */
public record AppointmentDto(
    Long id,
    String date,
    String time,
    String reason
) {

  /**
   * From entity appointment dto.
   *
   * @param appointment the appointment
   * @return the appointment dto
   */
  public static AppointmentDto fromEntity(Appointment appointment) {
    return new AppointmentDto(
        appointment.getId(),
        appointment.getDate(),
        appointment.getTime(),
        appointment.getReason()
    );
  }
}
