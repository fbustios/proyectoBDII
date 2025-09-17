package com.example.demo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="usuario")
@Getter
@Setter
public final class User {
    @Id
    private int id;

    @Column
    private String username;

    @Column
    private String rol;

    @Column
    private String correo_electronico;

    @Column
    private String password_hash;

    public User(final String user, final String rol,
             final String correo_electronico, final String password_hash) {
        this.username = user;
        this.rol = rol;
        this.correo_electronico = correo_electronico;
        this.password_hash = password_hash;

    }

    public User() {

    }
}
