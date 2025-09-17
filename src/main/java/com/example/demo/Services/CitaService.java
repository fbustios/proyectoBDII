package com.example.demo.Services;
import com.example.demo.Models.Cita;
import com.example.demo.Models.Dia;


import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {
    List<Cita> getAvailableAppointments();
    List<Cita> getConfirmedAndPendingAppointments();
    List<Cita> getAppointmentsByDate(LocalDateTime localDateTime);
    Dia[] getDaysAssignedWithDates();
    void resolveAppointment(boolean action);
    void bookAppointment(String username,int service_id, int date_id);
    void createAppointment(final LocalDateTime fechaInicio, final LocalDateTime fechaFin);
    void updateAppointmentDate();
}
