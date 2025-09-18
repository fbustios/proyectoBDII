package com.example.demo.Controllers;

import com.example.demo.DTOS.InventarioDTO;
import com.example.demo.DTOS.SignUpDTO;
import com.example.demo.Models.Cita;
import com.example.demo.Models.Inventario;
import com.example.demo.Services.CitaService;
import com.example.demo.Services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public final class AdminController {
    CitaService citaService;
    InventarioService inventarioService;


    AdminController(final CitaService citaService, final InventarioService inventarioService) {
        this.citaService = citaService;
        this.inventarioService  = inventarioService;
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
    public String showUnresolvedAppointments(Model model){ //select de todas las citas pendientes de confirmar
        List<Cita> unresolvedAppointments = citaService.getUnresolvedAppointments();
        model.addAttribute("citas",unresolvedAppointments);
        return "UnconfirmedAppointmentsPage";
    }

    @PostMapping("/admin/resolver")
    public String resolveAppointment(@RequestParam(name = "cita_id") int cita_id, @RequestParam(name = "action") boolean action){
        citaService.resolveAppointment(cita_id, action);
        return "redirect:/admin/solicitudes";
    }

    @GetMapping("/admin/citasAgendadas")
    public String showAllToDoAppointments(Model model){ //select de todas las citas no resueltas
        List<Cita> toDoAppointments = citaService.getToDoAppointments();
        model.addAttribute("citasDisponibles",toDoAppointments);
        return "AppointmentsToDoPage";
    }

    @PostMapping("/admin/agregarCitas")
    public String createAppointment(final @RequestParam(name = "fechaInicio") LocalDateTime fechaInicio,
                                    final @RequestParam(name = "fechaFin") LocalDateTime fechaFin){
        citaService.createAppointment(fechaInicio, fechaFin);

        return "redirect:/admin";
    }

    @GetMapping("/admin/stock")
    public String showStock(Model model){
        List<Inventario> stockList = inventarioService.listStock();
        model.addAttribute("stockList",stockList);
        return "StockPage";
    }

    @GetMapping("/admin/agregarStock")
    public String showAddStockForm(Model model){
        model.addAttribute("inventarioDTO", new InventarioDTO());
        return "AddStockPage";
    }
    @PostMapping("/admin/agregarStock")
    public String showAddStockForm(final @Valid @ModelAttribute InventarioDTO inventarioDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/agregarStock";
        }
        inventarioService.addStock(inventarioDTO.getProducto(), inventarioDTO.getCantidad_ml());
        return "redirect:/admin/stock";
    }

    @PostMapping("/admin/eliminarStock")
    public String deleteStock(final @RequestParam(name = "id") int item_id){
        System.out.println(item_id);
        inventarioService.deleteStock(item_id);
        return "redirect:/admin/stock";
    }
}
