package com.zooclinic.service.exception;

public class AppointmentAlreadyExistsException extends NotFoundException {
  public AppointmentAlreadyExistsException() {
    super("Appointment already exists.");
  }
}