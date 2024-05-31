package com.example.activity1;

import java.util.ArrayList;
import java.util.List;

public class DatabaseItems {
    private List<Menultem> cars = new ArrayList<>();

    public DatabaseItems() {
        // Adding more car items
        cars.add(new Menultem("sedan", "Toyota Camry", "sedan_image.png"));
        cars.add(new Menultem("sedan", "Honda Accord", "accord_image.png"));
        cars.add(new Menultem("suv", "Honda CR-V", "suv_image.png"));
        cars.add(new Menultem("suv", "Ford Escape", "escape_image.jpeg"));
        cars.add(new Menultem("convertible", "Mazda MX-5", "convertible_image.png"));
        cars.add(new Menultem("convertible", "BMW Z4", "z4_image.png"));
        cars.add(new Menultem("hatchback", "Volkswagen Golf", "hatchback_image.png"));
        cars.add(new Menultem("hatchback", "Ford Focus", "focus_image.png"));
        cars.add(new Menultem("coupe", "BMW 4 Series", "coupe_image.png"));
        cars.add(new Menultem("coupe", "Audi A5", "a5_image.png"));
        cars.add(new Menultem("pickup", "Ford F-150", "pickup_image.png"));
        cars.add(new Menultem("pickup", "Chevrolet Silverado", "silverado_image.png"));
        cars.add(new Menultem("minivan", "Honda Odyssey", "minivan_image.png"));
        cars.add(new Menultem("minivan", "Toyota Sienna", "sienna_image.png"));
        cars.add(new Menultem("crossover", "Toyota RAV4", "crossover_image.png"));
        cars.add(new Menultem("crossover", "Nissan Rogue", "rogue_image.png"));
        cars.add(new Menultem("electric", "Tesla Model 3", "electric_image.png"));
        cars.add(new Menultem("electric", "Nissan Leaf", "leaf_image.png"));
        cars.add(new Menultem("luxury", "Mercedes-Benz S-Class", "luxury_image.png"));
        cars.add(new Menultem("luxury", "BMW 7 Series", "7_series_image.png"));
        cars.add(new Menultem("sports", "Porsche 911", "sports_car_image.png"));
        cars.add(new Menultem("sports", "Chevrolet Corvette", "corvette_image.png"));
        // Add more car items as needed
    }

    public String[] getCategories() {
        // Assume we are reading data from a database
        String[] categories = {
                "sedan", "convertible", "suv", "hatchback",
                "coupe", "pickup", "minivan", "crossover",
                "electric", "luxury", "sports"
        };
        return categories;
    }

    public List<Menultem> getMenuItems(String category) {
        List<Menultem> result = new ArrayList<>();
        for (Menultem m : cars) {
            if (m.getCategory().equals(category)) {
                result.add(m);
            }
        }
        return result;
    }

    public List<Menultem> getAllMenuItems() {
        return new ArrayList<>(cars);
    }
}
