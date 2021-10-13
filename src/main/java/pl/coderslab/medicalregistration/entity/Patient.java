package pl.coderslab.medicalregistration.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "wypełnij pole imię")
    @Size(min=2, message = "imię - zbyt mało znaków")
    String name;
    @NotBlank(message = "wypełnij pole nazwisko")
    @Size(min=2, message = "nazwisko - zbyt mało znaków")
    String surName;
    @NotBlank(message = "wypełnij pole email")
    String email;
    @NotNull(message = "pacjent musi mieć minimum 1 procedurę")
    @ManyToMany
    List<Procedure> procedure;

    @Override
    public String toString() {
        return "Pacjent: "+name+" "+surName;
    }
}
