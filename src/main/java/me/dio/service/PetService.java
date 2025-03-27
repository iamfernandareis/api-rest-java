package me.dio.service;

import me.dio.domain.model.Pet;
import java.util.List;

public interface PetService {
    Pet findById(Long id);
    List<Pet> findAll();
    Pet create(Pet petToCreate);
    Pet update(Long id, Pet petToUpdate);
    void delete(Long id);
}