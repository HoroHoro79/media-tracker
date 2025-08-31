package com.imanol.media_tracker.service;

import com.imanol.media_tracker.exception.ObjectNotExistsException;
import com.imanol.media_tracker.model.MediaStatus;
import com.imanol.media_tracker.repository.MediaStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaStatusService {

    private final MediaStatusRepository mediaStatusRepository;


    public List<MediaStatus> findAll() {
        return mediaStatusRepository.findAll((Sort.by(Sort.Direction.ASC, "id")));
    }

    public MediaStatus findById(Long id) {
        return mediaStatusRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotExistsException("El registro con id " + id + " no existe en la BBDD en la tabla Status"));
    }
}
