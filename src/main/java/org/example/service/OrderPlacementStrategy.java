package org.example.service;

import org.example.models.RestItemPair;
import org.example.models.Restaurant;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface OrderPlacementStrategy {

    List<RestItemPair> pickRestuarants(List<String> items, ConcurrentHashMap<String, Restaurant> restaurants);
}
