package com.example.jo.repositories;

import com.example.jo.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRespository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    boolean existsByEmail(String email);
}
