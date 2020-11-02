package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thymeleaf.expression.Lists;
import pl.arturszejna.ICD10SearchEngine.entity.MainDisease;
import pl.arturszejna.ICD10SearchEngine.service.DiseaseTextConverterService;
import pl.arturszejna.ICD10SearchEngine.service.DiseaseService;

import java.util.List;

@Controller
public class DiseaseController {

    private DiseaseService diseaseService;
    private DiseaseTextConverterService textConverter;

    @Autowired
    public DiseaseController(DiseaseService diseaseService, DiseaseTextConverterService textConverter) {
        this.textConverter = textConverter;
        this.diseaseService = diseaseService;
    }

    @GetMapping("/diseases")
    public String getDiseasesFiltered(Model model) {

        List<MainDisease> diseases = diseaseService.getMainDiseases();

        //System.out.println(diseases);
        System.out.println(diseases.get(0).getUnitDiseases().get(0).getCode());

        model.addAttribute("diseases", diseases);
        //textConverter.convertTextAndAddToDatabase();

        return "diseases";
    }
}
