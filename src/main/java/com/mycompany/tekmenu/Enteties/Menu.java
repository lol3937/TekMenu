/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tekmenu.Enteties;

import java.util.List;

/**
 *
 * @author T4User
 */
public class Menu {
 private int year;
 private int week;
 private List<Meal> meals; 

    public Menu(int year, int week, List<Meal> meals) {
        this.year = year;
        this.week = week;
        this.meals = meals;
    }

 
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
 
}
