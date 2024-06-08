package com.zooclinic.exception;

/**
 * The type Appointment already exists exception.
 */
public class AppointmentAlreadyExistsException extends NotFoundException {

  /**
   * Instantiates a new Appointment already exists exception.
   */
  public AppointmentAlreadyExistsException() {
    super("Appointment already exists.");
  }
}