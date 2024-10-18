package org.example.models;

import java.util.Set;

public class Restaurant {
    String name;

    Set<Item> menu;

    Integer capacity;

    public Restaurant(String name, Set<Item> menu, Integer capacity) {
        this.name = name;
        this.menu = menu;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getMenu() {
        return menu;
    }

    public void setMenu(Set<Item> menu) {
        this.menu = menu;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getNameAndCapacity() {
        return this.name + " : " + this.capacity;
    }
}
