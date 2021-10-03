package pl.coderslab.medicalregistration.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.medicalregistration.entity.TreatmentStation;

public interface TreatmentStationRepository extends JpaRepository<TreatmentStation, Long> {
}
