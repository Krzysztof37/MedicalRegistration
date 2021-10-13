package pl.coderslab.medicalregistration.controller;


import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.medicalregistration.entity.Procedure;
import pl.coderslab.medicalregistration.entity.TreatmentStation;
import pl.coderslab.medicalregistration.utils.ProcedureRepository;
import pl.coderslab.medicalregistration.utils.TreatmentStationRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class TreatmentStationController {

    private final TreatmentStationRepository treatmentStationRepository;
    private final ProcedureRepository procedureRepository;

    public TreatmentStationController(TreatmentStationRepository treatmentStationRepository, ProcedureRepository procedureRepository) {
        this.treatmentStationRepository = treatmentStationRepository;
        this.procedureRepository = procedureRepository;
    }


    @GetMapping("/stations/getall")
    public String allStations(HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        List<TreatmentStation> stationsList = treatmentStationRepository.findAll();
        Gson gson = new Gson();

        return gson.toJson(stationsList);
    }

    @GetMapping("/stations/add")
    public String addStations(HttpServletResponse resp){

        resp.setHeader("Access-Control-Allow-Origin", "*");
        List<Procedure> newList = procedureRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(newList);
    }


    @PostMapping("/stations/add")
    public Object addStationsPost(@Valid TreatmentStation treatmentStation, BindingResult result, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        if(result.hasErrors()){
       return result.getFieldErrors().stream().map(e -> e.getDefaultMessage());

        }else{
            treatmentStationRepository.save(treatmentStation);
        }
        List<String> list = List.of("zapis wykonany");
        return gson.toJson(list);
    }
    @ModelAttribute("procedures")
    List<Procedure> getProceduresList() {
        return procedureRepository.findAll();
    }
}
