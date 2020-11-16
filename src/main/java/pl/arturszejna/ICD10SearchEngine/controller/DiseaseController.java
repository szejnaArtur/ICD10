package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.arturszejna.ICD10SearchEngine.entity.MainDisease;
import pl.arturszejna.ICD10SearchEngine.entity.MainDiseaseDescription;
import pl.arturszejna.ICD10SearchEngine.entity.UnitDisease;
import pl.arturszejna.ICD10SearchEngine.entity.UnitDiseaseDescription;
import pl.arturszejna.ICD10SearchEngine.service.MainDiseaseService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DiseaseController {

    private MainDiseaseService mainDiseaseService;

    @Autowired
    public DiseaseController(MainDiseaseService mainDiseaseService) {
        this.mainDiseaseService = mainDiseaseService;
    }

    @GetMapping("/diseases")
    public String getMainDiseases(Model model) {
        List<MainDisease> mainDiseases = mainDiseaseService.getMainDiseases();
        model.addAttribute("diseases", mainDiseases);
        return "diseases";
    }

    @GetMapping("/diseases/{code}")
    public String getMainDiseasesByCode(Model model, @PathVariable("code") String code) {
        List<MainDisease> mainDiseases = mainDiseaseService.findByCode(code);
        model.addAttribute("diseases", mainDiseases);
        return "diseases";
    }

    @GetMapping("/diseases/add")
    public String addDiseaseFromText(Model model) {

        String text = "";

        MainDisease newDisease = new MainDisease();
        List<MainDiseaseDescription> mainDiseaseDescriptions = new ArrayList<>();
        List<UnitDisease> unitDiseases = new ArrayList<>();

        String[] split = text.split("\n");

        int loop = 0;
        boolean isMain = true;

        for ( String i : split ) {
            if (loop == 0) {
                String code = i.substring(0, 3);
                String name = i.substring(4);
                newDisease.setCode(code);
                newDisease.setName(name);
                loop += 1;
            } else {
                if (isMain && !isNumeric(i.substring(1, 3))) {
                    mainDiseaseDescriptions.add(MainDiseaseDescription.of(i, newDisease));
                } else {
                    isMain = false;

                    if (isNumeric(i.substring(1, 3))) {
                        String code = i.substring(0, 5);
                        String name = i.substring(6);
                        unitDiseases.add(UnitDisease.of(code, name, newDisease));
                    } else {
                        UnitDisease localUnitDisease = unitDiseases.get(unitDiseases.size() - 1);
                        UnitDiseaseDescription description = UnitDiseaseDescription.of(i, localUnitDisease);
                        localUnitDisease.getDescriptions().add(description);
                    }
                }
            }
        }
        newDisease.setDescriptions(mainDiseaseDescriptions);
        newDisease.setUnitDiseases(unitDiseases);
        mainDiseaseService.addMainDisease(newDisease);

        List<MainDisease> mainDiseases = mainDiseaseService.getMainDiseases();
        model.addAttribute("diseases", mainDiseases);
        return "diseases";
    }

    private boolean isNumeric(String text) {
        for ( char c : text.toCharArray() ) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
