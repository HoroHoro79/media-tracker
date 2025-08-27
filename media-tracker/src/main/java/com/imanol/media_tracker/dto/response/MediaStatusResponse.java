package com.imanol.media_tracker.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaStatusResponse {

    private Long id;

    private String name;

    private String description;

}
