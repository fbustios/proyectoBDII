package com.example.demo.Repositories;

import com.example.demo.Models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaQueryRepository extends JpaRepository<Cita,Integer> {
    @Query(value = "SELECT * FROM TABLE(test.conseguirCitasPorHacer)", nativeQuery = true)
    List<Cita> getToDoAppointments();

    @Query(value = "SELECT * FROM TABLE(test.conseguirCitasDisponibles)", nativeQuery = true)
    List<Cita> getFreeAppointments();


    @Query(value = "SELECT * FROM TABLE(test.conseguirCitasSinResolver)", nativeQuery = true)
    List<Cita> getUnresolvedAppointments();


    @Query(value = "SELECT * FROM TABLE(test.conseguirCitasPorFecha(:inicio, :fin))", nativeQuery = true)
    List<Cita> findByDate(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);


    //@Query()
    //List<Cita> getUserAppointments();
}
