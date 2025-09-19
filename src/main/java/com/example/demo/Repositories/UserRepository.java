package com.example.demo.Repositories;

import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Procedure(procedureName = "test.crearUsuario")
    Integer createUser(@Param("p_username") String username,@Param("p_rol") String rol,
                   @Param("p_correo") String correo,@Param("p_password_hash") String password_hash);


    Optional<User> findByUsername(String username);
}
