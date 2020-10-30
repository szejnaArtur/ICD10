package pl.arturszejna.ICD10SearchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturszejna.ICD10SearchEngine.entity.Disease;
import pl.arturszejna.ICD10SearchEngine.repository.DiseaseRepository;

import java.util.List;

@Service
public class DiseaseService {

    private DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository){
        this.diseaseRepository = diseaseRepository;
    }

    public List<Disease> getAllDisease(){
        return diseaseRepository.findAll();
    }

    public void find(){
        List<Disease> all = diseaseRepository.findAllByNameFirstLetter("kr");
        all.forEach(System.out::println);
    }

}
