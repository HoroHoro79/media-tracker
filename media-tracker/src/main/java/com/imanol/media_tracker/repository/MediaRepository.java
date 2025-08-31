package com.imanol.media_tracker.repository;

import com.imanol.media_tracker.model.Media;
import com.imanol.media_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByUser(User user);
}
