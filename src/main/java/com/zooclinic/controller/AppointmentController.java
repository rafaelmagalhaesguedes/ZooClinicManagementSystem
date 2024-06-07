package com.zooclinic.controller;

import com.zooclinic.entity.Animal;
import com.zooclinic.entity.Appointment;
import com.zooclinic.service.AppointmentService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {

  private final AppointmentService appointmentService;

  @Autowired
  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @GetMapping
  public Map<String, Object> getAllAppointments(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {
    List<Appointment> appointments = appointmentService.getAllAppointments(pageNumber, pageSize);

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
      if (animal != null) {
        appointmentJson.put("Animal", Map.of(
            "animalId", animal.getId(),
            "animalName", animal.getName(),
            "breed", animal.getBreed(),
            "specie", animal.getSpecie(),
            "age", animal.getAge()
        ));
      }

      appointmentsJson.add(appointmentJson);
    }

    Map<String, Object> result = new HashMap<>();
    result.put("Appointments", appointmentsJson);
    return result;
  }
}
