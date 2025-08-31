package com.imanol.media_tracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;


    // 🔗 Relación con MediaType
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private MediaType type;

    // 🔗 Relación con MediaStatus
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private MediaStatus status;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "author_or_director")
    private String authorOrDirector;

    @Column(name = "cover_url", length = 500)
    private String coverUrl;

    // 🔗 Relación con usuario (cada usuario tiene sus propios medios)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
