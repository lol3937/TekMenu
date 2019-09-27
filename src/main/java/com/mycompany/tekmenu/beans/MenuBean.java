/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tekmenu.beans;

import com.mycompany.tekmenu.ConnectionFactory;
import com.mycompany.tekmenu.Enteties.Meal;
import com.mycompany.tekmenu.Enteties.Menu;
import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Stateless
public class MenuBean {

    public Menu scrape() {
        try {
            List<Meal> meals = new ArrayList();
            Document doc = Jsoup.connect("https://skolmaten.se/teknikum/").get();
            String week = doc.select("h3 span").get(0).html();

            Elements rows = doc.select(".week .row");
            for (Element row : rows) {
                String dish = row.select(".items span").get(0).html();
                String alt_dish = row.select(".items span").get(1).html();
                String day = row.select(".date span").get(0).html();
                System.out.println(dish);
                System.out.println(alt_dish);
                Meal meal = new Meal(dish, alt_dish, day);
                meals.add(meal);
            }

            Menu menu = new Menu(2019, Integer.parseInt(week), meals);
            return menu;
        } catch (Exception e) {
            System.out.println("asjkdhask");
        }
        
        return null;
    }

    public Menu getMenu() {
        Menu menu;
        //kolla om veckans meny finns i databasen
        menu = getFromDatabase();

        if (menu == null) {
            menu = scrape();
            insertToDataBase(menu);

        }
        return menu;
    }

    private Menu getFromDatabase() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            List<Meal> meals = new ArrayList();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM menu WHERE week = 39 AND YEAR = 2019";
            ResultSet data = stmt.executeQuery(sql);
            data.next();
            Meal monMeal = new Meal(data.getString("mon_dish"), data.getString("min_altdish"), "Måndag");
            meals.add(monMeal);
            Meal tueMeal = new Meal(data.getString("thu_dish"), data.getString("thu_altdish"), "Tisdag");
            meals.add(tueMeal);
            Meal wedMeal = new Meal(data.getString("Wed_dish"), data.getString("Wed_altdish"), "Onsdag");
            meals.add(wedMeal);
            Meal thuMeal = new Meal(data.getString("Tue_dish"), data.getString("Tue_altdish"), "Torsdag");
            meals.add(thuMeal);
            Meal friMeal = new Meal(data.getString("Fri_dish"), data.getString("Fri_altdish"), "Fredag");
            meals.add(friMeal);
            int year = data.getInt("year");
            int week = data.getInt("week");
            
            Menu menu = new Menu(year, week, meals);
//SKAPA EN MENU och RETURNERA 
            return menu;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null; 
        }
        
        
    }
    private void insertToDataBase(Menu meny){
     try (Connection connection = ConnectionFactory.getConnection()){
            Statement stmt = connection.createStatement();
            String sql = String.format(
                    "INSERT INTO product VALUES (NULL, %d, %d, '%s' , '%s' , '%s' , "
                            + "'%s' , '%s' , '%s' , '%s' , '%s' , '%s' , '%s' )"
                    , meny.getYear(), 
                    meny.getWeek(),
                    meny.getMeals().get(0).getDish(),
                    meny.getMeals().get(0).getAlt_dish(),
                    meny.getMeals().get(1).getDish(),
                    meny.getMeals().get(1).getAlt_dish(),
                    meny.getMeals().get(2).getDish(),
                    meny.getMeals().get(2).getAlt_dish(),
                    meny.getMeals().get(3).getDish(),
                    meny.getMeals().get(3).getAlt_dish(),
                    meny.getMeals().get(4).getDish(),
                    meny.getMeals().get(4).getAlt_dish());
                    stmt.executeUpdate(sql);
            
        } catch (Exception e) {
            System.out.println("ERROR productbean.persist" +e.getMessage());
        }
    }

}
