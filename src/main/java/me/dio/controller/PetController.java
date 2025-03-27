package me.dio.controller;

import me.dio.domain.model.Pet;
import me.dio.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> findById(@PathVariable Long id) {
        var pet = petService.findById(id);
        return ResponseEntity.ok(pet);
    }

    @GetMapping
    public ResponseEntity<List<Pet>> findAll() {
        var pets = petService.findAll();
        return ResponseEntity.ok(pets);
    }

    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet petToCreate) {
        var petCreated = petService.create(petToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(petCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(petCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> update(@PathVariable Long id, @RequestBody Pet petToUpdate) {
        var updatedPet = petService.update(id, petToUpdate);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}