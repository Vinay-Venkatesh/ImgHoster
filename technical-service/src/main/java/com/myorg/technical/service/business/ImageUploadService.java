package com.myorg.technical.service.business;

import com.myorg.technical.service.dao.ImageDao;
import com.myorg.technical.service.dao.UserDao;
import com.myorg.technical.service.entity.ImageEntity;
import com.myorg.technical.service.entity.UserAuthTokenEntity;
import com.myorg.technical.service.entity.UserEntity;
import com.myorg.technical.service.exception.UploadFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageUploadService {

    @Autowired
    private ImageDao imageDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ImageEntity upload(ImageEntity imageEntity, final String authorizationToken) throws UploadFailedException {

        //Firstly checking that the access token is a valid one(exists in the user_auth_tokens table)
        UserAuthTokenEntity userAuthTokenEntity = imageDao.getUserAuthToken(authorizationToken);

        //userAuthTokenEntity = null means that there is no record in user_auth_token table with given access token
        if (userAuthTokenEntity == null) {
            throw new UploadFailedException("UP-001","User is not Signed in, sign in to upload an image");
        }

        //Before persisting the object to the database we need to set the user_id of imageEntity
        imageEntity.setUser_id(userAuthTokenEntity.getUser());

        ImageEntity imgEntity = imageDao.createImage(imageEntity);
        return imgEntity;
    }
}

