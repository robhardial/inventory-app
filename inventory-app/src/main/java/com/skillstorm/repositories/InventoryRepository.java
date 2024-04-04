package com.skillstorm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.Car;
import com.skillstorm.models.Inventory;

import jakarta.transaction.Transactional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i FROM Inventory i WHERE i.warehouse.warehouseId = :warehouseId")
    List<Inventory> findByWarehouseId(@Param("warehouseId") int warehouseId);

    Inventory findByCar(Car existingCar);

    void deleteByCar(Car car);

    @Query("SELECT i FROM Inventory i WHERE i.car.id = :carId")
    Optional<Inventory> findByCarId(int carId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Inventory i WHERE i.car.id = :carId")
    void deleteByCarId(int carId);

}