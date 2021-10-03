package pl.coderslab.medicalregistration.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.medicalregistration.entity.Procedure;
import pl.coderslab.medicalregistration.entity.TreatmentStation;
import pl.coderslab.medicalregistration.utils.ProcedureRepository;
import pl.coderslab.medicalregistration.utils.TreatmentStationRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TreatmentStationController {

    private final TreatmentStationRepository treatmentStationRepository;
    private final ProcedureRepository procedureRepository;

    public TreatmentStationController(TreatmentStationRepository treatmentStationRepository, ProcedureRepository procedureRepository) {
        this.treatmentStationRepository = treatmentStationRepository;
        this.procedureRepository = procedureRepository;
    }


    @GetMapping("/stations/getall")
    public String allStations(Model model){
        List<TreatmentStation> stationsList = treatmentStationRepository.findAll();
        model.addAttribute("stationsList", stationsList);

        return "allstations";
    }

    @GetMapping("/stations/add")
    public String addStations(Model model){
        model.addAttribute("treatmentStation", new TreatmentStation());

        return "addstations-form";
    }
    @PostMapping("/stations/add")
    public String addStationsPost(@Valid TreatmentStation treatmentStation, BindingResult result){
        if(result.hasErrors()){

            return "addstations-form";
        }else{
            treatmentStationRepository.save(treatmentStation);
        }
        return "redirect:/stations/getall";
    }
    @ModelAttribute("procedures")
    List<Procedure> getProceduresList() {
        return procedureRepository.findAll();
    }
}
