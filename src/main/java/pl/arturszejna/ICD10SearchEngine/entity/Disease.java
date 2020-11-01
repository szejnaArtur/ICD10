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
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disease_id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    public static Disease of(String code, String name){
        Disease disease = new Disease();
        disease.setCode(code);
        disease.setName(name);
        return disease;
    }

}
