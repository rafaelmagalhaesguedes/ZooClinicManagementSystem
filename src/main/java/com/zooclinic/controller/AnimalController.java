package com.zooclinic.controller;

import com.zooclinic.controller.dto.AnimalCreationDto;
import com.zooclinic.controller.dto.AnimalDto;
import com.zooclinic.controller.dto.AppointmentCreationDto;
import com.zooclinic.controller.dto.AppointmentDto;
import com.zooclinic.controller.dto.AppointmentUpdateDto;
import com.zooclinic.controller.dto.AppointmentWithAnimalDto;
import com.zooclinic.entity.Animal;
import com.zooclinic.entity.Appointment;
import com.zooclinic.service.AnimalService;
import com.zooclinic.service.exception.AnimalNotFoundException;
import com.zooclinic.service.exception.AppointmentNotFoundException;
import java.util.List;
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

@RestController
@RequestMapping(value = "/animal")
public class AnimalController {

  private final AnimalService animalService;

  @Autowired
  public AnimalController(AnimalService animalService) {
    this.animalService = animalService;
  }

  @GetMapping("/{id}")
  public AnimalDto getAnimalById(@PathVariable Long id) throws AnimalNotFoundException {
    return AnimalDto.fromEntity(
        animalService.getAnimalById(id)
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AnimalDto createAnimal(@RequestBody AnimalCreationDto animalCreationDto) {
    return AnimalDto.fromEntity(
        animalService.createAnimal(animalCreationDto.toEntity())
    );
  }

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

  @PutMapping("/{id}")
  public AnimalDto updateAnimal(
      @PathVariable Long id,
      @RequestBody AnimalCreationDto animalCreationDto)
    throws AnimalNotFoundException {
    return AnimalDto.fromEntity(
        animalService.updateAnimal(id, animalCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{id}")
  public AnimalDto deleteAnimal(@PathVariable Long id) throws AnimalNotFoundException {
    return AnimalDto.fromEntity(
        animalService.deleteAnimal(id)
    );
  }

  @PostMapping("/{animalId}/appointment")
  @ResponseStatus(HttpStatus.CREATED)
  public AppointmentDto createAppointmentByAnimalId(
      @PathVariable Long animalId,
      @RequestBody AppointmentCreationDto appointmentCreationDto )
    throws AnimalNotFoundException {
    return AppointmentDto.fromEntity(
        animalService.createAppointment(animalId, appointmentCreationDto.toEntity())
    );
  }

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
