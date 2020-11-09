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
public class MainDisease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long main_disease_id;

    @Column
    private String code;

    @Column
    private String name;

    @OneToMany(mappedBy = "mainDisease", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<MainDiseaseDescription> descriptions;

//    @Column
//    private String it_includes;
//
//    @Column
//    private String it_does_not_includes;
//
//    @Column
//    private String warning;

    @OneToMany(mappedBy = "mainDisease", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<UnitDisease> unitDiseases;

    public static MainDisease of(String code, String name){
        MainDisease mainDisease = new MainDisease();
        mainDisease.setCode(code);
        mainDisease.setName(name);
        return mainDisease;
    }

}
