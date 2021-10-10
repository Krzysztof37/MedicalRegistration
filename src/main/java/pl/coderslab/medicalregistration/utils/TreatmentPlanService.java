package pl.coderslab.medicalregistration.utils;


import org.springframework.stereotype.Service;
import pl.coderslab.medicalregistration.controller.TreatmentPlanController;
import pl.coderslab.medicalregistration.entity.TreatmentPlan;

import java.time.DayOfWeek;
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
        if(treatmentPlanRepository.existsTreatmentPlanByDateAndTimeAndTreatmentStationId(treatmentPlan.getDate(),treatmentPlan.getTime(),treatmentPlan.getTreatmentStation().getId())
                || treatmentPlanRepository.existsTreatmentPlanByDateAndTimeAndPatientId(treatmentPlan.getDate(),treatmentPlan.getTime(), treatmentPlan.getPatient().getId())){
            return false;
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

public String automaticPlan(int dayNumber, TreatmentPlan treatmentPlan){
        String planInfo = "";
        LocalTime endTime = LocalTime.of(15,00);
        LocalTime checkTime = LocalTime.of(14,30);
        Long days = 1L;
        for(int i = 0; i< dayNumber-1; i++){
            TreatmentPlan treatmentPlanNew = new TreatmentPlan();
            treatmentPlanNew.setDate(treatmentPlan.getDate());
            treatmentPlanNew.setDate(treatmentPlanNew.getDate().plusDays(days));
            treatmentPlanNew.setTime(treatmentPlan.getTime());
            treatmentPlanNew.setTreatmentStation(treatmentPlan.getTreatmentStation());
            treatmentPlanNew.setPatient(treatmentPlan.getPatient());
            if(isSunday(treatmentPlanNew.getDate())) {treatmentPlanNew.setDate(treatmentPlanNew.getDate().plusDays(1L));
            days++;}
            boolean checkAllDay = true;
            boolean isSaved = false;
            while(treatmentPlanNew.getTime().isBefore(endTime) ){
                if(!treatmentPlanRepository.existsTreatmentPlanByDateAndTimeAndTreatmentStationId(treatmentPlanNew.getDate(),treatmentPlanNew.getTime(),treatmentPlanNew.getTreatmentStation().getId())){
                    if(!treatmentPlanRepository.existsTreatmentPlanByDateAndTimeAndPatientId(treatmentPlanNew.getDate(),treatmentPlanNew.getTime(), treatmentPlanNew.getPatient().getId()))
                    {
                        treatmentPlanRepository.save(treatmentPlanNew);
                        isSaved = true;
                        break;
                    }
           }
           treatmentPlanNew.setTime(treatmentPlanNew.getTime().plusMinutes(30));
           if(treatmentPlanNew.getTime().equals(checkTime) && checkAllDay){
               checkAllDay = false;
               treatmentPlanNew.setTime(LocalTime.of(8,30));
           }
        }
        if(!isSaved){
            planInfo = planInfo+" "+treatmentPlanNew.getDate();
        }
        days++;
        }

return planInfo;
}

public boolean isSunday(LocalDate localDate){
        if(localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            return true;
        }
        return false;
}


}
