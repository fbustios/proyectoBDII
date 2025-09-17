package com.example.demo.Repositories;

import com.example.demo.Models.Cita;
import com.example.demo.Models.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> getCitaByConfirmadaIsTrueAndPendienteIsTrue();

    List<Cita> getCitaByConfirmadaIsFalseAndPendienteIsFalse();

    @Query("SELECT c FROM Cita c WHERE c.fecha_inicio >= :inicio AND c.fecha_inicio < :fin AND c.confirmada = false")
    List<Cita> findByDate(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);


    //@Query()
    //List<Cita> getUserAppointments();

    //@Query()
    //int verifyNewAppointmentDates(LocalDate start, LocalDate end);
}
