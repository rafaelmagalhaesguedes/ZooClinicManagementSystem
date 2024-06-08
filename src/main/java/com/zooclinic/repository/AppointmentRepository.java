package com.zooclinic.repository;

import com.zooclinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Appointment repository.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> { }