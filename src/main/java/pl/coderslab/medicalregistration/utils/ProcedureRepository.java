package pl.coderslab.medicalregistration.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.medicalregistration.entity.Procedure;

import java.util.List;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {




}
