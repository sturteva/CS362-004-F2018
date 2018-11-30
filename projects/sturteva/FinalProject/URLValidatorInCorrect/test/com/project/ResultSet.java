package com.project;

/* Created own class to replace the class ResultPair which was buggy */
public class ResultSet {
    public String item;
    public boolean valid;

    public ResultSet(String item, boolean valid) {
        this.item = item;
        this.valid = valid;  //Weather the individual part of url is valid.
    }
}