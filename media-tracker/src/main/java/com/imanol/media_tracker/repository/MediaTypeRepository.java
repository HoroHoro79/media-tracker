package com.imanol.media_tracker.repository;

import com.imanol.media_tracker.model.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaTypeRepository extends JpaRepository<MediaType, Long> {
    // Podemos añadir queries personalizadas si es necesario
}
