package com.projet.controllers;

import com.projet.entities.Employe;
import com.projet.entities.Machine;
import com.projet.services.EmployeService;
import com.projet.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private MachineService machineService;
    // Ajouter ces méthodes dans votre MachineController

    @GetMapping("/listByEmployee")
    public String listMachinesByEmployee(@RequestParam(name = "employeeId", required = false) Long employeeId, Model model) {
        List<Machine> machines;
        if (employeeId != null) {
            machines = machineService.getMachinesByEmployee(employeeId);
        } else {
            machines = machineService.findAll();
        }
        List<Employe> employees = employeService.findAll();
        model.addAttribute("machines", machines);
        model.addAttribute("employees", employees);
        model.addAttribute("selectedEmployee", employeeId);

        return "machine/list";
    }

    @GetMapping("/listByDate")
    public String listMachinesByDate(@RequestParam(name = "startDate", required = false) String startDate,
                                     @RequestParam(name = "endDate", required = false) String endDate, Model model) {
        List<Machine> machines;
        if (startDate != null && endDate != null) {
            // Convertir les dates depuis la chaîne (vous devrez peut-être ajuster cela en fonction du format de date réel)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date start = sdf.parse(startDate);
                Date end = sdf.parse(endDate);
                machines = machineService.getMachinesBetweenDates(start, end);
            } catch (ParseException e) {
                // Gérer l'erreur de conversion de date si nécessaire
                machines = machineService.findAll();
            }
        } else {
            machines = machineService.findAll();
        }

        List<Employe> employees = employeService.findAll();
        model.addAttribute("machines", machines);
        model.addAttribute("employees", employees);
        model.addAttribute("selectedEmployee", null);

        return "machine/list";
    }


    @GetMapping("/list")
    public String listMachines(Model model) {
        List<Machine> machines = machineService.findAll();
        List<Employe> employees = employeService.findAll();
        model.addAttribute("machines", machines);
        model.addAttribute("employees", employees);
        model.addAttribute("selectedEmployee", null); // Ajout même s'il est nul
        return "machine/list";
    }

    @GetMapping("/form")
    public String showMachineForm(Model model) {
        model.addAttribute("machine", new Machine());
        model.addAttribute("employes", employeService.findAll());
        return "machine/form";
    }

    @PostMapping("/save")
    public String saveMachine(@ModelAttribute("machine") Machine machine) {
        machineService.create(machine);
        return "redirect:/machine/list";
    }

    @GetMapping("/edit/{id}")
    public String editMachine(@PathVariable Long id, Model model) {
        Machine machine = machineService.findById(id);
        model.addAttribute("machine", machine);
        model.addAttribute("employes", employeService.findAll());
        return "machine/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMachine(@PathVariable Long id) {
        Machine machine = machineService.findById(id);
        machineService.delete(machine);
        return "redirect:/machine/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });
    }
    @GetMapping("/acquisitionChart")
    @ResponseBody
    public Map<Integer, Long> getAcquisitionChartData() {
        return machineService.getMachineCountByYear();
    }


    @GetMapping("/showAcquisitionChart")
    public String showAcquisitionChart(Model model) {
        Map<Integer, Long> acquisitionData = machineService.getMachineCountByYear();
        model.addAttribute("acquisitionData", acquisitionData);
        return "machine/acquisitionChart";
    }

}
