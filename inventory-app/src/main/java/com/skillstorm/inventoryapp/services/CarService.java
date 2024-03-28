package com.skillstorm.inventoryapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.inventoryapp.models.Car;
import com.skillstorm.inventoryapp.repositories.CarRepository;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Car findCarById(int id) {
        Optional<Car> car = carRepository.findById(id);

        if (car.isPresent()) {

            return car.get();
        }

        return null;
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car editCar(int id, Car car) {
        Optional<Car> existingCarOptional = carRepository.findById(id);

        if (existingCarOptional.isPresent()) {
            Car existingCar = existingCarOptional.get();
            existingCar.setMake(car.getMake());
            existingCar.setModel(car.getModel());
            existingCar.setYear(car.getYear());
            existingCar.setPrice(car.getPrice());
            return carRepository.save(existingCar);
        } else {
            return carRepository.save(car);
        }
    }

    public void deleteCarById(int id) {
        carRepository.deleteById(id);
    }

}
