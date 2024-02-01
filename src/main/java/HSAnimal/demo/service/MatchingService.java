package HSAnimal.demo.service;

import HSAnimal.demo.DTO.AnimalDTO;
import HSAnimal.demo.domain.*;
import HSAnimal.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchingService {

    private final OptionsRepository optionsRepository;
    private final AnimalRepository animalRepository;
    private final UserKeywordsRepository userKeywordsRepository;
    private final AnimalKeywordsRepository animalKeywordsRepository;
    private final QuestionsRepository questionsRepository;

    @Autowired
    public MatchingService (OptionsRepository optionsRepository, AnimalRepository animalRepository,
                            UserKeywordsRepository userKeywordsRepository, AnimalKeywordsRepository animalKeywordsRepository,
                            QuestionsRepository questionsRepository){
        this.optionsRepository = optionsRepository;
        this.animalRepository = animalRepository;
        this.userKeywordsRepository = userKeywordsRepository;
        this.animalKeywordsRepository = animalKeywordsRepository;
        this.questionsRepository = questionsRepository;
    }

    public List<String> getAnimalList(){
        List<Animal> animalList = animalRepository.findAll();
        return animalList.stream()
                .map(Animal::getAnimalName)
                .collect(Collectors.toList());
    }

    public List<Integer> getMyOptionList(String userId){
        List<UserKeywords> userKeywordsList = userKeywordsRepository.findAllByUserId(userId);
        List<Integer> optionsList = new ArrayList<>();
        for (UserKeywords option: userKeywordsList) {
            optionsList.add(option.getOptionId());
        }
        return optionsList;
    }

    // 사용자와 겹치는 동물 리스트 생성
    public Set<AnimalDTO> getAnimalDTOList(List<Integer> optionList){
        Set<AnimalDTO> animalDTOList = new HashSet<>();
        for(int option : optionList){
            List<AnimalKeywords> animalKeywordsList = animalKeywordsRepository.findAllByOptionId(option);
            for (AnimalKeywords animalKeywords : animalKeywordsList) {
                Optional<Animal> optionalAnimal = animalRepository.findByAnimalId(animalKeywords.getAnimalId());
                optionalAnimal.ifPresent(animal ->
                    animalDTOList.add(AnimalDTO.builder()
                            .animalId(animal.getAnimalId())
                            .animalName(animal.getAnimalName())
                            .build())
                );
            }
        }
        return animalDTOList;
    }


    // 매칭된 동물들 반환
    public List<AnimalDTO> sumWeights(List<Integer> optionList, Set<AnimalDTO> animalDTOSet){
        for(AnimalDTO animalDTO: animalDTOSet){
            List<Integer> optionIdList = animalKeywordsRepository.findAllByAnimalId(animalDTO.getAnimalId())
                    .stream()
                    .map(AnimalKeywords::getOptionId)
                    .collect(Collectors.toList());
            Set<Integer> intersection = new HashSet<>(optionList);
            intersection.retainAll(optionIdList);

            int sum = 0;
            for (int optionId: intersection) {
                int weight = questionsRepository.findByQuestionId(optionsRepository.findByOptionId(optionId)
                        .get().getQuestionId()).get().getWeight();
                sum += weight;
                animalDTO.setMatchScore(sum);
            }
        }
        List<AnimalDTO> animalList = new ArrayList<>(animalDTOSet);
        Comparator<AnimalDTO> sumComparator = Comparator.comparingInt(AnimalDTO::getMatchScore);
        animalList.sort(sumComparator.reversed());
        return animalList;
    }
}
