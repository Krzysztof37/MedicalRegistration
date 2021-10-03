package pl.coderslab.medicalregistration.utils;


import org.springframework.stereotype.Service;
import pl.coderslab.medicalregistration.entity.TreatmentPlan;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentPlanService {

    private final TreatmentPlanRepository treatmentPlanRepository;

    public TreatmentPlanService(TreatmentPlanRepository treatmentPlanRepository) {
        this.treatmentPlanRepository = treatmentPlanRepository;
    }

    public boolean DateTimeChecker(TreatmentPlan treatmentPlan){

    List<TreatmentPlan> treatmentPlanList = treatmentPlanRepository.findAll();
    for(TreatmentPlan treatmentPlanLoop : treatmentPlanList){
        if(treatmentPlan.getDate().equals(treatmentPlanLoop.getDate())&&treatmentPlan.getTime().equals(treatmentPlanLoop.getTime())&&
                treatmentPlan.getTreatmentStation().getId().equals(treatmentPlanLoop.getTreatmentStation().getId())){

            return false;
        }
    }
    return true;
}

public List<LocalDate> getDateListService(){
    List<LocalDate> dateList = new ArrayList<>();
    LocalDate actualDate = LocalDate.now();
    dateList.add(actualDate);
    for(int i = 0; i<30; i++){
        actualDate =  actualDate.plusDays(1L);
        dateList.add(actualDate);
    }
return dateList;
}
public List<LocalTime> getTimeListService(){
    List<LocalTime> timeList = new ArrayList<>();
    LocalTime timeStart = LocalTime.of(8,30,00);

    for(int i = 0; i<13;i++){

        timeList.add(timeStart);
        timeStart = timeStart.plusMinutes(30);

    }
    return timeList;
}

}
