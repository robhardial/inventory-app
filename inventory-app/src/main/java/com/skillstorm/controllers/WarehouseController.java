package com.skillstorm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.DTO.CarQuantityDTO;
import com.skillstorm.models.Car;
import com.skillstorm.models.Inventory;
import com.skillstorm.models.Warehouse;
import com.skillstorm.services.InventoryService;
import com.skillstorm.services.WarehouseService;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.findAllWarehouses();
        return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
    }

    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable int id) {
        Warehouse warehouse = warehouseService.findWarehouseById(id);
        return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
    }

    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse newWarehouse = warehouseService.saveWarehouse(warehouse);
        return new ResponseEntity<Warehouse>(newWarehouse, HttpStatus.CREATED);
    }

    @PutMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> editWarehouse(@PathVariable int id, @RequestBody Warehouse warehouse) {
        Warehouse updatedWarehouse = warehouseService.editWarehouse(id, warehouse);
        return new ResponseEntity<Warehouse>(updatedWarehouse, HttpStatus.OK);
    }

    @DeleteMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable int id) {
        warehouseService.deleteWarehouseById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/warehouse/inventory/{id}")
    public ResponseEntity<List<CarQuantityDTO>> getWarehouseInventoryById(@PathVariable int id) {
        List<CarQuantityDTO> cars = inventoryService.getCarsInWarehouse(id);
        return new ResponseEntity<List<CarQuantityDTO>>(cars, HttpStatus.OK);
    }

    @PostMapping("/warehouse/inventory/{id}")
    public ResponseEntity<Inventory> addNewCarToAWarehouse(@PathVariable int id,
            @RequestBody CarQuantityDTO carQuantityDTO) {
        Car car = carQuantityDTO.getCar();
        int quantity = carQuantityDTO.getQuantity();
        Inventory newInventory = inventoryService.addNewCarToAWarehouse(car, quantity, id);
        return new ResponseEntity<Inventory>(newInventory, HttpStatus.OK);
    }

    @PutMapping("/warehouse/inventory/{id}")
    public ResponseEntity<Inventory> editCarInAWarehouse(@PathVariable int id,
            @RequestBody CarQuantityDTO carQuantityDTO) {
        Car car = carQuantityDTO.getCar();
        int quantity = carQuantityDTO.getQuantity();
        Inventory updatedInventory = inventoryService.editCarInAWarehouse(car, quantity, id);
        return new ResponseEntity<Inventory>(updatedInventory, HttpStatus.OK);
    }

    @DeleteMapping("/warehouse/inventory/{id}")
    public ResponseEntity<Inventory> deleteCarInAWarehouse(@PathVariable int id,
            @RequestBody CarQuantityDTO carQuantityDTO) {
        Car car = carQuantityDTO.getCar();
        inventoryService.deleteCarInAWarehouse(id, car);
        return ResponseEntity.noContent().build();
    }

}
