package edu.iu.c322.trackingservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    private String name;
    private int quantity;
    private int price;
    private String status = "Ordered";
    private String statusUpdatedOn = java.time.LocalDate.now().toString();

    public String getStatusUpdatedOn() {
        return statusUpdatedOn;
    }

    public void setStatusUpdatedOn(String statusUpdatedOn) {
        this.statusUpdatedOn = statusUpdatedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantity == item.quantity && price == item.price && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price);
    }
}
