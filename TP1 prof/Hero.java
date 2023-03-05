package ca.udem.ift1025.tp1.corrige;

import java.util.ArrayList;


public class Hero {
    private String name;
    private double cost;
    private int armor;
    private double hp;

    public Hero(String name,double cost, int armor, double hp) {
        this.name = name;
        this.cost = cost;
        this.armor = armor;
        this.hp = hp;
    }

    // getters et setters
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
}
