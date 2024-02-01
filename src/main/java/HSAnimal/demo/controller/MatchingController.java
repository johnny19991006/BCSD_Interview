package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.AnimalDTO;
import HSAnimal.demo.service.MatchingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService){
        this.matchingService = matchingService;
    }

    @GetMapping("/{user_id}/match")
    public List<String> showAnimalList() {
        return matchingService.getAnimalList();
    }

    @PostMapping("/{user_id}/match")
    public List<AnimalDTO> matchingAnimals(@PathVariable String user_id) {
        List<Integer> optionList = matchingService.getMyOptionList(user_id);
        Set<AnimalDTO> animalList = matchingService.getAnimalDTOList(optionList);
        return matchingService.sumWeights(optionList, animalList);
    }
}
