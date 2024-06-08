package com.zooclinic.exception;

/**
 * The type Animal not found exception.
 */
public class AnimalNotFoundException extends NotFoundException {

  /**
   * Instantiates a new Animal not found exception.
   */
  public AnimalNotFoundException() {
    super("Animal not found. Try again.");
  }
}