package com.example.demo.Repositories;

import com.example.demo.Models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    @Procedure(procedureName = "test_stock.agregarInventario")
    Integer addStock(@Param("p_producto") String producto, @Param("p_cantidad_ml") int cantidad);

    @Procedure(procedureName = "test_stock.eliminarInventario")
    Integer deleteStock(@Param("p_inventario_id") int producto_id);

    @Procedure(procedureName = "test_stock.actualizarInventario")
    Integer updateStock(@Param("p_inventario_id") int producto_id, @Param("extra_ml") int extra_ml);

    @Query(value = "SELECT * FROM TABLE(test_stock.listarInventario)", nativeQuery = true)
    List<Inventario> listStock();
}
