package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.arturszejna.ICD10SearchEngine.entity.MainDisease;
import pl.arturszejna.ICD10SearchEngine.service.DiseaseTextConverterService;
import pl.arturszejna.ICD10SearchEngine.service.MainDiseaseService;

import java.util.List;

@Controller
public class DiseaseController {

    private MainDiseaseService mainDiseaseService;
    private DiseaseTextConverterService textConverter;

    @Autowired
    public DiseaseController(MainDiseaseService mainDiseaseService, DiseaseTextConverterService textConverter) {
        this.textConverter = textConverter;
        this.mainDiseaseService = mainDiseaseService;
    }

    @GetMapping("/diseases")
    public String getMainDiseases(Model model) {

        List<MainDisease> mineDiseases = mainDiseaseService.getMainDiseases();

        model.addAttribute("diseases", mineDiseases);
        //textConverter.convertTextAndAddToDatabase();

        return "diseases";
    }
}
