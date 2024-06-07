package com.zooclinic.controller.dto;

import com.zooclinic.entity.Owner;

public record OwnerDto(
    Long id,
    String name,
    String address,
    String numberPhone,
    String email
) {
  public static OwnerDto fromEntity(Owner owner) {
    return new OwnerDto(
        owner.getId(),
        owner.getName(),
        owner.getAddress(),
        owner.getNumberPhone(),
        owner.getEmail()
    );
  }
}
