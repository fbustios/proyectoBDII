package com.example.demo.Repositories;

import com.example.demo.Models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

public interface CitaManagementRepository extends JpaRepository<Cita, Integer> {
    @Procedure(procedureName = "test.crearCita")
    Integer createAppointment(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    @Procedure(procedureName = "test.asignarCita")
    Integer bookAppointment(@Param("p_username") String username, @Param("p_servicio_id") int servicio_id,
                            @Param("p_cita_id") int cita_id, @Param("ubicacion_imagen") String ubicacion_imagen);

    @Procedure(procedureName = "test.resolverCita")
    Integer resolveAppointment(@Param("p_cita_id") int cita_id, @Param("decision") int decision);

    @Procedure(procedureName = "test.eliminarCita")
    Integer deleteAppointment(@Param("p_cita_id") int cita_id);
    //@Query()
    //int verifyNewAppointmentDates(LocalDate start, LocalDate end);
}
