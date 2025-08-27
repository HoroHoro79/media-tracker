package com.imanol.media_tracker.mapper;

import com.imanol.media_tracker.dto.response.MediaStatusResponse;
import com.imanol.media_tracker.model.MediaStatus;
import org.springframework.stereotype.Component;

@Component
public class MediaStatusMapper {

    public MediaStatusResponse entityToDto(MediaStatus entity) {
        return MediaStatusResponse.builder().id(entity.getId()).description(entity.getDescription()).name(entity.getName()).build();
    }
}
