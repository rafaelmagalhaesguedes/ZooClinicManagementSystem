package com.zooclinic.controller;

import com.zooclinic.controller.dto.AnimalCreationDto;
import com.zooclinic.controller.dto.AnimalDto;
import com.zooclinic.controller.dto.AppointmentCreationDto;
import com.zooclinic.controller.dto.AppointmentDto;
import com.zooclinic.controller.dto.AppointmentUpdateDto;
import com.zooclinic.entity.Animal;
import com.zooclinic.entity.Appointment;
import com.zooclinic.service.AnimalService;
import com.zooclinic.service.exception.AnimalNotFoundException;
import com.zooclinic.service.exception.AppointmentNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Animal controller.
 */
@RestController
@RequestMapping(value = "/animal")
public class AnimalController {

  private final AnimalService animalService;

  /**
   * Instantiates a new Animal controller.
   *
   * @param animalService the animal service
   */
  @Autowired
  public AnimalController(AnimalService animalService) {
    this.animalService = animalService;
  }

  /**
   * Gets animal by id.
   *
   * @param id the id
   * @return the animal by id
   * @throws AnimalNotFoundException the animal not found exception
   */
  @GetMapping("/{id}")
  public AnimalDto getAnimalById(@PathVariable Long id) throws AnimalNotFoundException {
    return AnimalDto.fromEntity(
        animalService.getAnimalById(id)
    );
  }

  /**
   * Create animal animal dto.
   *
   * @param animalCreationDto the animal creation dto
   * @return the animal dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AnimalDto createAnimal(@RequestBody AnimalCreationDto animalCreationDto) {
    return AnimalDto.fromEntity(
        animalService.createAnimal(animalCreationDto.toEntity())
    );
  }

  /**
   * Gets all animals.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the all animals
   */
  @GetMapping
  public List<AnimalDto> getAllAnimals(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {
    List<Animal> paginatedAnimals = animalService.findAllAnimals(pageNumber, pageSize);

    return paginatedAnimals.stream()
        .map(AnimalDto::fromEntity)
        .toList();
  }

  /**
   * Update animal animal dto.
   *
   * @param id                the id
   * @param animalCreationDto the animal creation dto
   * @return the animal dto
   * @throws AnimalNotFoundException the animal not found exception
   */
  @PutMapping("/{id}")
  public AnimalDto updateAnimal(
      @PathVariable Long id,
      @RequestBody AnimalCreationDto animalCreationDto
  ) throws AnimalNotFoundException {
    return AnimalDto.fromEntity(
        animalService.updateAnimal(id, animalCreationDto.toEntity())
    );
  }

  /**
   * Delete animal animal dto.
   *
   * @param id the id
   * @return the animal dto
   * @throws AnimalNotFoundException the animal not found exception
   */
  @DeleteMapping("/{id}")
  public AnimalDto deleteAnimal(@PathVariable Long id) throws AnimalNotFoundException {
    return AnimalDto.fromEntity(
        animalService.deleteAnimal(id)
    );
  }

  /**
   * Gets all appointments.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the all appointments
   */
  @GetMapping("/appointment")
  public Map<String, Object> getAllAppointments(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {
    List<Appointment> appointments = animalService.getAllAppointments(pageNumber, pageSize);

    List<Map<String, Object>> appointmentsJson = new ArrayList<>();
    for (Appointment appointment : appointments) {
      Map<String, Object> appointmentJson = new LinkedHashMap<>();
      appointmentJson.put("Appointment", Map.of(
          "id", appointment.getId(),
          "date", appointment.getDate(),
          "time", appointment.getTime(),
          "reason", appointment.getReason()
      ));

      Animal animal = appointment.getAnimal();
      appointmentJson.put("Animal", Map.of(
          "animalId", animal.getId(),
          "animalName", animal.getName(),
          "breed", animal.getBreed(),
          "specie", animal.getSpecie(),
          "age", animal.getAge()
      ));

      appointmentsJson.add(appointmentJson);
    }

    Map<String, Object> result = new HashMap<>();
    result.put("Appointments", appointmentsJson);
    return result;
  }

  /**
   * Create appointment by animal id appointment dto.
   *
   * @param animalId               the animal id
   * @param appointmentCreationDto the appointment creation dto
   * @return the appointment dto
   * @throws AnimalNotFoundException the animal not found exception
   */
  @PostMapping("/{animalId}/appointment")
  @ResponseStatus(HttpStatus.CREATED)
  public AppointmentDto createAppointmentByAnimalId(
      @PathVariable Long animalId,
      @RequestBody AppointmentCreationDto appointmentCreationDto
  ) throws AnimalNotFoundException {
    return AppointmentDto.fromEntity(
        animalService.createAppointment(animalId, appointmentCreationDto.toEntity())
    );
  }

  /**
   * Gets appointment by animal id.
   *
   * @param animalId the animal id
   * @return the appointment by animal id
   * @throws AnimalNotFoundException      the animal not found exception
   * @throws AppointmentNotFoundException the appointment not found exception
   */
  @GetMapping("/{animalId}/appointment")
  public List<AppointmentDto> getAppointmentByAnimalId(@PathVariable Long animalId)
      throws AnimalNotFoundException, AppointmentNotFoundException {
    List<Appointment> appointments = animalService.getAppointmentsByAnimalId(animalId);

    if (appointments.isEmpty()) {
      throw new AppointmentNotFoundException();
    }

    return appointments.stream()
        .map(AppointmentDto::fromEntity)
        .toList();
  }

  /**
   * Update appointment by animal id appointment dto.
   *
   * @param animalId             the animal id
   * @param appointmentUpdateDto the appointment update dto
   * @return the appointment dto
   * @throws AnimalNotFoundException      the animal not found exception
   * @throws AppointmentNotFoundException the appointment not found exception
   */
  @PutMapping("/{animalId}/appointment")
  public AppointmentDto updateAppointmentByAnimalId(
      @PathVariable Long animalId,
      @RequestBody AppointmentUpdateDto appointmentUpdateDto)
      throws AnimalNotFoundException, AppointmentNotFoundException {

    return AppointmentDto.fromEntity(
        animalService.updateAppointmentByAnimalId(animalId, appointmentUpdateDto.toEntity())
    );
  }
}
