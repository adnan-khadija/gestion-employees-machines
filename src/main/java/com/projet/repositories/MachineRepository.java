package com.projet.repositories;


import com.projet.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    List<Machine> findByEmployeId(Long employeeId);
    List<Machine> findAllByOrderByDateAchat();
    // Dans MachineRepository.java
    List<Machine> findByDateAchatBetween(Date startDate, Date endDate);


}
