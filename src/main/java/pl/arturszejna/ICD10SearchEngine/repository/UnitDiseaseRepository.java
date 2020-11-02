package pl.arturszejna.ICD10SearchEngine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arturszejna.ICD10SearchEngine.entity.UnitDisease;

public interface UnitDiseaseRepository extends JpaRepository<UnitDisease, Long> {
}
