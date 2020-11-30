package pl.arturszejna.ICD10SearchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturszejna.ICD10SearchEngine.entity.MainDisease;
import pl.arturszejna.ICD10SearchEngine.repository.MainDiseaseRepository;

import java.util.List;

@Service
public class MainDiseaseService {

    private MainDiseaseRepository mainDiseaseRepository;

    @Autowired
    public MainDiseaseService(MainDiseaseRepository diseaseRepository) {
        this.mainDiseaseRepository = diseaseRepository;
    }

    public List<MainDisease> getMainDiseases() {
        return mainDiseaseRepository.findAll();
    }

    public List<MainDisease> findByCode(String keyword) {
        return mainDiseaseRepository.findByKeyword(keyword);
    }

    public void addMainDisease(MainDisease mainDisease) {
        mainDiseaseRepository.save(mainDisease);
    }

}
