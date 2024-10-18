package org.example.service;

import org.example.models.Order;
import org.example.models.RestItemPair;
import org.example.models.Restaurant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OrdersService {

    public  final AtomicInteger orderCounter;

    private final RestaurantService restaurantService;

    private final ConcurrentHashMap<String, Order> orders;

    //we can implement multiple stratgye
    private final LowestPriceStrategy lowestPriceStrategy;

    public OrdersService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
        this.orderCounter = new AtomicInteger(0);
        this.orders = new ConcurrentHashMap<>();
        this.lowestPriceStrategy = new LowestPriceStrategy();
    }


    public void printSystemStats() {
        for(Map.Entry<String, Restaurant> restaurantEntry : restaurantService.getRestaurants().entrySet()) {
            System.out.println(restaurantEntry.getValue().getNameAndCapacity());
        }
    }


    public synchronized void placeOrder(List<String> items, String strategy) {
        List<RestItemPair> restItemPairs = lowestPriceStrategy.pickRestuarants(items,
                restaurantService.getRestaurants());
        /*if(items.size()!=restItemPairs.size()) {
            System.out.println("Unable to place order due restuarant capacity issue.");
        } else {*/
            String orderId = "#"+orderCounter.incrementAndGet();
            List<String> resturants = restItemPairs.stream().map(RestItemPair::getRest)
                    .collect(Collectors.toList());
            List<String> menuItems = restItemPairs.stream().map(RestItemPair::getItem)
                    .collect(Collectors.toList());
            Order ord = new Order(orderId, resturants, menuItems);
            orders.put(orderId, ord);
            if(!resturants.isEmpty()) {
                System.out.println("Order Id = "+orderId+" delivered from "+
                        String.join(",", resturants));
            }
            for(String rst : resturants) {
                int capacity = restaurantService.getRestaurants().get(rst).getCapacity();
                capacity--;
                System.out.println("New capacity for "+rst+" is now="+capacity);
                restaurantService.updateRestaurantCapacity(rst, capacity);
            }
        //}
    }

    public synchronized void fulfilledItemForRestaurant(String orderId) {
        if(orders.containsKey(orderId)) {
            for(String rst : orders.get(orderId).getRestuarants()) {
                int capacity = restaurantService.getRestaurants().get(rst).getCapacity();
                capacity++;
                restaurantService.updateRestaurantCapacity(rst, capacity);
            }
        } else {
            System.out.println("Order id not found for the restuarant");
        }
    }





}
