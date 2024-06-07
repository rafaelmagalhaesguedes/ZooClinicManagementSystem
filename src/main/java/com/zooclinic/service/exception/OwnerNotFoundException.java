package com.zooclinic.service.exception;

public class OwnerNotFoundException extends NotFoundException {
  public OwnerNotFoundException() {
    super("Owner not found. Try again.");
  }
}
