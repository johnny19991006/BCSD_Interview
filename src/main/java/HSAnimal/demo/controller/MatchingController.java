package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.AnimalDTO;
import HSAnimal.demo.DTO.OptionDTO;
import HSAnimal.demo.service.MatchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService){
        this.matchingService = matchingService;
    }

    @GetMapping("/{user_id}/match")
    public List<OptionDTO> showOptionsList(@PathVariable String user_id) {
        return matchingService.getOptionsDTOList(user_id);
    }

    @PostMapping("/{user_id}/match")
    public ResponseEntity<Object> matchingAnimals(@PathVariable String user_id) {
        List<Integer> optionList = matchingService.getOptionsDTOList(user_id).stream()
                .map(OptionDTO::getOptionId)
                .collect(Collectors.toList());
        Set<AnimalDTO> animalList = matchingService.getAnimalDTOList(optionList);
        try {
            return new ResponseEntity<>(matchingService.sumWeights(optionList, animalList), HttpStatus.OK);
        }
        catch (NullPointerException e) {
            return new ResponseEntity<>("매칭된 동물이 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
