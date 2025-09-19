package com.example.demo.Services;

import com.example.demo.Models.Inventario;

import java.util.List;

public interface InventarioService {
    int addStock(String producto, int cantidad_ml);
    int deleteStock(int item_id);
    int updateStock(int item_id, int cantidad_ml);
    List<Inventario> listStock();
}
