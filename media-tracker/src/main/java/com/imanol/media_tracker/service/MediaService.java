package com.imanol.media_tracker.service;

import com.imanol.media_tracker.exception.ObjectNotExistsException;
import com.imanol.media_tracker.model.Media;
import com.imanol.media_tracker.model.User;
import com.imanol.media_tracker.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;


    public List<Media> findAll() {
        return mediaRepository.findAll((Sort.by(Sort.Direction.ASC, "id")));
    }

    public Media findById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotExistsException("El registro con id " + id + " no existe en la BBDD en la tabla Media"));
    }

    public Media save(Media media) {
       return mediaRepository.save(media);
    }

    public List<Media> findByUser(User user) {
        List<Media> medios = mediaRepository.findByUser(user);
        if (medios.isEmpty()) {
            throw new ObjectNotExistsException("No hay datos para el usuario solicitado");
        }
        return medios;
    }

}
