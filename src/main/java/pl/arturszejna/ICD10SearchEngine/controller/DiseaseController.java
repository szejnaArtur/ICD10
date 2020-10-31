package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.arturszejna.ICD10SearchEngine.service.DiseaseService;

@Controller
public class DiseaseController {

    private DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/diseases")
    public String getDiseases(Model model, String keyword) {
        if (keyword != null) {
            model.addAttribute("diseases", diseaseService.findByKeyword(keyword));
        } else {
            model.addAttribute("diseases", diseaseService.getDiseases());
        }
        return "diseases";
    }

    @GetMapping("/diseasesFiltered")
    public String getDiseasesFiltered(Model model, String keyword) {

        model.addAttribute("diseases", diseaseService.getDiseases());

        return "diseasesFiltered";
    }
}
