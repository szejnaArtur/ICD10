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

        String text = "A06 Choroba zakaźna wywołana przez Entamoeba histolytica [pełzakowica] [ameboza]\n" +
                "Obejmuje: zakażenia wywołane przez Entamoeba histolytica. Nie obejmuje: inne pierwotniakowe choroby jelit (A07.–)\n" +
                "A06.0 Ostra czerwonka pełzakowa\n" +
                "Ostra pełzakowica. Pełzakowe zapalenie jelit BNO\n" +
                "A06.1 Przewlekła pełzakowica jelitowa\n" +
                "A06.2 Pełzakowy nieczerwonkowy nieżyt jelita grubego\n" +
                "A06.3 Guz pełzakowy jelita\n" +
                "Guz pełzakowy BNO\n" +
                "A06.4 Pełzakowy ropień wątroby\n" +
                "Pełzakowe zapalenie wątroby\n" +
                "A06.5 Pełzakowy ropień płuc (J99.8*)\n" +
                "Pełzakowy ropień płuc (i wątroby)\n" +
                "A06.6 Pełzakowy ropień mózgu (G07*)\n" +
                "Pełzakowy ropień mózgu (i wątroby) (i płuc)\n" +
                "A06.7 Pełzakowica skórna\n" +
                "A06.8 Pełzakowe zakażenie o innym umiejscowieniu\n" +
                "Pełzakowe: • zapalenie wyrostka robaczkowego, • zapalenie żołędzi prącia† (N51.2*)\n" +
                "A06.9 Pełzakowica, nieokreślona \n";

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
