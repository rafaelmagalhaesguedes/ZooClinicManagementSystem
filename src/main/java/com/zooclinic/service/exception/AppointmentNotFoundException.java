package com.zooclinic.service.exception;

public class AppointmentNotFoundException extends NotFoundException {
  public AppointmentNotFoundException() {
    super("Appointment not found. Try again");
  }
}
