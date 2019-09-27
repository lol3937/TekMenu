/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tekmenu.Enteties;

/**
 *
 * @author T4User
 */
public class Meal {
        String dish; 
        String alt_dish;
        String day;

    public Meal(String dish, String alt_dish, String day) {
        this.dish = dish;
        this.alt_dish = alt_dish;
        this.day = day;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getAlt_dish() {
        return alt_dish;
    }

    public void setAlt_dish(String alt_dish) {
        this.alt_dish = alt_dish;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
        
}
