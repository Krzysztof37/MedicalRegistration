package pl.coderslab.medicalregistration.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.medicalregistration.entity.TreatmentPlan;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {

    List<TreatmentPlan> findAllByDate(LocalDate localDate);
    boolean existsTreatmentPlanByDateAndTimeAndTreatmentStationId(LocalDate localDate, LocalTime localTime, Long id);
    boolean existsTreatmentPlanByDateAndTimeAndPatientId(LocalDate localDate, LocalTime localTime, Long id);
}
