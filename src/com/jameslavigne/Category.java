package com.jameslavigne;

public class Category {
    private int catId;
    private String name;

    public Category(){}

    public Category(int catId, String name) {
        this.catId = catId;
        this.name = name;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
