package tp1;

import java.util.ArrayList;


public class Hero {
    private String name;
    private int category;
    private double cost;
    private int armor;
    private double hp;

    public Hero(String name, int category, double cost, int armor, double hp) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.armor = armor;
        this.hp = hp;
    }

    // getters et setters


    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void enleveHp(double montant) {
        this.hp -= montant;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }
}
