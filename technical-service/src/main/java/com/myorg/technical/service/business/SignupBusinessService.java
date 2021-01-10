package com.myorg.technical.service.business;

import com.myorg.technical.service.dao.UserDao;
import com.myorg.technical.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupBusinessService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    //This method receives the UserEntity type object and calls createUser() method in UserDao class.
    //This method returns the UserEntity type object which has been stored in a database.
    public UserEntity signup(UserEntity userEntity) {
        if(userDao.getUser(userEntity.getEmail()) != null){
            System.out.println("User already exists!!");
            return null;
        }
        String[] encryptedText = passwordCryptographyProvider.encrypt(userEntity.getPassword());
        //encryptedPassword contains salt as 1st value and encrypted password as 2nd value.
        userEntity.setSalt(encryptedText[0]);
        userEntity.setPassword(encryptedText[1]);
        return userDao.createUser(userEntity);

    }
}
