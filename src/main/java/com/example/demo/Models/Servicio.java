package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "servicio")
@Setter
@Getter
public final class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String tipo;

    @Column
    private int precio;

    @Column
    private String descripcion;

    Servicio(final String tipo, final int precio, final String descripcion) {
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Servicio() {

    }
}
