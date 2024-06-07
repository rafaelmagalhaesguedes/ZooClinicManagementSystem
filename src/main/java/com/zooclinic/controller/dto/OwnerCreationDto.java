package com.zooclinic.controller.dto;

/**
 * The type Owner creation dto.
 */
public record OwnerCreationDto(
    String name,
    String address,
    String numberPhone,
    String email
) {

  /**
   * To entity owner creation dto.
   *
   * @return the owner creation dto
   */
  public OwnerCreationDto toEntity() {
    return new OwnerCreationDto(name, address, numberPhone, email);
  }
}
