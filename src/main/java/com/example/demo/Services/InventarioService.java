package com.example.demo.Services;

import com.example.demo.Models.Inventario;

import java.util.List;

public interface InventarioService {
    void addStock(String producto, int cantidad_ml);
    void deleteStock(int item_id);
    void updateStock(int item_id, int cantidad_ml);
    List<Inventario> listStock();
}
