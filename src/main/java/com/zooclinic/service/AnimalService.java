package com.zooclinic.service;

import com.zooclinic.entity.Animal;
import com.zooclinic.repository.AppointmentRepository;
import com.zooclinic.service.exception.AnimalNotFoundException;
import com.zooclinic.entity.Appointment;
import com.zooclinic.repository.AnimalRepository;
import com.zooclinic.service.exception.AppointmentNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

  private final AnimalRepository animalRepository;
  private final AppointmentRepository appointmentRepository;

  @Autowired
  public AnimalService(
      AnimalRepository animalRepository,
      AppointmentRepository appointmentRepository) {
    this.animalRepository = animalRepository;
    this.appointmentRepository = appointmentRepository;
  }

  public Animal getAnimalById(Long id) throws AnimalNotFoundException {
    return animalRepository.findById(id)
        .orElseThrow(AnimalNotFoundException::new);
  }

  public List<Animal> findAllAnimals(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Animal> page = animalRepository.findAll(pageable);

    return page.toList();
  }

  public Animal createAnimal(Animal animal) {
    return animalRepository.save(animal);
  }

  public Animal updateAnimal(Long id, Animal animal) throws AnimalNotFoundException {
    Animal animalFromDb = getAnimalById(id);

    animalFromDb.setName(animal.getName());
    animalFromDb.setAge(animal.getAge());
    animalFromDb.setBreed(animal.getBreed());
    animalFromDb.setSpecie(animal.getSpecie());
    animalFromDb.setSex(animal.getSex());
    animalFromDb.setWeight(animal.getWeight());

    return animalRepository.save(animalFromDb);
  }

  public Animal deleteAnimal(Long id) throws AnimalNotFoundException {
    Animal animal = getAnimalById(id);

    animalRepository.deleteById(id);

    return animal;
  }

  public Appointment createAppointment(Long animalId, Appointment appointment)
      throws AnimalNotFoundException {
    Animal animal = getAnimalById(animalId);

    appointment.setAnimal(animal);

    return appointmentRepository.save(appointment);
  }

  public List<Appointment> getAppointmentsByAnimalId(Long animalId)
      throws AnimalNotFoundException, AppointmentNotFoundException {
    Animal animal = getAnimalById(animalId);

    List<Appointment> appointments = animal.getAppointments();

    if (appointments == null || appointments.isEmpty()) {
      throw new AppointmentNotFoundException();
    }

    return appointments;
  }

  public Appointment updateAppointmentByAnimalId(Long animalId, Appointment appointment)
      throws AnimalNotFoundException, AppointmentNotFoundException {

    Animal animal = getAnimalById(animalId);

    if (animal == null) {
      throw new AnimalNotFoundException();
    }

    List<Appointment> appointments = animal.getAppointments();

    if (appointments == null || appointments.isEmpty()) {
      throw new AppointmentNotFoundException();
    }

    for (Appointment existingAppointment : appointments) {
      if (existingAppointment.getId().equals(appointment.getId())) {
        // Verifica se os campos da consulta são válidos antes de atualizar
        if (appointment.getDate() != null) {
          existingAppointment.setDate(appointment.getDate());
        }
        if (appointment.getTime() != null) {
          existingAppointment.setTime(appointment.getTime());
        }
        if (appointment.getReason() != null && !appointment.getReason().isEmpty()) {
          existingAppointment.setReason(appointment.getReason());
        }

        // Salva as alterações na consulta no banco de dados
        appointmentRepository.save(existingAppointment);
        return existingAppointment;
      }
    }

    throw new AppointmentNotFoundException();
  }
}
