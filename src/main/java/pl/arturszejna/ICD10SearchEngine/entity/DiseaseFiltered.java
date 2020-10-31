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
public class DiseaseFiltered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disease_id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String description;

}
