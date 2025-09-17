package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "cita")
public final class Cita {

    @Id
    private int id;

    @Column
    private LocalDateTime fecha_inicio;

    @Column
    private LocalDateTime fecha_fin;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @Column
    private String imagen_url;

    @Column(name = "confirmada")
    private boolean confirmada;

    @Column(name = "pendiente")
    private boolean pendiente;

    public Cita(final LocalDateTime fechaInicio, final LocalDateTime fechaFin){
        this.fecha_fin = fechaFin;
        this.fecha_inicio = fechaInicio;
        this.confirmada = false;
        this.pendiente = false;
        this.id = 4;
    }
    public Cita(){}
}
