package com.projet.controllers;


import com.projet.entities.Employe;
import com.projet.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/employe")
public class EmployeController {
    @Autowired
    private EmployeService employeService;
    @GetMapping("/list")
    public String listEmployes(Model model) {
        List<Employe> employes = employeService.findAll();
        model.addAttribute("employes", employes);
        return "employe/list";
    }
    @GetMapping("/form")
    public String showEmployeForm(Model model) {
        model.addAttribute("employe", new Employe());
        return "employe/form";
    }
    @PostMapping("/save")
    public String saveEmploye(@ModelAttribute("employe") Employe employe) {
        employeService.create(employe);
        return "redirect:/employe/list";
    }
    @GetMapping("/edit/{id}")
    public String editEmploye(@PathVariable Long id, Model model) {
        Employe employe = employeService.findById(id);
        model.addAttribute("employe", employe);
        return "employe/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmploye(@PathVariable Long id) {
        Employe employe = employeService.findById(id);
        employeService.delete(employe);
        return "redirect:/employe/list";
    }
}
