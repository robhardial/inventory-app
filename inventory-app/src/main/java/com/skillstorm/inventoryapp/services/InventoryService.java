package com.skillstorm.inventoryapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.inventoryapp.models.Car;
import com.skillstorm.inventoryapp.models.Inventory;
import com.skillstorm.inventoryapp.repositories.CarRepository;
import com.skillstorm.inventoryapp.repositories.InventoryRepository;
import com.skillstorm.inventoryapp.repositories.WarehouseRepository;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Car> getCarsInWarehouse(int warehouseId) {
        List<Inventory> inventoryItems = inventoryRepository.findByWarehouseId(warehouseId);
        List<Car> carsInWarehouse = inventoryItems.stream()
                .map(Inventory::getCar)
                .collect(Collectors.toList());
        return carsInWarehouse;
    }

}
