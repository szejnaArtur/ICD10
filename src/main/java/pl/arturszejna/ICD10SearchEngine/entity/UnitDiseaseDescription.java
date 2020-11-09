package pl.arturszejna.ICD10SearchEngine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UnitDiseaseDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_description_id;

    @Column
    private String text;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "unit_disease_id")
    private UnitDisease unitDisease;

    public static UnitDiseaseDescription of(String text, UnitDisease unitDiseaseId){
        UnitDiseaseDescription unitDiseaseDescription = new UnitDiseaseDescription();
        unitDiseaseDescription.setText(text);
        unitDiseaseDescription.setUnitDisease(unitDiseaseId);
        return unitDiseaseDescription;
    }

}
