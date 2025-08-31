package com.imanol.media_tracker.controller;

import com.imanol.media_tracker.constants.ConstantsRest;
import com.imanol.media_tracker.dto.request.MediaRequest;
import com.imanol.media_tracker.dto.response.MediaResponse;
import com.imanol.media_tracker.facade.MediaFacade;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ConstantsRest.BASE_MEDIA)
public class MediaController {

    private final MediaFacade mediaFacade;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<MediaResponse>> getAll() {
        return new ResponseEntity<>(mediaFacade.getAllMedia(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(ConstantsRest.GET_BY_ID)
    public ResponseEntity<MediaResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mediaFacade.getById(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(ConstantsRest.MEDIA_USER+ ConstantsRest.GET_BY_ID)
    public ResponseEntity<List<MediaResponse>> getByUser(@PathVariable Long id) {
        return new ResponseEntity<>(mediaFacade.getByUser(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<MediaResponse> post(@RequestBody MediaRequest mediaRequest) {
        return new ResponseEntity<>(mediaFacade.post(mediaRequest),  HttpStatus.OK);
    }

}
