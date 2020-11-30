package pl.arturszejna.ICD10SearchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturszejna.ICD10SearchEngine.entity.Problem;
import pl.arturszejna.ICD10SearchEngine.repository.ProblemRepository;

@Service
public class ProblemService {

    private ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public void addProblem(Problem problem) {
        problemRepository.save(problem);
    }

}
