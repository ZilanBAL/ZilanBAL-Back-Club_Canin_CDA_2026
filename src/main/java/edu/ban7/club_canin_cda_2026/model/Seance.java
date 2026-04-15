package edu.ban7.club_canin_cda_2026.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.view.AppPersonneView;
import edu.ban7.club_canin_cda_2026.view.SeanceView;
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
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SeanceView.class)
    protected Integer id;

    @Column(nullable = false)
    @NotBlank
    @JsonView({SeanceView.class, AppPersonneView.class})
    protected String nom;

    @Column(nullable = false)
    @NotNull
    @JsonView({SeanceView.class, AppPersonneView.class})
    protected LocalDate date;

    @JsonView({SeanceView.class, AppPersonneView.class})
    private LocalTime heureDebut;

    // Durée en minutes (ex: 60 pour 1h)
    @Min(value = 1, message = "La durée doit être d'au moins 1 minute")
    @JsonView({SeanceView.class, AppPersonneView.class})
    private Integer duree;

    //pas sûr ??
    @Min(value = 1, message = "Il doit y avoir au moins 1 participant possible")
    @JsonView(SeanceView.class)
    protected Integer nbParticipantMax;

    //@ManyToOne : une séance appartient à un type de cours précis
    @ManyToOne
    @NotNull(message = "Le type de cours est obligatoire")
    @JsonView(SeanceView.class)
    protected TypeCours typeCours;

    // @ManyToOne : un coach peut programmer plusieurs séances,
    // mais une séance est par un seul coach
    @ManyToOne
    @JsonView(SeanceView.class)
    protected AppPersonne coach;

}
