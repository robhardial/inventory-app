package com.skillstorm.DTO;

import com.skillstorm.models.Car;

public class CarQuantityDTO {

    private Car car;
    private int quantity;

    public CarQuantityDTO() {
    }

    public CarQuantityDTO(Car car, int quantity) {
        this.car = car;
        this.quantity = quantity;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((car == null) ? 0 : car.hashCode());
        result = prime * result + quantity;
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
        CarQuantityDTO other = (CarQuantityDTO) obj;
        if (car == null) {
            if (other.car != null)
                return false;
        } else if (!car.equals(other.car))
            return false;
        if (quantity != other.quantity)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CarQuantityDTO [car=" + car + ", quantity=" + quantity + "]";
    }

}