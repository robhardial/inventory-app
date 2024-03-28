package com.skillstorm.inventoryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.inventoryapp.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
