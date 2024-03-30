package com.skillstorm.services;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.models.Warehouse;
import com.skillstorm.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse findWarehouseById(int id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if (warehouse.isPresent()) {

            return warehouse.get();
        }

        return null;
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse editWarehouse(int id, Warehouse warehouse) {
        Optional<Warehouse> existingWarehouseOptional = warehouseRepository.findById(id);

        if (existingWarehouseOptional.isPresent()) {
            Warehouse existingWarehouse = existingWarehouseOptional.get();
            existingWarehouse.setLocation(warehouse.getLocation());
            existingWarehouse.setMaxCapacity(warehouse.getMaxCapacity());
            existingWarehouse.setWarehouseName(warehouse.getWarehouseName());
            return warehouseRepository.save(existingWarehouse);
        } else {
            return warehouseRepository.save(warehouse);
        }
    }

    public void deleteWarehouseById(int id) {
        warehouseRepository.deleteById(id);
    }
}
