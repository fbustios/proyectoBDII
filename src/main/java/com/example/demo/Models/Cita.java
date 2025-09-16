package com.example.demo.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cita")
public final class Cita {
    @Id
    private int id;

    @Column
    private LocalDate fecha_inicio;

    @Column
    private LocalDate fecha_fin;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @Column
    private String imagen_url;

    @Column
    private int confirmada;

    @Column
    private int pendiente;
}
