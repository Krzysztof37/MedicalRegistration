package pl.coderslab.medicalregistration.controller;


import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.medicalregistration.entity.Patient;
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
    public String allstations(Model model, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        List<Procedure> procedureList = procedureRepository.findAll();
        model.addAttribute("procedureList", procedureList);
        Gson gson = new Gson();
        String newList = gson.toJson(procedureList);
        return newList;
    }

    @GetMapping("/procedure/add")
    public String addstations(Model model){
        model.addAttribute("procedure", new Procedure());

        return "addprocedure-form";
    }
    @PostMapping("/procedure/add")
    public String addstationsPost(@Valid Procedure procedure, BindingResult result){
        if(result.hasErrors()){
            return "addprocedure-form";
        }else{
            procedureRepository.save(procedure);
        }
        return "redirect:/procedure/getall";
    }



}
