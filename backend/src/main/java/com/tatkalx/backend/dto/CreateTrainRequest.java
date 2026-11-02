package com.tatkalx.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTrainRequest(
        @NotBlank(message = "Train number is required")
        @Size(min=1,max=10,message="Train number must be between 1 and 10 characters")
        String trainNumber,

        @NotBlank(message = "Train name is required")
        @Size(max = 150, message = "Train name must not exceed 150 characters")
        String trainName,


        @NotBlank(message = "Source station is required")
        @Size(max = 100, message = "Source station must not exceed 100 characters")
        String sourceStation,

        @NotBlank(message = "Destination station is required")
        @Size(max = 100, message = "Destination station must not exceed 100 characters")
        String destinationStation
){}
