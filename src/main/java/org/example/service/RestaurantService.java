package org.example.service;

import org.example.models.Item;
import org.example.models.Restaurant;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RestaurantService {

    private ConcurrentHashMap<String, Restaurant> restaurants;

    public RestaurantService() {
        this.restaurants = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addRestaurant(String name, Set<Item> menu, Integer capacity) {
        if(!restaurants.containsKey(name)) {
            restaurants.put(name, new Restaurant(name, menu, capacity));
            System.out.println("Added the restaurant "+name);
        } else {
            System.out.println("Restaurant already present please update menu or capacity");
        }
    }

    public void updateRestaurantMenu(String name, Set<Item> menu) {
        if(restaurants.containsKey(name)) {
           Restaurant restaurant =  restaurants.get(name);
           restaurant.setMenu(menu);
           restaurants.put(name, restaurant);
        } else {
            System.out.println("Restaurant does not exists");
        }
    }

    public void updateRestaurantCapacity(String name, Integer capacity) {
        if(restaurants.containsKey(name)) {
            Restaurant restaurant =  restaurants.get(name);
            restaurant.setCapacity(capacity);
            restaurants.put(name, restaurant);
        } else {
            System.out.println("Restaurant does not exists");
        }
    }
}
