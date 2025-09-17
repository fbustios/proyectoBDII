package com.example.demo.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Dia {
    private LocalDateTime fecha;

    private boolean disponible;

    public Dia(LocalDateTime fecha, boolean disponible) {
        this.fecha = fecha;
        this.disponible = disponible;
    }
    public int getDia(){
        return fecha.getDayOfMonth();
    }
}
