package com.example.demo.Services;
import com.example.demo.Models.Cita;
import com.example.demo.Models.Dia;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {
    List<Cita> getAvailableAppointments();
    List<Cita> getToDoAppointments();
    List<Cita> getUnresolvedAppointments();
    List<Cita> getAppointmentsByDate(LocalDateTime localDateTime);
    Dia[] getDaysAssignedWithDates();

    //separar
    int resolveAppointment(int cita_id, boolean action);
    int bookAppointment(String username, int service_id, int date_id, MultipartFile file);
    int createAppointment(final LocalDateTime fechaInicio, final LocalDateTime fechaFin);
    int deleteAppointment(final int cita_id);
    void updateAppointmentDate();
}
