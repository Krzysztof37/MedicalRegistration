package pl.coderslab.medicalregistration.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.medicalregistration.entity.Patient;
import pl.coderslab.medicalregistration.entity.Procedure;
import pl.coderslab.medicalregistration.utils.ProcedureRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProcedureController {

    private final ProcedureRepository procedureRepository;

    public ProcedureController(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }


    @GetMapping("/procedure/getall")
    public String allstations(Model model) {
        List<Procedure> procedureList = procedureRepository.findAll();
        model.addAttribute("procedureList", procedureList);

        return "allprocedure";
    }

    @GetMapping("/procedure/add")
    public String addstations(Model model) {
        model.addAttribute("procedure", new Procedure());

        return "addprocedure-form";
    }

    @PostMapping("/procedure/add")
    public String addstationsPost(@Valid Procedure procedure, BindingResult result) {
        if (result.hasErrors()) {
            return "addprocedure-form";
        } else {
            procedureRepository.save(procedure);
        }
        return "redirect:/procedure/getall";
    }


}
