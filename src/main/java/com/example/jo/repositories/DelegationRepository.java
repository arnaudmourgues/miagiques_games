package com.example.jo.repositories;

import com.example.jo.db.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DelegationRepository extends JpaRepository<Delegation, UUID> {
}
