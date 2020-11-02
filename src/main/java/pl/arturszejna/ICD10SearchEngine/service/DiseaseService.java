package pl.arturszejna.ICD10SearchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturszejna.ICD10SearchEngine.entity.MainDisease;
import pl.arturszejna.ICD10SearchEngine.repository.MainDiseaseRepository;

import java.util.List;

@Service
public class DiseaseService {

    private MainDiseaseRepository mainDiseaseRepository;

    @Autowired
    public DiseaseService(MainDiseaseRepository diseaseRepository) {
        this.mainDiseaseRepository = diseaseRepository;
    }

    public List<MainDisease> getMainDiseases() {
        return mainDiseaseRepository.findAll();
    }

    public List<MainDisease> findByKeyword(String keyword) {
        return mainDiseaseRepository.findByKeyword(keyword);
    }

}
