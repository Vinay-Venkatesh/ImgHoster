package com.myorg.technical.service.dao;

import com.myorg.technical.service.entity.ImageEntity;
import com.myorg.technical.service.entity.UserAuthTokenEntity;
import com.myorg.technical.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class ImageDao {
    @PersistenceContext
    private EntityManager entityManager;
    public ImageEntity createImage(ImageEntity imageEntity) {
        entityManager.persist(imageEntity);
        return imageEntity;
    }

    public UserAuthTokenEntity getUserAuthToken(final String accesstoken) {
        try {
            return entityManager.createNamedQuery("userauthtoken", UserAuthTokenEntity.class)
                    .setParameter("accessToken", accesstoken)
                    .getSingleResult();
        }catch (NoResultException nre){
            return null;
        }
    }

    public ImageEntity getImage(final String imageUuid) {
        try {
            return entityManager.createNamedQuery("ImageEntityByUuid", ImageEntity.class).setParameter("uuid", imageUuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}

