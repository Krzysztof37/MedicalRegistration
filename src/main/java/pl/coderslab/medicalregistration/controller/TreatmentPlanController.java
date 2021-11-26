package pl.coderslab.medicalregistration.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.medicalregistration.entity.Patient;
import pl.coderslab.medicalregistration.entity.TreatmentPlan;
import pl.coderslab.medicalregistration.entity.TreatmentStation;
import pl.coderslab.medicalregistration.utils.PatientRepository;
import pl.coderslab.medicalregistration.utils.TreatmentPlanRepository;
import pl.coderslab.medicalregistration.utils.TreatmentPlanService;
import pl.coderslab.medicalregistration.utils.TreatmentStationRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
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
    public String allPlans(Model model) {
        List<TreatmentPlan> treatmentPlanList = treatmentPlanRepository.findAll();
        List<TreatmentPlan> treatmentPlanList2 = treatmentPlanRepository.findAllByDate(LocalDate.now());
        model.addAttribute("treatmentPlanList", treatmentPlanList);
        model.addAttribute("treatmentPlanList2", treatmentPlanList2);
        model.addAttribute("planInfo", planInfo);
        return "allplans";
    }

    @GetMapping("/plans/add")
    public String addPlans(Model model) {
        model.addAttribute("treatmentPlan", new TreatmentPlan());

        return "addplans-form";
    }

    @PostMapping("/plans/add")
    public String addPlansPost(@Valid TreatmentPlan treatmentPlan, BindingResult result, Model model, @Param("dayNumber") int dayNumber) {
        if (treatmentPlanService.isSunday(treatmentPlan.getDate())) {

            model.addAttribute("sundayError", "To niedziela!");
            return "addplans-form";

        }

        if (!treatmentPlanService.DateTimeChecker(treatmentPlan)) {

            model.addAttribute("uniqueError", "termin jest zajęty! Wybierz inny");

            return "addplans-form";

        }

        if (result.hasErrors()) {

            return "addplans-form";

        } else {

            treatmentPlanRepository.save(treatmentPlan);

            if (dayNumber > 1) {

                planInfo = treatmentPlanService.automaticPlan(dayNumber, treatmentPlan);
            }
        }
        return "redirect:/plans/getall";
    }

    @ModelAttribute("date")
    List<LocalDate> getDateList() {
        return treatmentPlanService.getDateListService();
    }

    @ModelAttribute("time")
    List<LocalTime> getTimeList() {
        return treatmentPlanService.getTimeListService();
    }

    @ModelAttribute("treatmentStation")
    List<TreatmentStation> getTreatmentStationList() {
        return treatmentStationRepository.findAll();
    }

    @ModelAttribute("patients")
    List<Patient> getPatients() {
        return patientRepository.findAll();
    }

}
