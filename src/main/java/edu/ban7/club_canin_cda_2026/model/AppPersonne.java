package edu.ban7.club_canin_cda_2026.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.club_canin_cda_2026.view.AppPersonneView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

public class AppPersonne {

    public interface OnCreate {}
    public interface OnUpdate {}


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView(AppPersonneView.class)
    protected Integer id;

    @Column(length = 50, nullable = false)
    @NotBlank(groups = {OnCreate.class}, message = "Le nom ne peut pas être vide")
    @Length(min=3, max=50)
    @JsonView(AppPersonneView.class)
    protected String nom;

    @Column(nullable = false)
    @NotBlank(groups = {OnCreate.class}, message = "Le prénom ne peut pas être vide")
    @JsonView(AppPersonneView.class)
    protected String prenom;

    @Column(nullable = false)
    @NotNull
    @JsonView(AppPersonneView.class)
    protected LocalDate dateNaissance;

    @Column(length = 15)
    @JsonView(AppPersonneView.class)
    protected String numero;

    @Column(nullable = false, unique = true)
    @NotBlank(groups = {OnCreate.class}, message = "L'email ne peut pas être vide")
    @Email(groups = {OnCreate.class}, message = "L'email est mal formé")
    @JsonView(AppPersonneView.class)
    protected String mail;

    @Column(nullable = false)
    @NotBlank(groups = {OnCreate.class}, message = "Le mot de passe ne peut pas être vide")
    protected String motDePasse;


    @ManyToOne
    @JsonView(edu.ban7.club_canin_cda_2026.view.AppPersonneView.class)
    protected Role role;


    @OneToMany(mappedBy = "proprietaire")
    @JsonView(edu.ban7.club_canin_cda_2026.view.AppPersonneView.class)
    List<Chien> chiens = new ArrayList<>();


    @OneToMany(mappedBy = "coach")
    @JsonView(edu.ban7.club_canin_cda_2026.view.AppPersonneView.class)
    List<Seance> seances = new ArrayList<>();


}
