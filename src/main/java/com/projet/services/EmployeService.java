package com.projet.services;



import com.projet.entities.Employe;
import com.projet.idao.IDao;
import com.projet.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService implements IDao<Employe> {

    @Autowired
    private EmployeRepository employeRepository;
    @Override
    public Employe create(Employe employe) {
        return employeRepository.save(employe);
    }
    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }
    @Override
    public Employe update(Employe employe) {
        return employeRepository.save(employe);
    }
    @Override
    public boolean delete(Employe employe) {
        employeRepository.delete(employe);
        return true;
    }
    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id).orElse(null);
    }
}
