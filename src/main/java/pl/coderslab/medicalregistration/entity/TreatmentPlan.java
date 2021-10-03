package pl.coderslab.medicalregistration.entity;


import lombok.Data;
import pl.coderslab.medicalregistration.entity.Patient;
import pl.coderslab.medicalregistration.entity.TreatmentStation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class TreatmentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    Long id;
    LocalDate date;
    LocalTime time;
    @NotNull
    @ManyToOne
    Patient patient;
    @ManyToOne
    @NotNull
    TreatmentStation treatmentStation;



}
