package com.myorg.technical.service.business;

import com.myorg.technical.service.dao.ImageDao;
import com.myorg.technical.service.dao.UserDao;
import com.myorg.technical.service.entity.ImageEntity;
import com.myorg.technical.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageUploadService {

    @Autowired
    private ImageDao imageDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ImageEntity upload(ImageEntity imageEntity) {
        //call createImage() method for imageDao and pass imageEntity as an argument
        //Note that createImage() method returns the created image of type ImageEntity
        //Also note that upload() method returns the value returned by createImage() method
        ImageEntity createdImage = imageDao.createImage(imageEntity);
        return createdImage;
    }
}

