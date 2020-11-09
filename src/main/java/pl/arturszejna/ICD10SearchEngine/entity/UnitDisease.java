package pl.arturszejna.ICD10SearchEngine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UnitDisease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_disease_id;

    @Column
    private String code;

    @Column
    private String name;

    @OneToMany(mappedBy = "unitDisease", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<UnitDiseaseDescription> descriptions;

//    @Column
//    private String it_includes;
//
//    @Column
//    private String it_does_not_includes;
//
//    @Column
//    private String warning;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "main_disease_id")
    private MainDisease mainDisease;


    public static UnitDisease of(String code, String name, MainDisease mainDisease){
        UnitDisease unitDisease = new UnitDisease();
        unitDisease.setCode(code);
        unitDisease.setName(name);
        unitDisease.setMainDisease(mainDisease);
        unitDisease.setDescriptions(new ArrayList<>());
        return unitDisease;
    }
}
