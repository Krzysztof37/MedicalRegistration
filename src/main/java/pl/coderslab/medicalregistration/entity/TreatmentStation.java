package pl.coderslab.medicalregistration.entity;


import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stations")
@Data
public class TreatmentStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Podaj nazwę kabinki")
    String nameStation;
    @NotNull(message = "Wybierz procedurę")
    @ManyToOne
    Procedure procedure;

    @Override
    public String toString() {
        return "Identyfikator kabinki: "+id+" "+nameStation+" "+procedure;
    }
}
