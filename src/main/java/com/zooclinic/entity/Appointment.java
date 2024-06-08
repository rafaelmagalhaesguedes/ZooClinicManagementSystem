package com.zooclinic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The type Appointment.
 */
@Entity
@Table(name = "appointments")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String date;
  private String time;
  private String reason;

  @ManyToOne
  @JoinColumn(name = "animal_id")
  private Animal animal;

  /**
   * Instantiates a new Appointment.
   */
  public Appointment() { }

  /**
   * Instantiates a new Appointment.
   *
   * @param date   the date
   * @param time   the time
   * @param reason the reason
   */
  public Appointment(String date, String time, String reason) {
    this.date = date;
    this.time = time;
    this.reason = reason;
  }

  /**
   * Instantiates a new Appointment.
   *
   * @param id     the id
   * @param date   the date
   * @param time   the time
   * @param reason the reason
   */
  public Appointment(Long id, String date, String time, String reason) {
    this.id = id;
    this.date = date;
    this.time = time;
    this.reason = reason;
  }

  /**
   * Instantiates a new Appointment.
   *
   * @param id     the id
   * @param date   the date
   * @param time   the time
   * @param reason the reason
   * @param animal the animal
   */
  public Appointment(Long id, String date, String time, String reason, Animal animal) {
    this.id = id;
    this.date = date;
    this.time = time;
    this.reason = reason;
    this.animal = animal;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets date.
   *
   * @return the date
   */
  public String getDate() {
    return date;
  }

  /**
   * Sets date.
   *
   * @param date the date
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * Gets time.
   *
   * @return the time
   */
  public String getTime() {
    return time;
  }

  /**
   * Sets time.
   *
   * @param time the time
   */
  public void setTime(String time) {
    this.time = time;
  }

  /**
   * Gets reason.
   *
   * @return the reason
   */
  public String getReason() {
    return reason;
  }

  /**
   * Sets reason.
   *
   * @param reason the reason
   */
  public void setReason(String reason) {
    this.reason = reason;
  }

  /**
   * Gets animal.
   *
   * @return the animal
   */
  public Animal getAnimal() {
    return animal;
  }

  /**
   * Sets animal.
   *
   * @param animal the animal
   */
  public void setAnimal(Animal animal) {
    this.animal = animal;
  }
}
