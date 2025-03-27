package me.dio.service.impl;

import me.dio.domain.model.Pet;
import me.dio.domain.repository.PetRepository;
import me.dio.service.PetService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public Pet create(Pet petToCreate) {
        if (petRepository.existsByNameAndOwner(petToCreate.getName(), petToCreate.getOwner())) {
            throw new IllegalArgumentException("A pet with this name and owner already exists.");
        }
        return petRepository.save(petToCreate);
    }

    @Override
    public Pet update(Long id, Pet petToUpdate) {
        Pet existingPet = findById(id);
        
        if (!existingPet.getId().equals(petToUpdate.getId())) {
            throw new IllegalArgumentException("Pet IDs don't match.");
        }
        
        return petRepository.save(petToUpdate);
    }

    @Override
    public void delete(Long id) {
        Pet pet = findById(id);
        petRepository.delete(pet);
    }
}