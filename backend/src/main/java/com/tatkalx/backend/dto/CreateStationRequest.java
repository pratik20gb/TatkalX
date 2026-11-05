package com.tatkalx.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateStationRequest(
        @NotBlank(message = "Station code is required")
        @Size(min = 2, max = 10, message = "Station code must be between 2 and 10 characters")
        String stationCode,

        @NotBlank(message = "Station name is required")
        @Size(max = 100, message = "Station name cannot exceed 100 characters")
        String stationName,

        @NotBlank(message = "City is required")
        @Size(max = 100, message = "City cannot exceed 100 characters")
        String city,

        @NotBlank(message = "State is required")
        @Size(max = 100, message = "State cannot exceed 100 characters")
        String state
) {
}