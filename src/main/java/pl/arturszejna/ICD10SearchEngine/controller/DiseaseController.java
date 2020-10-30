package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.arturszejna.ICD10SearchEngine.service.DiseaseService;

@Controller
public class DiseaseController {

    private DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService){
        this.diseaseService = diseaseService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView test(ModelAndView modelAndView){
        modelAndView.setViewName("start");
        diseaseService.find();

        return modelAndView;
    }
}
