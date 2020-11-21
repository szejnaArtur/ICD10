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
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problem_id;

    @Column
    private String text;

    public static Problem of(String text) {
        Problem problem = new Problem();
        problem.setText(text);
        return problem;
    }
}