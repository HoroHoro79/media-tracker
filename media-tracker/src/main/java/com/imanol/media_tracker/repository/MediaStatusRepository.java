package com.imanol.media_tracker.repository;

import com.imanol.media_tracker.model.MediaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaStatusRepository extends JpaRepository<MediaStatus, Long> {
}
