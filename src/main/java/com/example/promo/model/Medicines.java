package com.example.promo.model;

import java.util.List;

public class Medicines {
    private String level;
    private List<String> category;
    private List<String> manufactures;
    private List<String> skus;

    public Medicines() {
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getManufactures() {
        return manufactures;
    }

    public void setManufactures(List<String> manufactures) {
        this.manufactures = manufactures;
    }

    public List<String> getSkus() {
        return skus;
    }

    public void setSkus(List<String> skus) {
        this.skus = skus;
    }
}
