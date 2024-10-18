package org.example;

import org.example.models.Item;
import org.example.service.OrdersService;
import org.example.service.RestaurantService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class RestuarantOrderingSystem
{
    public static void main( String[] args )
    {
        RestaurantService restaurantService = new RestaurantService();
        OrdersService ordersService = new OrdersService(restaurantService);


        //Adding restuarants start
        Set<Item> itemsForA2B = new HashSet<>(Arrays.asList(new Item("idly", 40.0),
                new Item("Vada", 30.0), new Item("Dosa", 50.0)));
        restaurantService.addRestaurant("A2B", itemsForA2B, 4);

        Set<Item> itemsForRasganga = new HashSet<>(Arrays.asList(new Item("idly", 45.0),
                new Item("Set Dosa", 60.0), new Item("Poori", 25.0)));
        restaurantService.addRestaurant("Rasaganga", itemsForRasganga, 6);

        Set<Item> itemsForEatfit = new HashSet<>(Arrays.asList(new Item("idly", 30.0),
                new Item("Set Dosa", 40.0)));
        restaurantService.addRestaurant("Eatfit", itemsForEatfit, 2);
        //Adding restuarants end

        ordersService.printSystemStats();

        //placing orders start
        ordersService.placeOrder(Arrays.asList("idly", "Poori"), "lowest_price_strategy");

        ordersService.placeOrder(Arrays.asList("idly", "Vada"), "lowest_price_strategy");

        ordersService.printSystemStats();

        ordersService.placeOrder(Arrays.asList("idly"), "lowest_price_strategy");
        //placing orders end

        //order fulfillement start
        ordersService.fulfilledItemForRestaurant("#1");

        ordersService.fulfilledItemForRestaurant("#2");
        //order fulfillement end

        ordersService.printSystemStats();

        ordersService.placeOrder(Arrays.asList("idly"), "lowest_price_strategy");

        ordersService.placeOrder(Arrays.asList("ice_cream"), "lowest_price_strategy");
    }
}
