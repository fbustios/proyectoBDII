package com.example.demo.Services;

import com.example.demo.Models.Cita;
import com.example.demo.Models.Dia;
import com.example.demo.Repositories.CitaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@Service
public class DefaultCitaService implements CitaService{
    CitaRepository citaRepo;

    DefaultCitaService(CitaRepository citaRepo){
        this.citaRepo = citaRepo;
    }

    @Override
    public void resolveAppointment(boolean action) {

    }

    @Override
    public List<Cita> getAvailableAppointments() {
        return citaRepo.getCitaByConfirmadaIsFalseAndPendienteIsFalse();
    }

    @Override
    public List<Cita> getConfirmedAndPendingAppointments() {
        return citaRepo.getCitaByConfirmadaIsTrueAndPendienteIsTrue();
    }

    @Override
    public List<Cita> getAppointmentsByDate(LocalDateTime date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        LocalDateTime inicio = LocalDateTime.of(year,month,day,0,0,0);
        LocalDateTime fin = LocalDateTime.of(year,month,day,23,59,55);
        return citaRepo.findByDate(inicio, fin);
    }

    @Override
    public Dia[] getDaysAssignedWithDates() {
        int currentYear = Year.now().getValue();
        int currentMonth = YearMonth.now().getMonthValue();
        int monthLength = YearMonth.now().lengthOfMonth();
        Dia[] dias = new Dia[monthLength];
        for (int i = 0; i < monthLength; i++) {
            LocalDateTime date = LocalDateTime.of(currentYear,currentMonth,i+1,12,0);
            boolean status = (getAppointmentsByDate(date).toArray().length != 0);
            Dia dia = new Dia(date,status);
            dias[i] = dia;
        }
        return dias;
    }


    @Override
    @Transactional
    public void bookAppointment(String username, int service_id, int date_id) {

    }


    @Override
    @Transactional
    public void createAppointment(final LocalDateTime fechaInicio, final LocalDateTime fechaFin) {

        if(fechaInicio.isAfter(fechaFin)){
            System.out.println("hola");
            return;
        }
        Cita newCita = new Cita(fechaInicio, fechaFin);
        System.out.println(newCita.toString());
        citaRepo.save(newCita);

    }

    @Override
    @Transactional
    public void updateAppointmentDate() {

    }
}
