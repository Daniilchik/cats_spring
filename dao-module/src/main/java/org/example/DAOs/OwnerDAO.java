package org.example.DAOs;


import org.example.Entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDAO extends JpaRepository<Owner, Integer> {
}
