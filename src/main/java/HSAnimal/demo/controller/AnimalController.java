package HSAnimal.demo.controller;

import HSAnimal.demo.domain.Animal;
import HSAnimal.demo.repository.AnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

// 코드 미완성(사용X)
// 동물 정보 페이지 관리 클래스
@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalRepository animalRepository;
    public AnimalController(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

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
