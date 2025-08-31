package com.imanol.media_tracker.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.imanol.media_tracker.utils.LocalDateDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaRequest {


        @NotBlank(message = "El título es obligatorio")
        private String title;

        @NotBlank(message = "La descripción es obligatoria")
        private String description;

        @NotNull(message = "La fecha de lanzamiento es obligatoria")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate releaseDate;

        private String authorOrDirector;

        private String coverUrl;

        @NotNull(message = "El tipo de medio es obligatorio")
        private Long mediaTypeId;

        @NotNull(message = "El estado del medio es obligatorio")
        private Long mediaStatusId;

        @NotNull(message = "El usuario es obligatorio")
        private Long userId;


}
