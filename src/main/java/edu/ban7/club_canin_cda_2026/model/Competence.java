package edu.ban7.club_canin_cda_2026.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.view.ChienView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Competence {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView(ChienView.class)
    protected Integer id;

    @Column(length = 50, nullable = false, unique = true)
    @NotBlank
    @Length(min=3, max=50)
    @JsonView(ChienView.class)
    protected String nom;

    @ManyToMany(mappedBy = "competencesNecessaires")
    private List<TypeCours> typesCours;

}
