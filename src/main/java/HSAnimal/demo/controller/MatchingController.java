package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.myAnimalDto;
import HSAnimal.demo.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MatchingController {

    private final MatchService matchService;

    public MatchingController(MatchService matchService){
        this.matchService = matchService;
    }

    @GetMapping("/{user_id}/match")
    public List<String> showAnimalList() {
        return matchService.getAnimalList();
    }

    @PostMapping("/{user_id}/match")
    public List<myAnimalDto> matchAnimals(@PathVariable String user_id) {
        return matchService.sumWeights(user_id);
    }
}
