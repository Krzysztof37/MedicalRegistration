package pl.coderslab.medicalregistration.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.medicalregistration.entity.Patient;
import pl.coderslab.medicalregistration.entity.Procedure;
import pl.coderslab.medicalregistration.utils.PatientRepository;
import pl.coderslab.medicalregistration.utils.ProcedureRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PatientController {

    private final PatientRepository patientRepository;
    private final ProcedureRepository procedureRepository;


    public PatientController(PatientRepository patientRepository, ProcedureRepository procedureRepository) {
        this.patientRepository = patientRepository;
        this.procedureRepository = procedureRepository;
    }

    @GetMapping(value = "/patients/getall")
    public String allPatients(Model model, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        List<Patient> patientsList = patientRepository.findAll();
        Gson gson = new Gson();

        return gson.toJson(patientsList);
    }

    @GetMapping("/patients/add")
     public String addPatients(HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Patient patient = new Patient();
        Gson gson = new Gson();
        return gson.toJson(procedureRepository.findAll());
    }
    @PostMapping("/patients/add")
    public String addPatientsPost(@Valid Patient patient, BindingResult result, HttpServletResponse resp, HttpServletRequest req){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        if(result.hasErrors()){
            String referer = req.getHeader("Referer");
            return "Wystąpiły błędy  <a href='file:///home/krzysztof/Pulpit/frontendprojekt/front-end/patientsAdd.html'>Powrót</a>";
        }else{
            patientRepository.save(patient);
        }
        return "redirect:/patients/getall";
    }

    @ModelAttribute("procedures")
    List<Procedure> getProceduresList(){
        return procedureRepository.findAll();

    }


}
