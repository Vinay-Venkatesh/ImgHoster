package com.myorg.technical.api.controller;

import com.myorg.technical.api.model.ImageDetailsResponse;
import com.myorg.technical.service.business.AdminService;
import com.myorg.technical.service.entity.ImageEntity;
import com.myorg.technical.service.exception.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @RequestMapping(method = RequestMethod.GET, path = "/images/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ImageDetailsResponse> getImage(@PathVariable("id") final String imageUuid) throws ImageNotFoundException {
        ImageEntity imageEntity = adminService.getImage(imageUuid);
        if(imageEntity == null){
            throw new ImageNotFoundException("IMG-001","Image with given uuid not found");
        }
        ImageDetailsResponse imageDetailsResponse = new ImageDetailsResponse();
        imageDetailsResponse.setName(imageEntity.getName());
        imageDetailsResponse.setDescription(imageEntity.getDescription());
        imageDetailsResponse.setStatus(imageEntity.getStatus());
        imageDetailsResponse.setId((int) imageEntity.getId());

        return new ResponseEntity<ImageDetailsResponse>(imageDetailsResponse , HttpStatus.OK);
    }
}
