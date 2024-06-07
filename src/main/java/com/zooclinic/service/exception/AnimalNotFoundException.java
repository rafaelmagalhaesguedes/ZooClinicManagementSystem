package com.zooclinic.service.exception;

public class AnimalNotFoundException extends NotFoundException {
  public AnimalNotFoundException() {
    super("Animal not found. Try again.");
  }
}