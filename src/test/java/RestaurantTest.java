import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant = new Restaurant("Amelie's Cafe", "Chennai", LocalTime.parse("10:00:00"), LocalTime.parse("22:00:00"));
        Restaurant restaurant1 = Mockito.spy(restaurant);
        // Set current time to 12:00:00, which is between opening and closing time
        LocalTime currentTime = LocalTime.parse("12:00:00");
        when(restaurant1.getCurrentTime()).thenReturn(currentTime);

        Assertions.assertTrue(restaurant1.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant = new Restaurant("Amelie's Cafe", "Chennai", LocalTime.parse("10:00:00"), LocalTime.parse("22:00:00"));
        Restaurant restaurant1 = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.parse("23:00:00");
        when(restaurant1.getCurrentTime()).thenReturn(currentTime);
        Assertions.assertFalse(restaurant1.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<Failing Test Case>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void should_return_total_order_value_when_given_items_are_present_in_menu() throws itemNotFoundException {
        Restaurant restaurant = new Restaurant("Amelie's Cafe", "Chennai", LocalTime.parse("10:00:00"), LocalTime.parse("22:00:00"));
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        List<String> selectedItems = new ArrayList<>();
        selectedItems.add("Sweet corn soup");
        selectedItems.add("Vegetable lasagne");

        // Call getTotalOrderValue method and check if it returns the correct value
        assertEquals(300, restaurant.getTotalOrderValue(selectedItems));
    }

    @Test
    public void should_return_zero_when_no_items_are_present_in_menu() throws itemNotFoundException {
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Location", LocalTime.parse("10:00:00"), LocalTime.parse("22:00:00"));

        List<String> selectedItems = new ArrayList<>();
        //selectedItems.add("Sweet corn soup");
        //selectedItems.add("Vegetable lasagne");

        // Call getTotalOrderValue method and check if it returns 0
        assertEquals(0, restaurant.getTotalOrderValue(selectedItems));
    }


    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}