package pl.coderslab.medicalregistration.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.medicalregistration.entity.Patient;
import pl.coderslab.medicalregistration.entity.Procedure;
import pl.coderslab.medicalregistration.utils.PatientRepository;
import pl.coderslab.medicalregistration.utils.ProcedureRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    private final PatientRepository patientRepository;
    private final ProcedureRepository procedureRepository;


    public PatientController(PatientRepository patientRepository, ProcedureRepository procedureRepository) {
        this.patientRepository = patientRepository;
        this.procedureRepository = procedureRepository;
    }

    @GetMapping("/")
    @ResponseBody
    public String welcomeText(){
        return "witaj w aplikacji";
    }

    @GetMapping("/patients/getall")
    public String allPatients(Model model){
        List<Patient> patientsList = patientRepository.findAll();
        model.addAttribute("patientsList", patientsList);

        return "allpatients";
    }

    @GetMapping("/patients/add")
     public String addPatients(Model model){
        model.addAttribute("patient", new Patient());

        return "addpatients-form";
    }
    @PostMapping("/patients/add")
    public String addPatientsPost(@Valid Patient patient, BindingResult result){
        if(result.hasErrors()){

            return "addpatients-form";
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
