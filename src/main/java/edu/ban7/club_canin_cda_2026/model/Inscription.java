package edu.ban7.club_canin_cda_2026.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.view.SeanceView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscription {

    // Vérifier le cours
    // Classe interne représentant la clé primaire composée (chienId + seanceId)
    // @Embeddable : cette classe est intégrée dans l'entité parente (pas de table propre)
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Key implements Serializable {

        @Column(name = "chien_id")
        Integer chienId;

        @Column(name = "seance_id")
        Integer seanceId;
    }

    // @EmbeddedId : indique que la clé primaire est un objet composé (la classe Key)
    @EmbeddedId
    private Key id;

    //  ce champ au "chienId" déclaré dans Key
    @ManyToOne
    @MapsId("chienId")
    @JoinColumn(name = "chien_id")
    @JsonView(SeanceView.class)
    protected Chien chien;


    @ManyToOne
    @MapsId("seanceId")
    @JoinColumn(name = "seance_id")
    @JsonView(SeanceView.class)
    protected Seance seance;


    @JsonView(SeanceView.class)
    protected LocalDate dateInscription;


    // true = présent en séance, false = absent
    @Column(nullable = false)
    @JsonView(SeanceView.class)
    private boolean statut = false;



}
