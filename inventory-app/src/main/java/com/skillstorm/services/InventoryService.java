package com.skillstorm.services;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.DTO.CarQuantityDTO;
import com.skillstorm.Exceptions.WarehouseException;
import com.skillstorm.models.Car;
import com.skillstorm.models.Inventory;
import com.skillstorm.models.Warehouse;
import com.skillstorm.repositories.CarRepository;
import com.skillstorm.repositories.InventoryRepository;
import com.skillstorm.repositories.WarehouseRepository;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Inventory> findAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory findInventoryById(int id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);

        if (inventory.isPresent()) {

            return inventory.get();
        }

        return null;
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory editInventory(int id, Inventory inventory) {
        Optional<Inventory> existingInventoryOptional = inventoryRepository.findById(id);

        if (existingInventoryOptional.isPresent()) {
            Inventory existingInventory = existingInventoryOptional.get();
            existingInventory.setCar(inventory.getCar());
            existingInventory.setWarehouse(inventory.getWarehouse());
            existingInventory.setQuantity(inventory.getQuantity());
            return inventoryRepository.save(existingInventory);
        } else {
            return inventoryRepository.save(inventory);
        }
    }

    public void deleteInventoryById(int id) {
        inventoryRepository.deleteById(id);
    }

    public List<CarQuantityDTO> getCarsInWarehouse(int warehouseId) {
        List<Inventory> inventoryItems = inventoryRepository.findByWarehouseId(warehouseId);

        return inventoryItems.stream()
                .map(inv -> new CarQuantityDTO(inv.getCar(), inv.getQuantity()))
                .collect(Collectors.toList());
    }

    public Inventory addNewCarToAWarehouse(Car car, int quantity, int warehouseId) {
        Car existingCar = carRepository.findByMakeAndModel(car.getMake(),
                car.getModel());

        if (existingCar == null) {
            existingCar = carRepository.save(car);
        }

        Inventory newInventory = new Inventory();
        newInventory.setCar(existingCar);
        newInventory.setQuantity(quantity);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);

        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();
            newInventory.setWarehouse(warehouse);
        } else {
            throw new WarehouseException("Warehouse not found for ID: " + warehouseId);
        }

        return inventoryRepository.save(newInventory);

    }

    public Inventory editCarInAWarehouse(Car car, int quantity, int id) {

        Optional<Car> optionalCar = carRepository.findById(car.getCarId());

        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();
            // If the car exists, update its details
            existingCar.setYear(car.getYear());
            existingCar.setMake(car.getMake());
            existingCar.setModel(car.getModel());
            existingCar.setPrice(car.getPrice());
            carRepository.save(existingCar);
        } else {
            // If the car doesn't exist, save the new car
            Car existingCar = carRepository.save(car);
        }

        Inventory inventory = inventoryRepository.findByCar(car);

        if (inventory == null) {
            inventory = new Inventory();
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);

            if (optionalWarehouse.isPresent()) {
                Warehouse warehouse = optionalWarehouse.get();
                inventory.setWarehouse(warehouse);
            } else {
                throw new WarehouseException("Warehouse not found for ID: " + id);
            }

            inventory.setCar(car);
            inventory.setQuantity(quantity);
        } else {
            inventory.setQuantity(quantity);
        }

        return inventoryRepository.save(inventory);

    }

    public void deleteCarInAWarehouse(int id, Car car) {
        inventoryRepository.deleteById(id);
        carRepository.deleteById(car.getCarId());
    }

}
