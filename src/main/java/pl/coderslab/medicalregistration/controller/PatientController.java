package pl.coderslab.medicalregistration.controller;


import com.google.gson.Gson;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.medicalregistration.entity.Patient;
import pl.coderslab.medicalregistration.entity.Procedure;
import pl.coderslab.medicalregistration.utils.PatientRepository;
import pl.coderslab.medicalregistration.utils.ProcedureRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
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
    public String allPatients(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        List<Patient> patientsList = patientRepository.findAll();
        Gson gson = new Gson();

        return gson.toJson(patientsList);
    }

    @GetMapping("/patients/add")
    public String addPatients(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");

        Gson gson = new Gson();
        return gson.toJson(procedureRepository.findAll());
    }

    @PostMapping("/patients/add")
    public Object addPatientsPost(@Valid Patient patient, BindingResult result, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();

        if (result.hasErrors()) {
            return result.getFieldErrors().stream().map(e -> e.getDefaultMessage());

        } else {

            patientRepository.save(patient);

        }
        List<String> list = List.of("zapis wykonany");
        return gson.toJson(list);
    }

    @GetMapping("/patients/getone")
    public String getOnePatient(@Param("patientId") Long patientId, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Patient patient = patientRepository.getById(patientId);
        Gson gson = new Gson();
        List<Procedure> list = patient.getProcedure();

        return gson.toJson(list);

    }

    @GetMapping("/patients/delete")
    public void patientsDelete(@Param("patientId") Long patientId, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        patientRepository.deleteById(patientId);
    }


}
