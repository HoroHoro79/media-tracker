package com.imanol.media_tracker.dto;

import com.imanol.media_tracker.model.MediaStatus;
import com.imanol.media_tracker.model.MediaType;
import com.imanol.media_tracker.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDto {


    private String title;

    private String description;

    private LocalDate releaseDate;

    private String authorOrDirector;

    private String coverUrl;

    private User user;

    private MediaType type;

    private MediaStatus status;
}
