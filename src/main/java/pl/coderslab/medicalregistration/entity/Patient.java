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
    @NotBlank(message = "wypełnij pole")
    @Size(min=2, message = "zbyt mało znaków")
    String name;
    @NotBlank(message = "wypełnij pole")
    @Size(min=2, message = "zbyt mało znaków")
    String surName;
    @NotBlank(message = "wypełnij pole")
    String email;
    @Size(min=3, message = "pacjent musi mieć minimum 3 procedury")
    @ManyToMany
    List<Procedure> procedure;

    @Override
    public String toString() {
        return "Pacjent: "+name+" "+surName;
    }
}
