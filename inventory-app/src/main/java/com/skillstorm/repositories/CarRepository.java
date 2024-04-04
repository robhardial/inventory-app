package com.skillstorm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.Car;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findByMakeAndModelAndYear(String make, String model, int Year);

}
