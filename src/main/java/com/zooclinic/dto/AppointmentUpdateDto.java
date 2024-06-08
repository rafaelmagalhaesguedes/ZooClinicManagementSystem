package com.zooclinic.dto;

import com.zooclinic.entity.Appointment;

/**
 * The type Appointment update dto.
 */
public record AppointmentUpdateDto(
    Long id,
    String date,
    String time,
    String reason
) {

  /**
   * To entity appointment.
   *
   * @return the appointment
   */
  public Appointment toEntity() {
    return new Appointment(id, date, time, reason);
  }
}
