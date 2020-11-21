package pl.arturszejna.ICD10SearchEngine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arturszejna.ICD10SearchEngine.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
