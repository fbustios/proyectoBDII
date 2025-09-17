package com.example.demo.Controllers;

import com.example.demo.Models.Cita;
import com.example.demo.Services.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public final class AdminController {
    CitaService citaService;


    AdminController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping("/admin")
    public String showAdminPage(){
        return "AdminHomePage";
    }

    @GetMapping("/admin/agregarCitas")
    public String showAddAppointments(){ 
        return "MakeAppointmentPage";
    }

    @GetMapping("/admin/solicitudes")
    public String showPendingAppointments(){ //select de todas las citas pendientes de confirmar
        return "UnconfirmedAppointmentsPage";
    }


    @GetMapping("/admin/citasAgendadas")
    public String showAllConfirmedAndPendingAppointments(Model model){ //select de todas las citas no resueltas
        List<Cita> citas = citaService.getConfirmedAndPendingAppointments();
        model.addAttribute("citasDisponibles",citas);
        return "AppointmentsToDoPage";
    }

    @PostMapping("/admin/agregarCitas")
    public String createAppointment(final @RequestParam(name = "fechaInicio") LocalDateTime fechaInicio,
                                    final @RequestParam(name = "fechaFin") LocalDateTime fechaFin){
        System.out.println(fechaFin);

        citaService.createAppointment(fechaInicio, fechaFin);

        return "redirect:/admin";
    }
}
