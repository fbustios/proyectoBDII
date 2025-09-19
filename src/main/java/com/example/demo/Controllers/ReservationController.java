package com.example.demo.Controllers;


import com.example.demo.Models.Cita;
import com.example.demo.Models.Dia;
import com.example.demo.Services.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;


@Controller
public final class ReservationController {
    CitaService citaService;

    ReservationController(final CitaService citaService){
        this.citaService = citaService;
    }

    @GetMapping("/home/calendar")
    public String showCalendarPage(Model model) {
        Dia[] days = citaService.getDaysAssignedWithDates();
        model.addAttribute("diasMes", days);
        return "CalendarPage";

    }

    @GetMapping("/home/servicios")
    public String showServices(@RequestParam(name = "cita_id") int cita_id, Model model){
        model.addAttribute("cita_id",cita_id);
        return "servicesPage";
    }

    @GetMapping("/home/citasDisponibles")
    public String showAvailableAppointments(final @RequestParam(name = "dia") int dia, Model model){
        int currentYear = Year.now().getValue();
        int currentMonth = YearMonth.now().getMonthValue();
        LocalDateTime date = LocalDateTime.of(currentYear,currentMonth,dia,12,30,40);
        List<Cita> availableAppointments = citaService.getAppointmentsByDate(date);
        model.addAttribute("citas",availableAppointments);
        return "FreeAppointmentsPage";
    }

    @GetMapping("/reservar")
    public String showReservationForm(final @RequestParam(name = "s_id") int servicio_id, final @RequestParam(name = "c_id") int cita_id,
                                      Model model){
        model.addAttribute("c_id",cita_id);
        model.addAttribute("s_id",servicio_id);
        return "reservationPage";
    }

    @PostMapping("/reservar")
    public String finishReservation(final @RequestParam(name = "servicio_id") int servicio_id, final @RequestParam(name = "imagen") MultipartFile file,
                                    final @RequestParam(name = "cita_id") int cita_id, Principal principal, RedirectAttributes redirectAttributes){
        int exito = citaService.bookAppointment(principal.getName(),servicio_id,cita_id, file);;
        if(exito == 1) {
            redirectAttributes.addFlashAttribute("msgSuccess","Reservación realizada satisfactoriamente");
        }else{
            redirectAttributes.addFlashAttribute("msgError","Hubo un problema con la reservación");
        }

        return "redirect:/home";
    }

}
