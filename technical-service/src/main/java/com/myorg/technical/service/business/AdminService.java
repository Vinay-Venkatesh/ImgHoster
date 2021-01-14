package com.myorg.technical.service.business;

import com.myorg.technical.service.dao.ImageDao;
import com.myorg.technical.service.dao.UserDao;
import com.myorg.technical.service.entity.ImageEntity;
import com.myorg.technical.service.exception.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ImageDao imageDao;

    public ImageEntity getImage(final String imageUuid) throws ImageNotFoundException {
        ImageEntity imageEntity = imageDao.getImage(imageUuid);
        if(imageEntity == null){
            throw new ImageNotFoundException("IMG-001","Image with given uuid not found");
        }
        return imageEntity;
    }
}
