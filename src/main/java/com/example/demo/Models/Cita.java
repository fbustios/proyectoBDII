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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDateTime fecha_inicio;

    @Column
    private LocalDateTime fecha_fin;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @Column
    private String imagen_url;

    @Column(name = "confirmada")
    private boolean confirmada;

    @Column(name = "pendiente")
    private boolean pendiente;

    @Column(name = "notificacion")
    private String notificacion;

    public Cita(final LocalDateTime fechaInicio, final LocalDateTime fechaFin){
        this.fecha_fin = fechaFin;
        this.fecha_inicio = fechaInicio;
        this.confirmada = false;
        this.pendiente = false;
    }
    public Cita(){}
}
