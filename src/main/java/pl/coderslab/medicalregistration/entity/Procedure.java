package pl.coderslab.medicalregistration.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "procedures")
@Data
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String procedureName;
    @NotBlank
    String procedureTime;

    @Override
    public String toString() {
        return "Nazwa procedury: "+ procedureName+" Czas procedury: "+procedureTime;
    }
}
