package HSAnimal.demo.service;

import HSAnimal.demo.DTO.myAnimalDTO;
import HSAnimal.demo.domain.*;
import HSAnimal.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final AnimalRepository animalRepository;
    private final UserKeywordsRepository userKeywordsRepository;
    private final AnimalKeywordsRepository animalKeywordsRepository;
    private final QuestionsRepository questionsRepository;

    @Autowired
    public MatchService(AnimalRepository animalRepository, UserKeywordsRepository userKeywordsRepository,
                        AnimalKeywordsRepository animalKeywordsRepository, QuestionsRepository questionsRepository){
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

    public Set<Integer> getMyOptionList(String userId){
        List<UserKeywords> userKeywordsList = userKeywordsRepository.findAllByUserId(userId);
        Set<Integer> optionsList = new HashSet<>();
        for (UserKeywords option: userKeywordsList) {
            optionsList.add(option.getOptionId());
        }
        return optionsList;
    }

    // 사용자와 겹치는 동물 리스트 생성
    public Set<myAnimalDTO> getMyAnimalDTOList(Set<Integer> optionList){
        Set<myAnimalDTO> myAnimalDTOList = new HashSet<>();
        for(int option : optionList){
            List<AnimalKeywords> animalKeywordsList = animalKeywordsRepository.findAllByOptionId(option);
            for (AnimalKeywords animalKeywords : animalKeywordsList) {
                Optional<Animal> optionalAnimal = animalRepository.findByAnimalId(animalKeywords.getAnimalId());
                optionalAnimal.ifPresent(animal ->
                    myAnimalDTOList.add(myAnimalDTO.builder()
                            .animalId(animal.getAnimalId())
                            .animalName(animal.getAnimalName())
                            .build())
                );
            }
        }
        return myAnimalDTOList;
    }

    // 매칭된 동물들 반환
    public List<myAnimalDTO> sumWeights(String userId){
        Set<Integer> myOptionList = getMyOptionList(userId);
        Set<myAnimalDTO> myAnimalDTOSet = getMyAnimalDTOList(myOptionList);
        for(myAnimalDTO myAnimalDTO : myAnimalDTOSet){
            List<Integer> optionIdList = animalKeywordsRepository.findAllByAnimalId(myAnimalDTO.getAnimalId())
                    .stream()
                    .map(AnimalKeywords::getOptionId)
                    .collect(Collectors.toList());
            Set<Integer> intersection = new HashSet<>(myOptionList);
            intersection.retainAll(optionIdList);

            int sum = 0;
            for (int optionId: intersection) {
                int weight = questionsRepository.findWeightByOptionId(optionId);
                sum += weight;
                myAnimalDTO.setMatchScore(sum);
            }
        }
        List<myAnimalDTO> myAnimalList = new ArrayList<>(myAnimalDTOSet);
        Comparator<myAnimalDTO> sumComparator = Comparator.comparingInt(myAnimalDTO::getMatchScore);
        myAnimalList.sort(sumComparator.reversed());
        return myAnimalList;
    }
}
