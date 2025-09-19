package com.example.demo.Services;

import com.example.demo.Models.Inventario;
import com.example.demo.Repositories.InventarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultInventarioService implements InventarioService{
    InventarioRepository inventarioRepo;

    DefaultInventarioService(InventarioRepository inventarioRepo) {
        this.inventarioRepo = inventarioRepo;
    }
    @Transactional
    @Override
    public int addStock(final String producto, final int cantidad_ml) {
        return inventarioRepo.addStock(producto, cantidad_ml);
    }
    @Transactional
    @Override
    public int deleteStock(final int item_id) {
        return inventarioRepo.deleteStock(item_id);
    }

    @Override
    public int updateStock(final int item_id, final int cantidad) {
        return inventarioRepo.updateStock(item_id, cantidad);
    }

    @Override
    public List<Inventario> listStock() {
        return inventarioRepo.listStock();
    }

}
