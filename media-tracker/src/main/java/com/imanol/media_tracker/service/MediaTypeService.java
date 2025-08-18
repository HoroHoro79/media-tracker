package com.imanol.media_tracker.service;

import com.imanol.media_tracker.model.MediaType;
import com.imanol.media_tracker.repository.MediaTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaTypeService {

    private final MediaTypeRepository mediaTypeRepository;


    public List<MediaType> findAll() {
        return mediaTypeRepository.findAll();
    }
}
