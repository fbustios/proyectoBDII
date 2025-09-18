package com.example.demo.Repositories;

import com.example.demo.Models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    boolean existsByProducto(String producto);
}
