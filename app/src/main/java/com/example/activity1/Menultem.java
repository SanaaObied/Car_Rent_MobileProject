package com.example.activity1;

public class Menultem {
    private String category;
    private String name;
    private String imagePath;

    public Menultem(String category, String name, String imagePath) {
        this.category = category;
        this.name = name;
        this.imagePath = imagePath;
    }

    // Getters and setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}



