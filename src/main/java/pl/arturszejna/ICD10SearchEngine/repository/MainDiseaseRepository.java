package pl.arturszejna.ICD10SearchEngine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.arturszejna.ICD10SearchEngine.entity.MainDisease;

import java.util.List;

public interface MainDiseaseRepository extends JpaRepository<MainDisease, Long> {

    @Query(value = "SELECT * FROM main_disease d WHERE d.code LIKE CONCAT(:keyword,'%')", nativeQuery = true)
    List<MainDisease> findByKeyword(@Param("keyword") String keyword);

}
