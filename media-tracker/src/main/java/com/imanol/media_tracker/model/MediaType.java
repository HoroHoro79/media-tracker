package com.imanol.media_tracker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "media_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 255)
    private String description;
}
