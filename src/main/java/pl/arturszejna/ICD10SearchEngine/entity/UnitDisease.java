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
public class UnitDisease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_disease_id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "main_disease_id")
    private MainDisease mainDisease;

}
