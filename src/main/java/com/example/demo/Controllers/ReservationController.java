package com.example.demo.Controllers;


import com.example.demo.Models.Cita;
import com.example.demo.Models.Dia;
import com.example.demo.Services.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;


@Controller
public final class ReservationController {
    CitaService citaService;

    ReservationController(CitaService citaService){
        this.citaService = citaService;
    }

    @GetMapping("/home/calendar")
    public String showCalendarPage(Model model) {
        Dia[] days = citaService.getDaysAssignedWithDates();
        model.addAttribute("diasMes", days);
        return "CalendarPage";

    }

    @GetMapping("/home/servicios")
    public String showServices(){
        return "servicesPage";
    }

    @GetMapping("/home/citasDisponibles")
    public String showAvailableAppointments(@RequestParam(name = "dia") int dia, Model model){
        int currentYear = Year.now().getValue();
        int currentMonth = YearMonth.now().getMonthValue();
        LocalDateTime date = LocalDateTime.of(currentYear,currentMonth,dia,12,30,40);
        List<Cita> availableAppointments = citaService.getAppointmentsByDate(date);
        model.addAttribute("citas",availableAppointments);
        return "FreeAppointmentsPage";
    }

    @GetMapping("/reservar")
    public String showReservationForm(@RequestParam(name = "cita_id") int cita_id){
        System.out.println("hola");
        return "reservationPage";
    }
}
