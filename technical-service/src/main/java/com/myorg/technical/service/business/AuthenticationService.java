package com.myorg.technical.service.business;

import com.myorg.technical.service.dao.UserDao;
import com.myorg.technical.service.entity.UserAuthTokenEntity;
import com.myorg.technical.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.rmi.UnexpectedException;
import java.time.ZonedDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserAuthTokenEntity authenticate(final String username ,final String password) throws UnexpectedException {

        UserEntity userEntity = userDao.getUser(username);
        String encryptedPassword = cryptographyProvider.encrypt(password, userEntity.getSalt());

        //Now encryptedPassword contains the password entered by the user in encrypted form
        //And userEntity.getPassword() gives the password stored in the database in encrypted form
        //We compare both the passwords.
        if (encryptedPassword.equals(userEntity.getPassword())) {
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);
            UserAuthTokenEntity userAuthTokenEntity = new UserAuthTokenEntity();
            userAuthTokenEntity.setUser(userEntity);
            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime expiresAt = now.plusHours(8);

            userAuthTokenEntity.setAccessToken(jwtTokenProvider.generateToken(userEntity.getUuid(), now, expiresAt));

            userAuthTokenEntity.setLoginAt(now);
            userAuthTokenEntity.setExpiresAt(expiresAt);

            userDao.createAuthToken(userAuthTokenEntity);

            //To update the last login time of user
            //updateUser() method in UserDao class calls the merge() method to update the userEntity
            userDao.updateUser(userEntity);
            userEntity.setLastLoginAt(now);
            return userAuthTokenEntity;
        }else{
            return null;
        }
    }
}

