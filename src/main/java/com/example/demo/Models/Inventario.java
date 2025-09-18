package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventario")
@Getter
@Setter
public final class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String producto;

    @Column
    private int cantidad_ml;

    public Inventario() {}

    public Inventario(final String producto, final int cantidad_ml) {
        this.producto = producto;
        this.cantidad_ml = cantidad_ml;
    }
}
