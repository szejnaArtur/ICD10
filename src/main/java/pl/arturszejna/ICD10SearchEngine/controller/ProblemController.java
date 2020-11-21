package pl.arturszejna.ICD10SearchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arturszejna.ICD10SearchEngine.entity.Problem;
import pl.arturszejna.ICD10SearchEngine.service.ProblemService;

@Controller
public class ProblemController {

    private ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService){
        this.problemService = problemService;
    }

    @GetMapping("/problem")
    public String problemGET(Model model){
        return "problem";
    }

    @PostMapping("/problem")
    public String problemPOST(Model model, @RequestParam("message") String message){
        Problem problem = Problem.of(message);
        problemService.addProblem(problem);
        return "redirect:diseases";
    }

}
