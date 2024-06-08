package com.zooclinic.service.exception;

/**
 * The type Appointment not found exception.
 */
public class AppointmentNotFoundException extends NotFoundException {

  /**
   * Instantiates a new Appointment not found exception.
   */
  public AppointmentNotFoundException() {
    super("Appointment not found. Try again");
  }
}
