package com.skillstorm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.Car;
import com.skillstorm.models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i FROM Inventory i WHERE i.warehouse.warehouseId = :warehouseId")
    List<Inventory> findByWarehouseId(@Param("warehouseId") int warehouseId);

    Inventory findByCar(Car existingCar);

}