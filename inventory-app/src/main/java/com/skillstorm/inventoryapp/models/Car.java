package com.skillstorm.inventoryapp.models;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int carId;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    @Min(1885)
    @Max(2025)
    private int year;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @OneToMany(targetEntity = Inventory.class, mappedBy = "car")
    private Set<Inventory> inventory;

    public Car() {
    }

    public Car(int carId, String make, String model, @Min(1885) @Max(2025) int year, BigDecimal price,
            Set<Inventory> inventory) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.inventory = inventory;
    }

    public Car(String make, String model, @Min(1885) @Max(2025) int year, BigDecimal price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Car(int carId, String make, String model, @Min(1885) @Max(2025) int year, BigDecimal price) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(Set<Inventory> inventory) {
        this.inventory = inventory;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + carId;
        result = prime * result + ((make == null) ? 0 : make.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + year;
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (carId != other.carId)
            return false;
        if (make == null) {
            if (other.make != null)
                return false;
        } else if (!make.equals(other.make))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (year != other.year)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (inventory == null) {
            if (other.inventory != null)
                return false;
        } else if (!inventory.equals(other.inventory))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Car [carId=" + carId + ", make=" + make + ", model=" + model + ", year=" + year + ", price=" + price
                + "]";
    }

}
