package com.zooclinic.service;

import com.zooclinic.entity.Animal;
import com.zooclinic.entity.Appointment;
import com.zooclinic.repository.AnimalRepository;
import com.zooclinic.repository.AppointmentRepository;
import com.zooclinic.service.exception.AppointmentNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final AnimalRepository animalRepository;

  @Autowired
  public AppointmentService(
      AppointmentRepository appointmentRepository,
      AnimalRepository animalRepository) {
    this.appointmentRepository = appointmentRepository;
    this.animalRepository = animalRepository;
  }

  public Appointment getAppointmentById(Long id) throws AppointmentNotFoundException {
    return appointmentRepository.findById(id)
        .orElseThrow(AppointmentNotFoundException::new);
  }

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

  public Appointment deleteAppointment(Long id) throws AppointmentNotFoundException {
    Appointment appointment = getAppointmentById(id);

    appointmentRepository.deleteById(id);

    return appointment;
  }
}
