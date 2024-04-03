package com.example.jo.repositories;

import com.example.jo.entities.InfrastructureSportive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InfrastructureSportiveRepository extends JpaRepository<InfrastructureSportive, UUID> {
}
