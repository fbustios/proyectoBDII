package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public final class AdminController {


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
        return "PendingAppointmentsPage";
    }


    @GetMapping("/admin/MisCitas")
    public String showAllAppointments(Model model){ //select de todas las citas no resueltas
        return "AllAppointmentsPage";
    }

    @PostMapping("/admin/agregarCitas")
    public void uploadAppointment(){  //crear cita

    }
}
