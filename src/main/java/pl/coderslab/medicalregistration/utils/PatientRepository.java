package pl.coderslab.medicalregistration.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.medicalregistration.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
