package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.arturszejna.ICD10SearchEngine.service.DiseaseTextConverterService;
import pl.arturszejna.ICD10SearchEngine.service.DiseaseService;

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
    public String getDiseasesFiltered(Model model, String keyword) {

        model.addAttribute("diseases", diseaseService.getDiseases());
        //textConverter.convertTextAndAddToDatabase();

        return "diseases";
    }
}
