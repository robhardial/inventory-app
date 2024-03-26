package com.skillstorm.inventoryapp.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @Column(name = "warehouse_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouseId;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column
    private String location;

    @Column(name = "maximum_capacity")
    private int maxCapacity;

    @OneToMany(targetEntity = Inventory.class, mappedBy = "warehouse")
    private Set<Inventory> inventory;

    public Warehouse() {
    }

    public Warehouse(int warehouseId, String warehouseName, String location, int maxCapacity) {
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.location = location;
        this.maxCapacity = maxCapacity;
    }

    public Warehouse(String warehouseName, String location, int maxCapacity) {
        this.warehouseName = warehouseName;
        this.location = location;
        this.maxCapacity = maxCapacity;
    }

    public Warehouse(int warehouseId, String warehouseName, String location, int maxCapacity,
            Set<Inventory> inventory) {
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.inventory = inventory;
    }

    public Warehouse(String warehouseName, String location, int maxCapacity, Set<Inventory> inventory) {
        this.warehouseName = warehouseName;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.inventory = inventory;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
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
        result = prime * result + warehouseId;
        result = prime * result + ((warehouseName == null) ? 0 : warehouseName.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + maxCapacity;
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
        Warehouse other = (Warehouse) obj;
        if (warehouseId != other.warehouseId)
            return false;
        if (warehouseName == null) {
            if (other.warehouseName != null)
                return false;
        } else if (!warehouseName.equals(other.warehouseName))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (maxCapacity != other.maxCapacity)
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
        return "Warehouse [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", location=" + location
                + ", maxCapacity=" + maxCapacity + "]";
    }

}
