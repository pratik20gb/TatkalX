package com.tatkalx.backend.dto;

import java.time.OffsetDateTime;
import java.util.Map;

public record ErrorResponse(
        int status,
        String message,
        Map<String,String> errors,
        OffsetDateTime timestamp
) {
}
