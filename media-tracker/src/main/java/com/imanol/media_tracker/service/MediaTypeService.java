package com.imanol.media_tracker.service;

import com.imanol.media_tracker.exception.ObjectNotExistsException;
import com.imanol.media_tracker.model.MediaType;
import com.imanol.media_tracker.repository.MediaTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaTypeService {

    private final MediaTypeRepository mediaTypeRepository;


    public List<MediaType> findAll() {
        return mediaTypeRepository.findAll((Sort.by(Sort.Direction.ASC, "id")));
    }

    public MediaType findById(Long id) {
        return mediaTypeRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotExistsException("El registro con id " + id + " no existe en la BBDD en la tabla Type"));
    }
}
