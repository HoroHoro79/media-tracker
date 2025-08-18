package com.imanol.media_tracker.facade;

import com.imanol.media_tracker.dto.response.MediaTypeResponse;
import com.imanol.media_tracker.mapper.MediaTypeMapper;
import com.imanol.media_tracker.model.MediaType;
import com.imanol.media_tracker.service.MediaTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminParamFacade {

    private final MediaTypeService mediaTypeService;
    private final MediaTypeMapper mediaTypeMapper;

    public List<MediaTypeResponse> findAllMediaTypes(){
        List<MediaType> mediaTypes =  mediaTypeService.findAll();
        return mediaTypes.stream().map(mediaTypeMapper::entityToDto).toList();
    }
}
