package com.imanol.media_tracker.service;

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
}
