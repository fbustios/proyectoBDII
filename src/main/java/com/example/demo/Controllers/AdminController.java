package com.example.demo.Controllers;

import com.example.demo.DTOS.InventarioDTO;
import com.example.demo.Models.Cita;
import com.example.demo.Models.Inventario;
import com.example.demo.Services.CitaService;
import com.example.demo.Services.InventarioService;
import jakarta.validation.Valid;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String showUnresolvedAppointments(Model model) {
        List<Cita> unresolvedAppointments = citaService.getUnresolvedAppointments();
        model.addAttribute("citas",unresolvedAppointments);
        return "UnconfirmedAppointmentsPage";
    }

    @PostMapping("/admin/resolver")
    public String resolveAppointment(@RequestParam(name = "cita_id") int cita_id, @RequestParam(name = "action") boolean action,
                                     RedirectAttributes redirectAttributes) {
        int exito = citaService.resolveAppointment(cita_id, action);
        if(exito == 1) {
            redirectAttributes.addFlashAttribute("msgSuccess","Cita resuelta correctamente");
        }else{
            redirectAttributes.addFlashAttribute("msgError","No se pudo resolver la cita");
        }
        return "redirect:/admin/solicitudes";
    }

    @GetMapping("/admin/citasAgendadas")
    public String showAllToDoAppointments(Model model){
        List<Cita> toDoAppointments = citaService.getToDoAppointments();
        model.addAttribute("citasDisponibles",toDoAppointments);
        return "AppointmentsToDoPage";
    }

    @PostMapping("/admin/agregarCitas")
    public String createAppointment(final @RequestParam(name = "fechaInicio") LocalDateTime fechaInicio,
                                    final @RequestParam(name = "fechaFin") LocalDateTime fechaFin,
                                    RedirectAttributes redirectAttributes){
        int exito = citaService.createAppointment(fechaInicio, fechaFin);
        if(exito == 1) {
            redirectAttributes.addFlashAttribute("msgSuccess","Cita creada correctamente");
        }else{
            redirectAttributes.addFlashAttribute("msgError","No se pudo crear la cita");
        }

        return "redirect:/admin";
    }

    @GetMapping("/admin/stock")
    public String showStock(Model model) {
        List<Inventario> stockList = inventarioService.listStock();
        model.addAttribute("stockList",stockList);
        return "StockPage";
    }

    @GetMapping("/admin/agregarStock")
    public String showAddStockForm(Model model) {
        model.addAttribute("inventarioDTO", new InventarioDTO());
        return "AddStockPage";
    }
    @PostMapping("/admin/agregarStock")
    public String showAddStockForm(final @Valid @ModelAttribute InventarioDTO inventarioDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/agregarStock";
        }
        int exito = inventarioService.addStock(inventarioDTO.getProducto(), inventarioDTO.getCantidad_ml());
        if(exito == 1) {
            redirectAttributes.addFlashAttribute("msgSuccess","Item agregado satisfactoriamente");
        }else{
            redirectAttributes.addFlashAttribute("msgError","No se pudo agregar el item");
        }
        return "redirect:/admin/stock";
    }

    @PostMapping("/admin/eliminarStock")
    public String deleteStock(final @RequestParam(name = "id") int item_id, RedirectAttributes redirectAttributes){
        int exito = inventarioService.deleteStock(item_id);
        if(exito == 1) {
            redirectAttributes.addFlashAttribute("msgSuccess","Item eliminado satisfactoriamente");
        }else{
            redirectAttributes.addFlashAttribute("msgError","No se pudo eliminar el item");
        }

        return "redirect:/admin/stock";
    }
    @GetMapping("/admin/actualizarStock")
    public String showUpdateStockForm(@RequestParam("id") int producto_id, Model model){
        model.addAttribute("item_id",producto_id);
        return "updateStockPage";
    }

    @PostMapping("/admin/actualizarStock")
    public String updateStock(@RequestParam(name = "item_id") int producto_id, @RequestParam(name = "cantidad") int cantidad,
                              RedirectAttributes redirectAttributes) {
        int exito = inventarioService.updateStock(producto_id, cantidad);
        if(exito == 1) {
            redirectAttributes.addFlashAttribute("msgSuccess","Item actualizado satisfactoriamente");
        }else{
            redirectAttributes.addFlashAttribute("msgError","No se pudo actualizar el item");
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/eliminarCita")
    public String deleteAppointment(){
        return "deleteAppointmentPage";
    }

    @PostMapping("/admin/eliminarCita")
    public String deleteAppointment(@RequestParam("cita_id") int cita_id, RedirectAttributes redirectAttributes){
        int exito = citaService.deleteAppointment(cita_id);
        if(exito == 1) {
            redirectAttributes.addFlashAttribute("msgSuccess","Cita eliminada satisfactoriamente");
        }else{
            redirectAttributes.addFlashAttribute("msgError","No se pudo eliminar la cita");
        }
        return "redirect:/admin";
    }
}
