package edu.ban7.club_canin_cda_2026.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.view.SeanceView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TypeCours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SeanceView.class)
    protected Integer id;

    @Column(length = 50, nullable = false, unique = true)
    @NotBlank
    @Length(min = 3, max = 50)
    @JsonView(SeanceView.class)
    protected String nom;

    @Column(columnDefinition = "TEXT")
    @JsonView(SeanceView.class)
    protected String description;

    @Min(value = 0, message = "L'âge minimum ne peut pas être négatif")
    @JsonView(SeanceView.class)
    protected Integer ageMin;

    @JsonView(SeanceView.class)
    protected Integer ageMax;

}
