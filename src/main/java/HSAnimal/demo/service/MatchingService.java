package HSAnimal.demo.service;

import com.example.demo.DTO.AnimalDTO;
import com.example.demo.DTO.OptionDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
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

    // 사용자의 키워드 리스트 생성
    public List<OptionDTO> getOptionsDTOList(String userId){
        List<UserKeywords> optionsList = userKeywordsRepository.findAllByUserId(userId);
        List<OptionDTO> optionsDTOList = new ArrayList<>();
        for (UserKeywords option: optionsList) {
            int optionId = option.getOptionId();
            if (optionsRepository.findByOptionId(optionId).isPresent()) {
                OptionDTO optionsDTO = OptionDTO.builder()
                        .optionId(optionId)
                        .build();
                optionsDTOList.add(optionsDTO);
            }
        }
        return optionsDTOList;
    }

    // 사용자와 겹치는 동물 리스트 생성
    public Set<AnimalDTO> getAnimalDTOList(List<Integer> optionDTOList){
        Set<AnimalDTO> animalDTOList = new HashSet<>();
//        for(int option : optionDTOList){
//            List<AnimalKeywords> animalKeywordsList = animalKeywordsRepository.findAllByOptionId(option);
//            for (AnimalKeywords animalKeywords : animalKeywordsList) {
//                Optional<Animal> optionalAnimal = animalRepository.findByAnimalId(animalKeywords.getAnimalId());
//                if (optionalAnimal.isPresent()) {
//                    animalDTOList.add(AnimalDTO.builder()
//                            .animalId(optionalAnimal.get().getAnimalId())
//                            .animalName(optionalAnimal.get().getAnimalName())
//                            .build());
//                }
//            }
//        }
        for (int option : optionDTOList) {
            List<AnimalKeywords> animalKeywordsList = animalKeywordsRepository.findAllByOptionId(option);
            animalKeywordsList.forEach(animalKeywords -> {
                animalRepository.findByAnimalId(animalKeywords.getAnimalId())
                        .ifPresent(optionalAnimal -> animalDTOList.add(AnimalDTO.builder()
                                .animalId(optionalAnimal.getAnimalId())
                                .animalName(optionalAnimal.getAnimalName())
                                .build()));
            });
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
                        .get().getQuestionId()).getWeight();
                sum += weight;
                animalDTO.setSum(sum);
            }
        }
        List<AnimalDTO> animalList = new ArrayList<>(animalDTOSet);
        Comparator<AnimalDTO> sumComparator = Comparator.comparingInt(AnimalDTO::getSum);
        Collections.sort(animalList, sumComparator.reversed());
        return animalList;
    }
}
