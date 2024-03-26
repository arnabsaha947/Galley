package com.example.Galley.GalleyDatabase.Repository;

import com.example.Galley.GalleyDatabase.Entity.UserRegistrationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public interface DataRepositoryImpl extends Repository<UserRegistrationEntity,String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO UserRegistrationEntity (fullname,dob,username,password) WHERE VALUES= (?1,?2,?3,?4)",nativeQuery = true)
    List<UserRegistrationEntity> saveToDB(String fullname,Date dob,String username,String password) throws Exception;

}
