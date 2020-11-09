package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.arturszejna.ICD10SearchEngine.entity.MainDisease;
import pl.arturszejna.ICD10SearchEngine.entity.MainDiseaseDescription;
import pl.arturszejna.ICD10SearchEngine.entity.UnitDisease;
import pl.arturszejna.ICD10SearchEngine.entity.UnitDiseaseDescription;
import pl.arturszejna.ICD10SearchEngine.service.MainDiseaseService;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

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
    public String addDiseaseFromText() {

        String text = "A19 Gruźlica prosówkowa\n" +
                "Obejmuje: gruźlica:\n" +
                "• rozsiana\n" +
                "• uogólniona\n" +
                "gruźlicze zapalenie błon surowiczych\n" +
                "A19.0 Gruźlica prosówkowa ostra o pojedynczej określonej lokalizacji\n" +
                "A19.1 Gruźlica prosówkowa ostra o wielomiejscowej lokalizacji\n" +
                "A19.2 Gruźlica prosówkowa ostra, nieokreślona\n" +
                "A19.8 Inne postacie gruźlicy prosówkowej\n" +
                "A19.9 Gruźlica prosówkowa, nieokreślona \n";

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
        return "diseases";
    }

    private boolean isNumeric(String text) {
        for ( char c : text.toCharArray() ) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
