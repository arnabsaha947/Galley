package com.example.Galley.Service;

import com.example.Galley.DataBase2.Entity.Database2Entity;
import com.example.Galley.GalleyDatabase.Entity.UserRegistrationEntity;
import com.example.Galley.DataBase2.Repository.Database2Repository;
import com.example.Galley.GalleyDatabase.Repository.NormalRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class DbOperationsService {

    /*
    @Autowired
    private DataRepositoryImpl dataRepositoryImpl;

     */

    @Autowired
    private NormalRepository normalRepository;

    @Autowired
    private Database2Repository db2Repository;

    @Autowired
    private EntityManager entityManager;

    // Using inbuilt JPA functions. Could not figure out other way of inserting data other than in built function.
    public ResponseEntity registerUserToDB(UserRegistrationEntity register){

        try{
            normalRepository.saveAndFlush(register);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("Data Inserted Successfully!",HttpStatus.OK);

    }

    // Using entity Manager.
    public List<UserRegistrationEntity> fetchUserInfo(String username){

        List<UserRegistrationEntity> userDetailsList=null;

        try{
            String insertSQL = "Select U From UserRegistrationEntity U Where username='"+username+"' ";
            userDetailsList = entityManager.createQuery(insertSQL).getResultList();
            //dataRepositoryImpl.saveToDB(register.getFullname(),register.getDob(),register.getUsername(),register.getPassword());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return userDetailsList;
    }

    public ResponseEntity getdb2Response() {

        List<Database2Entity> listAll = new ArrayList<Database2Entity>();

        try{
            listAll = db2Repository.findAll();
            return new ResponseEntity(listAll,HttpStatus.OK);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
