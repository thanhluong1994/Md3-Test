package com.codegym.model;

public class Category {
    private int id_category;
    private String nam_category;

    public Category() {
    }

    public Category(int id_category, String nam_category) {
        this.id_category = id_category;
        this.nam_category = nam_category;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getNam_category() {
        return nam_category;
    }

    public void setNam_category(String nam_category) {
        this.nam_category = nam_category;
    }
}
