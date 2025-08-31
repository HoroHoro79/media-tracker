package com.imanol.media_tracker.mapper;

import com.imanol.media_tracker.dto.MediaDto;
import com.imanol.media_tracker.dto.response.MediaResponse;
import com.imanol.media_tracker.model.Media;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {

    public MediaResponse entityToDto(Media entity) {
        return MediaResponse.builder()
                .id(entity.getId()).description(entity.getDescription())
                .title(entity.getTitle())
                .mediaStatusId(entity.getStatus().getId())
                .mediaStatusName(entity.getStatus().getName())
                .mediaTypeId(entity.getType().getId())
                .mediaTypeName(entity.getType().getName())
                .releaseDate(entity.getReleaseDate())
                .authorOrDirector(entity.getAuthorOrDirector())
                .coverUrl(entity.getCoverUrl())
                .userId(entity.getUser().getId()).build();
    }

    public Media DtoToEntity(MediaDto dto) {
       return Media.builder().title(dto.getTitle()).description(dto.getDescription())
               .status(dto.getStatus())
               .type(dto.getType())
               .user(dto.getUser())
               .coverUrl(dto.getCoverUrl())
               .releaseDate(dto.getReleaseDate())
               .authorOrDirector(dto.getAuthorOrDirector()).build();


    }
}
