package com.example.demo.Repositories;

import com.example.demo.Models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CitaManagementRepository extends JpaRepository<Cita, Integer> {
    @Procedure(procedureName = "test.crearCita")
    Integer createAppointment(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);


    //@Query()
    //int verifyNewAppointmentDates(LocalDate start, LocalDate end);
}
