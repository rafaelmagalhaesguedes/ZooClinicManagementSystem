package com.zooclinic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

  public Appointment() { }

  public Appointment(String date, String time, String reason) {
    this.date = date;
    this.time = time;
    this.reason = reason;
  }

  public Appointment(Long id, String date, String time, String reason) {
    this.id = id;
    this.date = date;
    this.time = time;
    this.reason = reason;
  }

  public Appointment(Long id, String date, String time, String reason, Animal animal) {
    this.id = id;
    this.date = date;
    this.time = time;
    this.reason = reason;
    this.animal = animal;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Animal getAnimal() {
    return animal;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }
}
