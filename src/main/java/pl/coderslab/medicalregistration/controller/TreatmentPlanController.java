package pl.coderslab.medicalregistration.controller;

import com.google.gson.Gson;
import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.medicalregistration.entity.TreatmentPlan;
import pl.coderslab.medicalregistration.utils.PatientRepository;
import pl.coderslab.medicalregistration.utils.TreatmentPlanRepository;
import pl.coderslab.medicalregistration.utils.TreatmentPlanService;
import pl.coderslab.medicalregistration.utils.TreatmentStationRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TreatmentPlanController {
    private static String planInfo;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final TreatmentStationRepository treatmentStationRepository;
    private final PatientRepository patientRepository;
    private final TreatmentPlanService treatmentPlanService;

    public TreatmentPlanController(TreatmentPlanRepository treatmentPlanRepository, TreatmentStationRepository treatmentStationRepository, PatientRepository patientRepository, TreatmentPlanService treatmentPlanService) {
        this.treatmentPlanRepository = treatmentPlanRepository;
        this.treatmentStationRepository = treatmentStationRepository;
        this.patientRepository = patientRepository;
        this.treatmentPlanService = treatmentPlanService;
    }


    @GetMapping("/plans/getall")
    public String allPlans( HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        List<TreatmentPlan> treatmentPlanList = treatmentPlanRepository.findAll();
        List<TreatmentPlan> treatmentPlanList2 = treatmentPlanRepository.findAllByDate(LocalDate.now());

        return gson.toJson(treatmentPlanList.stream().flatMap(e -> Stream.of(e.getDate().toString(), e.getTime().toString(), e.getTreatmentStation().getNameStation(), e.getPatient().getFullName())).collect(Collectors.toList()));


    }

    @GetMapping("/plans/add")
    public String addPlans() {

        return "addplans-form";
    }

    @PostMapping("/plans/add")
    public String addPlansPost(@Valid TreatmentPlan treatmentPlan, BindingResult result, @Param("dayNumber") int dayNumber, HttpServletResponse resp, @Param("date") String date, @Param("time") String time) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        treatmentPlan.setDate(LocalDate.parse(date));
        treatmentPlan.setTime(LocalTime.parse(time));
        Gson gson = new Gson();
        if (treatmentPlanService.isSunday(treatmentPlan.getDate())) {

            List<String> listError1 = List.of("To niedziela!");
            return gson.toJson(listError1);
        }

        if (!treatmentPlanService.DateTimeChecker(treatmentPlan)) {

            List<String> listError2 = List.of("termin jest zajęty! Wybierz inny");
            return gson.toJson(listError2);
        }


        treatmentPlanRepository.save(treatmentPlan);
        if (dayNumber > 1) {
            planInfo = treatmentPlanService.automaticPlan(dayNumber, treatmentPlan);
        }

        List<String> listSave = List.of("Zapis wykonany", " ewentaulne błędy: ", planInfo);
        return gson.toJson(listSave);
    }

    @GetMapping("/get/date")
    Object getDateList(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        return gson.toJson(treatmentPlanService.getDateListService().stream().map(e -> e.toString()).collect(Collectors.toList()));
    }

    @GetMapping("/get/time")
    String getTimeList(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        return gson.toJson(treatmentPlanService.getTimeListService().stream().map(e -> e.toString()).collect(Collectors.toList()));
    }

    @GetMapping("/get/treatmentStation")
    String getTreatmentStationList(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        return gson.toJson(treatmentStationRepository.findAll());
    }

    @GetMapping("/get/patients")
    String getPatients(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        return gson.toJson(patientRepository.findAll());
    }

    @GetMapping("/check/dateTime")
    String frontEndCheckDateTime(@Param("date") String date, @Param("time") String time, @Param("idStation") Long idStation, @Param("idPatients") Long idPatients, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();

        List<String> freeHourList = treatmentPlanService.frontEndDateTimeChecker(LocalDate.parse(date), LocalTime.parse(time), idStation, idPatients);


        return gson.toJson(freeHourList);
    }


}