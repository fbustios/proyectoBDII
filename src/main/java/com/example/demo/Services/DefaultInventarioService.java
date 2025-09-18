package com.example.demo.Services;

import com.example.demo.Models.Inventario;
import com.example.demo.Repositories.InventarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultInventarioService implements InventarioService{
    InventarioRepository inventarioRepo;

    DefaultInventarioService(InventarioRepository inventarioRepo){
        this.inventarioRepo = inventarioRepo;
    }
    @Transactional
    @Override
    public void addStock(final String producto, final int cantidad_ml) {
            boolean alreadyExists = inventarioRepo.existsByProducto(producto);
            if(!alreadyExists) {
                Inventario item = new Inventario(producto,cantidad_ml);
                inventarioRepo.save(item);
            }
    }
    @Transactional
    @Override
    public void deleteStock(final int item_id) {
        inventarioRepo.deleteById(item_id);
    }

    @Override
    public void updateStock(final int item_id, final int cantidad) {

    }

    @Override
    public List<Inventario> listStock() {
        return inventarioRepo.findAll();
    }

    private void notifyMissingStock(){

    }
}
