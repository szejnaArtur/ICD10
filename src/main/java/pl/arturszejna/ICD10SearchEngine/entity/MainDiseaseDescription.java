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
public class MainDiseaseDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long main_description_id;

    @Column
    private String text;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "main_disease_id")
    private MainDisease mainDisease;

    public static MainDiseaseDescription of(String text, MainDisease mainDiseaseId){
        MainDiseaseDescription mainDiseaseDescription = new MainDiseaseDescription();
        mainDiseaseDescription.setText(text);
        mainDiseaseDescription.setMainDisease(mainDiseaseId);
        return mainDiseaseDescription;
    }

}
