package pl.arturszejna.ICD10SearchEngine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MainDisease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long main_disease_id;

    @Column
    private String code;

    @Column
    private String name;

    @OneToMany(mappedBy = "mainDisease", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<UnitDisease> unitDiseases;

    public static MainDisease of(String code, String name){
        MainDisease disease = new MainDisease();
        disease.setCode(code);
        disease.setName(name);
        return disease;
    }

}