package com.imanol.media_tracker.facade;


import com.imanol.media_tracker.dto.MediaDto;
import com.imanol.media_tracker.dto.request.MediaRequest;
import com.imanol.media_tracker.dto.response.MediaResponse;
import com.imanol.media_tracker.mapper.MediaMapper;
import com.imanol.media_tracker.model.Media;
import com.imanol.media_tracker.service.MediaService;
import com.imanol.media_tracker.service.MediaStatusService;
import com.imanol.media_tracker.service.MediaTypeService;
import com.imanol.media_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MediaFacade {

    private final MediaService mediaService;

    private final MediaStatusService mediaStatusService;

    private final MediaTypeService mediaTypeService;

    private final UserService userService;

    private final MediaMapper mediaMapper;


    public List<MediaResponse> getAllMedia(){
        List<Media> media =  mediaService.findAll();
        return media.stream().map(mediaMapper::entityToDto).toList();
    }


    public MediaResponse getById(Long id) {
        Media media = mediaService.findById(id);
        return mediaMapper.entityToDto(media);
    }

    public MediaResponse post(MediaRequest mediaRequest) {
        MediaDto mediaDto= MediaDto.builder().title(mediaRequest.getTitle())
                .description(mediaRequest.getDescription())
                .authorOrDirector(mediaRequest.getAuthorOrDirector())
                .coverUrl(mediaRequest.getCoverUrl())
                .releaseDate(mediaRequest.getReleaseDate())
                .user(userService.findById(mediaRequest.getUserId()))
                .type(mediaTypeService.findById(mediaRequest.getMediaTypeId()))
                .status(mediaStatusService.findById(mediaRequest.getMediaStatusId())).build();
        return mediaMapper.entityToDto(mediaService.save(mediaMapper.DtoToEntity(mediaDto)));
    }

    public List<MediaResponse> getByUser(Long id) {
        List<Media> media = mediaService.findByUser(userService.findById(id));
        return media.stream().map(mediaMapper::entityToDto).toList();
    }
}
