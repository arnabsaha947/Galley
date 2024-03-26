package com.example.Galley.Controller;

import java.util.*;

import com.example.Galley.GalleyDatabase.Entity.UserRegistrationEntity;
import com.example.Galley.Service.DbOperationsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private DbOperationsService dbOperationsService;

    @PostMapping("/register")
    public ResponseEntity userRegistration(@RequestBody UserRegistrationEntity register ){
        // Register User to DB
        return dbOperationsService.registerUserToDB(register);

    }

    @PostMapping("/getUserDetails")
    public ResponseEntity getUserDetails(HttpServletRequest httpRequest){
        String username = httpRequest.getHeader("username");
        List<UserRegistrationEntity> userDetailsList = dbOperationsService.fetchUserInfo(username);
        if(userDetailsList.size()==1){
            return new ResponseEntity(userDetailsList.get(0), HttpStatus.OK);
        }
        return new ResponseEntity("More Than one Account found. Please Check",HttpStatus.CONFLICT);
    }

    @PostMapping("/getdb2")
    public ResponseEntity getDB2Details(){
        return dbOperationsService.getdb2Response();
    }
}
