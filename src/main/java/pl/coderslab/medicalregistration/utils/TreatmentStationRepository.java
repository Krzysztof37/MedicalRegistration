package pl.coderslab.medicalregistration.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.medicalregistration.entity.TreatmentStation;

import java.util.List;

public interface TreatmentStationRepository extends JpaRepository<TreatmentStation, Long> {

    List<TreatmentStation> findByProcedureId(Long id);

}
