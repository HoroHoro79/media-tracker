package com.imanol.media_tracker.controller;

import com.imanol.media_tracker.constants.ConstantsRest;
import com.imanol.media_tracker.dto.response.MediaStatusResponse;
import com.imanol.media_tracker.dto.response.MediaTypeResponse;
import com.imanol.media_tracker.facade.AdminParamFacade;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ConstantsRest.BASE_PARAM_REST)
@RequiredArgsConstructor
public class AdminParamController {

    private final AdminParamFacade  adminParamFacade;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(ConstantsRest.MEDIA_TYPES)
    public ResponseEntity<List<MediaTypeResponse>> getAllTypes() {
        return new ResponseEntity<>(adminParamFacade.findAllMediaTypes(), HttpStatus.OK);
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(ConstantsRest.MEDIA_STATUS)
    public ResponseEntity<List<MediaStatusResponse>> getALLStatuses() {
        return new ResponseEntity<>(adminParamFacade.findAllMediaStatuses(), HttpStatus.OK);
    }

}
