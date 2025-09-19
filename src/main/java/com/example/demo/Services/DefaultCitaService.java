package com.example.demo.Services;

import com.example.demo.Models.Cita;
import com.example.demo.Models.Dia;
import com.example.demo.Repositories.CitaManagementRepository;
import com.example.demo.Repositories.CitaQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@Service
public class DefaultCitaService implements CitaService{
    CitaManagementRepository managementRepo;
    CitaQueryRepository queryRepo;

    DefaultCitaService(final CitaManagementRepository mRepo, final CitaQueryRepository qRepo){
        this.queryRepo = qRepo;
        this.managementRepo= mRepo;
    }

    @Override
    public int resolveAppointment(int cita_id, boolean action) {
        if(action) return managementRepo.resolveAppointment(cita_id,1);
        else return managementRepo.resolveAppointment(cita_id,0);
    }

    @Override
    public List<Cita> getAvailableAppointments() {
        return queryRepo.getFreeAppointments();
    }

    @Override
    public List<Cita> getToDoAppointments() {
        return queryRepo.getToDoAppointments();
    }

    @Override
    public List<Cita> getUnresolvedAppointments() {
        return queryRepo.getUnresolvedAppointments();
    }

    @Override
    public List<Cita> getAppointmentsByDate(final LocalDateTime date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        LocalDateTime inicio = LocalDateTime.of(year,month,day,0,0,0);
        LocalDateTime fin = LocalDateTime.of(year,month,day,23,59,55);
        return queryRepo.findByDate(inicio, fin);
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
    public int bookAppointment(final String username, final int service_id, final int date_id, MultipartFile file) {
        return managementRepo.bookAppointment(username,service_id,date_id,"ubi");
    }


    @Override
    @Transactional
    public int createAppointment(final LocalDateTime fechaInicio, final LocalDateTime fechaFin) {
        return managementRepo.createAppointment(fechaInicio, fechaFin);
    }

    @Override
    public int deleteAppointment(int cita_id) {
        return managementRepo.deleteAppointment(cita_id);
    }

    @Override
    @Transactional
    public void updateAppointmentDate() {

    }

    private void acceptAppointment(){}
}
