package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.arturszejna.ICD10SearchEngine.entity.MainDisease;
import pl.arturszejna.ICD10SearchEngine.entity.UnitDisease;
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

        List<MainDisease> mineDiseases = mainDiseaseService.getMainDiseases();

        model.addAttribute("diseases", mineDiseases);

        return "diseases";
    }

    @GetMapping("/diseases/add")
    public String addMainDisease() {

        String text = "A05 Inne bakterie zawarte w pokarmie powodujące zatrucia niesklasyfikowane gdzie indziej\n" +
                "Nie obejmuje: zakażenia wywołane przez Escherichia coli (A04.0–A04.4), listerioza (A32.–), zatrucie pokarmowe i zakażenie wywołane przez Salmonella (A02.–), zatrucie spowodowane przez szkodliwe produkty spożywcze (T61–T62)\n" +
                "A05.0 Pokarmowe zatrucie gronkowcowe\n" +
                "A05.1 Zatrucie pokarmowe wywołane przez Clostridium botulinum\n" +
                "Klasyczne zatrucie pokarmowe wywołane przez Clostridium botulinum\n" +
                "A05.2 Zatrucie pokarmowe wywołane przez Clostridium perfringens [Clostridium welchii]\n" +
                "Martwicze zapalenie jelit. Pig-bel\n" +
                "A05.3 Zatrucie pokarmowe wywołane przez Vibrio parahaemolyticus\n" +
                "A05.4 Zatrucie pokarmowe wywołane przez Bacillus cereus\n" +
                "A05.8 Inne określone bakteryjne zatrucia pokarmowe\n" +
                "A05.9 Bakteryjne zatrucie pokarmowe, nieokreślone ";

        MainDisease newDisease = new MainDisease();
        List<UnitDisease> unitDiseasesList = new ArrayList<>();
        String[] split = text.split("\n");
        int loop = 0;
        for ( String i : split ) {
            if (loop == 0) {
                String code = i.substring(0, 3);
                String name = i.substring(4);
                newDisease.setCode(code);
                newDisease.setName(name);
                loop += 1;
            } else {
                if(loop == 1 && !isNumeric(i.substring(1, 3))){
                    newDisease.setDescription(i);
                    loop += 1;
                } else {
                    if (isNumeric(i.substring(1, 3))) {
                        String code = i.substring(0, 5);
                        String name = i.substring(6);
                        unitDiseasesList.add(UnitDisease.of(code, name, newDisease));
                        loop += 1;
                    } else {
                        unitDiseasesList.get(unitDiseasesList.size() - 1).setDescription(i);
                        loop += 1;
                    }
                }
            }
        }

        newDisease.setUnitDiseases(unitDiseasesList);
        mainDiseaseService.addMainDisease(newDisease);

        return "diseases";
    }

    private boolean isNumeric(String text) {
        for ( char c : text.toCharArray() ) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
