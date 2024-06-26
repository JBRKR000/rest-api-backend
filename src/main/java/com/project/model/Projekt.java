package com.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="projekt")
public class Projekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="projekt_id")
    private Integer projektId;

    @NotBlank(message = "Pole nazwa nie może być puste!")
    @Size(min = 3, max = 50, message = "Pole nazwa musi zawierać od {min} do {max} znaków!")
    @Column(nullable = false, length = 50)
    private String nazwa;

    @Column(length = 1000)
    private String opis;

    @CreationTimestamp
    @Column(name = "dataczas_utworzenia", nullable = false, updatable = false)
    private LocalDateTime dataCzasUtworzenia;

    @UpdateTimestamp
    @Column(name = "dataczas_modyfikacji", nullable = false)
    private LocalDateTime dataCzasModyfikacji;

    @OneToMany(mappedBy = "projekt")
    @JsonIgnoreProperties({"projekt"})
    private List<Zadanie> zadania;

    @ManyToMany
    @JoinTable(name = "projekt_student",
            joinColumns = {@JoinColumn(name = "projekt_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> studenci;

    public Projekt() {}

    public Projekt(String nazwa, String opis) {
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public Projekt(String nazwa, String opis, LocalDate dataUtworzenia) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataCzasUtworzenia = dataUtworzenia.atStartOfDay();
        this.dataCzasModyfikacji = LocalDateTime.now();
    }

    public Projekt(Integer projektId, String nazwa, String opis, LocalDate dataUtworzenia) {
        this.projektId = projektId;
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataCzasUtworzenia = dataUtworzenia.atStartOfDay();
        this.dataCzasModyfikacji = LocalDateTime.now();
    }

    public Projekt(int i, String nazwa2, String opis2, LocalDateTime now, LocalDate of) {
    }

    public Integer getProjektId() {
        return projektId;
    }

    public void setProjektId(Integer projektId) {
        this.projektId = projektId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataCzasUtworzenia() {
        return dataCzasUtworzenia;
    }

    public void setDataCzasUtworzenia(LocalDateTime dataCzasUtworzenia) {
        this.dataCzasUtworzenia = dataCzasUtworzenia;
    }

    public LocalDateTime getDataCzasModyfikacji() {
        return dataCzasModyfikacji;
    }

    public void setDataCzasModyfikacji(LocalDateTime dataCzasModyfikacji) {
        this.dataCzasModyfikacji = dataCzasModyfikacji;
    }

    public List<Zadanie> getZadania() {
        return zadania;
    }

    public void setZadania(List<Zadanie> zadania) {
        this.zadania = zadania;
    }

    public Set<Student> getStudenci() {
        return studenci;
    }

    public void setStudenci(Set<Student> studenci) {
        this.studenci = studenci;
    }
}
