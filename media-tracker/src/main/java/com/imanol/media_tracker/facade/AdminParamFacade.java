package com.imanol.media_tracker.facade;

import com.imanol.media_tracker.dto.response.MediaStatusResponse;
import com.imanol.media_tracker.dto.response.MediaTypeResponse;
import com.imanol.media_tracker.mapper.MediaStatusMapper;
import com.imanol.media_tracker.mapper.MediaTypeMapper;
import com.imanol.media_tracker.model.MediaStatus;
import com.imanol.media_tracker.model.MediaType;
import com.imanol.media_tracker.service.MediaStatusService;
import com.imanol.media_tracker.service.MediaTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminParamFacade {

    private final MediaTypeService mediaTypeService;

    private final MediaStatusService mediaStatusService;

    private final MediaTypeMapper mediaTypeMapper;

    private final MediaStatusMapper mediaStatusMapper;

    public List<MediaTypeResponse> findAllMediaTypes(){
        List<MediaType> mediaTypes =  mediaTypeService.findAll();
        return mediaTypes.stream().map(mediaTypeMapper::entityToDto).toList();
    }

    public List<MediaStatusResponse> findAllMediaStatuses() {
        List<MediaStatus> mediaStatuses =  mediaStatusService.findAll();
        return mediaStatuses.stream().map(mediaStatusMapper::entityToDto).toList();
    }
}
