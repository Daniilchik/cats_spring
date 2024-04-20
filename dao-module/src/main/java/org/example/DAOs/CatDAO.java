package org.example.DAOs;


import org.example.Entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatDAO extends JpaRepository<Cat, Integer> {
}
