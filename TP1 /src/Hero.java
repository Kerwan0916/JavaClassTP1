import java.util.ArrayList;

public class Hero {
    private String name;
    private int category;
    private double cost;
    private int armor;
    private double lifePoints;

    public Hero(String name, int category, double cost, int armor, double lifePoints) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.armor = armor;
        this.lifePoints = lifePoints;
    }

    // getters et setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
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
    public double getLifePoints() {
        return lifePoints;
    }
    public void setLifePoints(double lifePoints) {
        this.lifePoints = lifePoints;
    }
    public void buyHero(String name, int category, double cost, int armor, double lifePoints, inventory inventory) {
        Hero hero = new Hero(name, category, cost, armor, lifePoints);
        inventory.addToList(hero);
    }
}
