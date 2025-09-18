package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="usuario")
@Getter
@Setter
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String rol;

    @Column(name = "correo_electronico")
    private String email;

    @Column
    private String password_hash;

    public User(final String user, final String rol,
                final String email, final String password_hash) {
        this.username = user;
        this.rol = rol;
        this.email = email;
        this.password_hash = password_hash;
    }

    public User() {

    }
}
