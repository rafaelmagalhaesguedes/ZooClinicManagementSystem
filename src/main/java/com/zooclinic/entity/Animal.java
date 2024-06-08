package com.zooclinic.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 * The type Animal.
 */
@Entity
public class Animal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String specie;
  private String breed;
  private Integer age;
  private String sex;
  private Double weight;

  @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
  private List<Appointment> appointments;

  /**
   * Instantiates a new Animal.
   */
  public Animal() { }

  /**
   * Instantiates a new Animal.
   *
   * @param name   the name
   * @param specie the specie
   * @param breed  the breed
   * @param age    the age
   * @param sex    the sex
   * @param weight the weight
   */
  public Animal(String name, String specie, String breed, Integer age, String sex, Double weight) {
    this.name = name;
    this.specie = specie;
    this.breed = breed;
    this.age = age;
    this.sex = sex;
    this.weight = weight;
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
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets specie.
   *
   * @return the specie
   */
  public String getSpecie() {
    return specie;
  }

  /**
   * Sets specie.
   *
   * @param specie the specie
   */
  public void setSpecie(String specie) {
    this.specie = specie;
  }

  /**
   * Gets breed.
   *
   * @return the breed
   */
  public String getBreed() {
    return breed;
  }

  /**
   * Sets breed.
   *
   * @param breed the breed
   */
  public void setBreed(String breed) {
    this.breed = breed;
  }

  /**
   * Gets age.
   *
   * @return the age
   */
  public Integer getAge() {
    return age;
  }

  /**
   * Sets age.
   *
   * @param age the age
   */
  public void setAge(Integer age) {
    this.age = age;
  }

  /**
   * Gets sex.
   *
   * @return the sex
   */
  public String getSex() {
    return sex;
  }

  /**
   * Sets sex.
   *
   * @param sex the sex
   */
  public void setSex(String sex) {
    this.sex = sex;
  }

  /**
   * Gets weight.
   *
   * @return the weight
   */
  public Double getWeight() {
    return weight;
  }

  /**
   * Sets weight.
   *
   * @param weight the weight
   */
  public void setWeight(Double weight) {
    this.weight = weight;
  }

  /**
   * Gets appointments.
   *
   * @return the appointments
   */
  public List<Appointment> getAppointments() {
    return appointments;
  }

  /**
   * Sets appointments.
   *
   * @param appointments the appointments
   */
  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }
}
