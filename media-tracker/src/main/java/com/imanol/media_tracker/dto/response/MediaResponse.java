package com.imanol.media_tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponse {

    private Long id;
    private String title;
    private String description;

    private LocalDate releaseDate;
    private String authorOrDirector;
    private String coverUrl;

    private Long mediaTypeId;
    private String mediaTypeName;

    private Long mediaStatusId;
    private String mediaStatusName;

    private Long userId;
}
