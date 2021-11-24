package pl.coderslab.medicalregistration.controller;


import com.google.gson.Gson;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.medicalregistration.entity.Procedure;
import pl.coderslab.medicalregistration.utils.ProcedureRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ProcedureController {

    private final ProcedureRepository procedureRepository;

    public ProcedureController(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }


    @GetMapping("/procedure/getall")
    public String allstations(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        List<Procedure> procedureList = procedureRepository.findAll();

        Gson gson = new Gson();
        return gson.toJson(procedureList);
    }


    @GetMapping("/procedure/add")
    public List<String> addstations() {
        return List.of("Metoda get");
    }

    @PostMapping("/procedure/add")
    public Object addstationsPost(@Valid Procedure procedure, BindingResult result, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        if (result.hasErrors()) {

            return result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage);
        } else {

            procedureRepository.save(procedure);
        }
        List<String> list = List.of("zapis wykonany");
        return gson.toJson(list);
    }


}
