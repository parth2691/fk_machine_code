package org.example.service;

import jdk.internal.util.xml.impl.Pair;
import org.example.models.Item;
import org.example.models.RestItemPair;
import org.example.models.Restaurant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LowestPriceStrategy implements OrderPlacementStrategy {

    public LowestPriceStrategy() {

    }


    @Override
    public List<RestItemPair> pickRestuarants(List<String> items, ConcurrentHashMap<String, Restaurant> restaurants) {
        List<RestItemPair> pairs = new ArrayList<>();
        for(String itm:items) {
            PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparing(Item::getPrice));
            List<Item> allMenuItems = restaurants.values().stream().map(rst -> rst.getMenu().stream()
                            .map(mitm -> new Item(mitm.getName(), mitm.getPrice(), rst.getName()))
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList()).stream().flatMap(List::stream)
                    .collect(Collectors.toList());
            List<Item> requiredItem = allMenuItems.stream().filter(menuItm -> (menuItm.getName().trim()
                            .equalsIgnoreCase(itm.trim())))
                    .collect(Collectors.toList());
            if(!requiredItem.isEmpty()) {
                pq.addAll(requiredItem);
                Item lowestPrice = pq.poll();
                if(restaurants.get(lowestPrice.getRestuarant()).getCapacity()>0) {
                    pairs.add(new RestItemPair(lowestPrice.getRestuarant(), lowestPrice.getName()));
                } else {
                    System.out.println("Selected restaurant capacity is full "+lowestPrice.getRestuarant());
                }
            } else {
                System.out.println("Items in the order not found in any restuarants");
            }
        }
        return pairs;
    }
}
