package com.projet.services;


import com.projet.entities.Machine;
import com.projet.idao.IDao;
import com.projet.repositories.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MachineService implements IDao<Machine> {

    @Autowired
    private MachineRepository machineRepository;

    @Override
    public Machine create(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    @Override
    public Machine update(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public boolean delete(Machine machine) {
        machineRepository.delete(machine);
        return true;
    }

    @Override
    public Machine findById(Long id) {

        return machineRepository.findById(id).orElse(null);
    }

    public List<Machine> getMachinesByEmployee(Long employeeId) {
        return machineRepository.findByEmployeId(employeeId);
    }

    public List<Machine> getMachinesSortedByDate() {
        return machineRepository.findAllByOrderByDateAchat();
    }

    // Dans MachineService.java
    public List<Machine> getMachinesBetweenDates(Date startDate, Date endDate) {
        return machineRepository.findByDateAchatBetween(startDate, endDate);
    }
    public Map<Integer, Long> getMachineCountByYear() {
        List<Machine> machines = machineRepository.findAll();
        Map<Integer, Long> countByYear = machines.stream()
                .collect(Collectors.groupingBy(machine -> machine.getDateAchat().getYear() + 1900, Collectors.counting()));
        return countByYear;
    }

}
