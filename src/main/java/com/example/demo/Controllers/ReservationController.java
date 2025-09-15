package com.example.demo.Controllers;


import com.example.demo.Models.Dia;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.YearMonth;

@Controller
public final class ReservationController {

    @GetMapping("/home/calendar")
    public String showCalendarPage(Model model) {
        YearMonth yearMonth = YearMonth.now();
        int monthLength = yearMonth.lengthOfMonth();
        Dia[] dias = new Dia[monthLength];
        for (int i = 0; i < monthLength; i++) {
            Dia dia = new Dia(i + 1,true);
            dias[i] = dia;
        }

        model.addAttribute("diasMes", dias);
        return "CalendarPage";

    }
}
