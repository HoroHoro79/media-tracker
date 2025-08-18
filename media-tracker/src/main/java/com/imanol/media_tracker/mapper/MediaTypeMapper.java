package com.imanol.media_tracker.mapper;

import com.imanol.media_tracker.dto.response.MediaTypeResponse;

import com.imanol.media_tracker.model.MediaType;

import org.springframework.stereotype.Component;

@Component
public class MediaTypeMapper {

    public MediaTypeResponse entityToDto(MediaType entity)
    {
        return MediaTypeResponse.builder().id(entity.getId()).description(entity.getDescription()).name(entity.getName()).build();
    }
}
