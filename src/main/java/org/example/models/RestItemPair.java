package org.example.models;

public class RestItemPair {

    private String rest;
    private String item;

    public RestItemPair(String rest, String item) {
        this.item = item;
        this.rest = rest;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
