package com.myorg.technical.api.controller;

import com.myorg.technical.api.model.SignupUserRequest;
import com.myorg.technical.api.model.SignupUserResponse;
import com.myorg.technical.service.business.SignupBusinessService;
import com.myorg.technical.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class SignupController {

    @Autowired
    private SignupBusinessService signupBusinessService;


    //This method is for user signup
    //This method receives the object of SignupUserRequest type with its attributes being set.
    //This method is listening for a HTTP POST request as indicated by method= RequestMethod.POST , maps to a URL request of type '/usersignup' and consumes and produces Json.
    @RequestMapping(method= RequestMethod.POST, path="/usersignup", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupUserResponse> userSignup(final SignupUserRequest signupUserRequest) {
        //Now declaring the object of UserEntity class and set its corresponding attributes using signupUserRequest
        final UserEntity userEntity = new UserEntity();

        userEntity.setUuid(UUID.randomUUID().toString());
        userEntity.setFirstName(signupUserRequest.getFirstName());
        userEntity.setLastName(signupUserRequest.getLastName());
        userEntity.setEmail(signupUserRequest.getEmailAddress());
        userEntity.setPassword(signupUserRequest.getPassword());
        userEntity.setMobilePhone(signupUserRequest.getMobileNumber());
        userEntity.setSalt("1234abc");
        userEntity.setRole("nonadmin");

        //After setting the attributes of userEntity, pass it to signup() method, to add this object to a persistent context
        final UserEntity createdUserEntity = signupBusinessService.signup(userEntity);
        if(createdUserEntity == null){
            //Declaring an object of SignupUserResponse type and set its attributes using createdUserEntity.
            SignupUserResponse userResponse = new SignupUserResponse().id(userEntity.getEmail()).status("USER ALREADY EXISTS");
            return new ResponseEntity<SignupUserResponse>(userResponse,HttpStatus.ALREADY_REPORTED);
        }

        //Declaring an object of SignupUserResponse type and set its attributes using createdUserEntity.
        SignupUserResponse userResponse = new SignupUserResponse().id(createdUserEntity.getUuid()).status("USER SUCCESSFULLY REGISTERED");

        return new ResponseEntity<SignupUserResponse>(userResponse,HttpStatus.CREATED);
    }

}
