package HSAnimal.demo.controller;

import HSAnimal.demo.domain.Animal;
import HSAnimal.demo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping("/animal")
    public Animal create(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @GetMapping("/{animal_name}")
    public Animal read(@PathVariable String animal_name) {
        return animalRepository.findByAnimalName(animal_name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "animal not found"));
    }
}