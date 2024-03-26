package com.example.Galley.GalleyDatabase.Repository;

import com.example.Galley.GalleyDatabase.Entity.UserRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NormalRepository extends JpaRepository<UserRegistrationEntity,String> {
}
