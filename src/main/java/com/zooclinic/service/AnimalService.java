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

/**
 * The type Animal service.
 */
@Service
public class AnimalService {

  private final AnimalRepository animalRepository;
  private final AppointmentRepository appointmentRepository;

  /**
   * Instantiates a new Animal service.
   *
   * @param animalRepository      the animal repository
   * @param appointmentRepository the appointment repository
   */
  @Autowired
  public AnimalService(
      AnimalRepository animalRepository,
      AppointmentRepository appointmentRepository
  ) {
    this.animalRepository = animalRepository;
    this.appointmentRepository = appointmentRepository;
  }

  /**
   * Gets animal by id.
   *
   * @param id the id
   * @return the animal by id
   * @throws AnimalNotFoundException the animal not found exception
   */
  public Animal getAnimalById(Long id) throws AnimalNotFoundException {
    return animalRepository.findById(id)
        .orElseThrow(AnimalNotFoundException::new);
  }

  /**
   * Find all animals list.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the list
   */
  public List<Animal> findAllAnimals(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Animal> page = animalRepository.findAll(pageable);

    return page.toList();
  }

  /**
   * Create animal.
   *
   * @param animal the animal
   * @return the animal
   */
  public Animal createAnimal(Animal animal) {
    return animalRepository.save(animal);
  }

  /**
   * Update animal.
   *
   * @param id     the id
   * @param animal the animal
   * @return the animal
   * @throws AnimalNotFoundException the animal not found exception
   */
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

  /**
   * Delete animal.
   *
   * @param id the id
   * @return the animal
   * @throws AnimalNotFoundException the animal not found exception
   */
  public Animal deleteAnimal(Long id) throws AnimalNotFoundException {
    Animal animal = getAnimalById(id);

    animalRepository.deleteById(id);

    return animal;
  }

  /**
   * Gets appointments by animal id.
   *
   * @param animalId the animal id
   * @return the appointments by animal id
   * @throws AnimalNotFoundException      the animal not found exception
   * @throws AppointmentNotFoundException the appointment not found exception
   */
  public List<Appointment> getAppointmentsByAnimalId(Long animalId)
      throws AnimalNotFoundException, AppointmentNotFoundException {
    Animal animal = getAnimalById(animalId);

    List<Appointment> appointments = animal.getAppointments();

    if (appointments == null || appointments.isEmpty()) {
      throw new AppointmentNotFoundException();
    }

    return appointments;
  }

  /**
   * Gets appointment by id.
   *
   * @param id the id
   * @return the appointment by id
   * @throws AppointmentNotFoundException the appointment not found exception
   */
  public Appointment getAppointmentById(Long id) throws AppointmentNotFoundException {
    return appointmentRepository.findById(id)
        .orElseThrow(AppointmentNotFoundException::new);
  }

  /**
   * Gets all appointments.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the all appointments
   */
  public List<Appointment> getAllAppointments(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Appointment> appointments = appointmentRepository.findAll(pageable);
    for (Appointment appointment : appointments) {
      Animal animal = animalRepository
          .findById(appointment.getAnimal().getId()).orElse(null);
      appointment.setAnimal(animal);
    }
    return appointments.toList();
  }

  /**
   * Create appointment.
   *
   * @param animalId    the animal id
   * @param appointment the appointment
   * @return the appointment
   * @throws AnimalNotFoundException the animal not found exception
   */
  public Appointment createAppointment(Long animalId, Appointment appointment)
      throws AnimalNotFoundException {
    Animal animal = getAnimalById(animalId);

    appointment.setAnimal(animal);

    return appointmentRepository.save(appointment);
  }

  /**
   * Update appointment by animal id appointment.
   *
   * @param animalId    the animal id
   * @param appointment the appointment
   * @return the appointment
   * @throws AnimalNotFoundException      the animal not found exception
   * @throws AppointmentNotFoundException the appointment not found exception
   */
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
        if (appointment.getDate() != null) {
          existingAppointment.setDate(appointment.getDate());
        }
        if (appointment.getTime() != null) {
          existingAppointment.setTime(appointment.getTime());
        }
        if (appointment.getReason() != null && !appointment.getReason().isEmpty()) {
          existingAppointment.setReason(appointment.getReason());
        }

        appointmentRepository.save(existingAppointment);
        return existingAppointment;
      }
    }

    throw new AppointmentNotFoundException();
  }

  /**
   * Delete appointment appointment.
   *
   * @param id the id
   * @return the appointment
   * @throws AppointmentNotFoundException the appointment not found exception
   */
  public Appointment deleteAppointment(Long id) throws AppointmentNotFoundException {
    Appointment appointment = getAppointmentById(id);

    appointmentRepository.deleteById(id);

    return appointment;
  }
}
