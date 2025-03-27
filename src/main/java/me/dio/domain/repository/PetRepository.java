package me.dio.domain.repository;

import me.dio.domain.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    boolean existsByNameAndOwner(String name, String owner);
}