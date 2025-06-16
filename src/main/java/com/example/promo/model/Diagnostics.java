package com.example.promo.model;

import java.util.List;

public class Diagnostics {
    private String level;
    private List<String> category;
    private List<String> labs;
    private List<String> tests;

    public Diagnostics() {
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

    public List<String> getLabs() {
        return labs;
    }

    public void setLabs(List<String> labs) {
        this.labs = labs;
    }

    public List<String> getTests() {
        return tests;
    }

    public void setTests(List<String> tests) {
        this.tests = tests;
    }
}
