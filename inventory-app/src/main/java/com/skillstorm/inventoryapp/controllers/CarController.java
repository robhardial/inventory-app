package com.skillstorm.inventoryapp.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventoryapp.models.Car;
import com.skillstorm.inventoryapp.models.Warehouse;
import com.skillstorm.inventoryapp.services.CarService;
import com.skillstorm.inventoryapp.services.WarehouseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars() {
        List<Car> cars = carService.findAllCars();
        return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        Car car = carService.findCarById(id);
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car newCar = carService.saveCar(car);
        return new ResponseEntity<Car>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<Car> editCar(@PathVariable int id, @RequestBody Car car) {
        Car updatedCar = carService.editCar(id, car);
        return new ResponseEntity<Car>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable int id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

}
