package com.imanol.media_tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String message;           // Mensaje general del error
    private List<String> details;     // Detalles específicos (por ejemplo, errores de validación)
}
