package pl.arturszejna.ICD10SearchEngine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.arturszejna.ICD10SearchEngine.entity.Disease;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    @Query("SELECT d FROM Disease d WHERE d.name LIKE CONCAT('%',:text,'%') ")
    List<Disease> findAllByNameFirstLetter(@Param("text") String text);

}
