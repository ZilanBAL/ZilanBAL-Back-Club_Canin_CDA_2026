package edu.ban7.club_canin_cda_2026.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.view.AppPersonneView;
import edu.ban7.club_canin_cda_2026.view.ChienView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Chien {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView({ChienView.class, AppPersonneView.class})
    protected Integer id;

    @Column(length = 50, nullable = false)
    @NotBlank
    @Length(min=3, max=50)
    @JsonView({ChienView.class, AppPersonneView.class})
    protected String nom;

    @Column(nullable = false)
    @NotNull
    @JsonView(ChienView.class)
    protected LocalDate dateNaissance;

    @Column(unique = true)
    @JsonView(ChienView.class)
    protected String matricule;

    @JsonView(ChienView.class)
    protected Double taille;

    @JsonView(ChienView.class)
    protected Double poid;

    // Relation avec Sexe : un chien a exactement un sexe
    // @ManyToOne : plusieurs chiens peuvent avoir le même sexe
    @ManyToOne
    @NotNull(message = "Le sexe est obligatoire")
    @JsonView(ChienView.class)
    protected Sexe sexe;

    // Relation avec Race : un chien appartient à une race
    @ManyToOne
    @NotNull(message = "La race est obligatoire")
    @JsonView(ChienView.class)
    protected Race race;

    // 1 chien = 1 propriétaire
    // @ManyToOne : un propriétaire peut avoir plusieurs chiens
    @ManyToOne
    @NotNull(message = "Le propriétaire est obligatoire")
    @JsonView(ChienView.class)
    protected AppPersonne proprietaire;


    // @JoinTable : crée une table intermédiaire "chien_competence" en base de données
    // inverseJoinColumns → colonne qui pointe vers Compétence
    @ManyToMany
    @JoinTable(
            name = "chien_competence",
            joinColumns = @JoinColumn(name = "chien_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )

    @JsonView(ChienView.class)
    protected List<Competence> competences = new ArrayList<>();


}
