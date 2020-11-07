package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        String text = "A30 Choroba zakaźna wywołana przez Mycobacterium leprae [trąd] [choroba Hansena]\n" +
                "Obejmuje: zakażenie wywołane przez Mycobacterium leprae\n" +
                "Nie obejmuje: następstwa trądu (B92)\n" +
                "A30.0 Trąd, postać nieokreślona\n" +
                "Trąd I\n" +
                "A30.1 Trąd, postać tuberkuloidowa\n" +
                "Trąd TT\n" +
                "A30.2 Trąd, postać tuberkuloidowa graniczna\n" +
                "Trąd BT\n" +
                "A30.3 Trąd, postać graniczna\n" +
                "Trąd BB\n" +
                "A30.4 Trąd, postać lepromatyczna graniczna\n" +
                "Trąd BL\n" +
                "A30.5 Trąd, postać lepromatyczna\n" +
                "Trąd LL\n" +
                "A30.8 Inne postacie trądu\n" +
                "A30.9 Trąd, nieokreślony";

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
                if (loop == 1 && !isNumeric(i.substring(1, 3))) {
                    if (i.substring(0, 8).equals("Obejmuje")) {
                        newDisease.setIt_includes(i);
                    } else if (i.substring(0, 12).equals("Nie obejmuje")) {
                        newDisease.setIt_does_not_includes(i);
                    } else if (i.substring(0, 5).equals("Uwaga")){
                        newDisease.setWarning(i);
                    } else {
                        newDisease.setDescription(i);
                    }
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
