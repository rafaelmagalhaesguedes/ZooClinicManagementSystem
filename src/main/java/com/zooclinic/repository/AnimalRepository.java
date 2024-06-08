package com.zooclinic.repository;

import com.zooclinic.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Animal repository.
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> { }