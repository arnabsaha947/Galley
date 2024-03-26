package com.example.Galley.DataBase2.Repository;

import com.example.Galley.DataBase2.Entity.Database2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface Database2Repository extends JpaRepository<Database2Entity,String> {
}
