package ca.udem.ift1025.tp1.corrige;

public class Cat1 extends Hero {
    private int category = 1;


    public Cat1(String name, double cost, int armor, double hp) {
        super(name, cost, armor, hp);
        this.category = category;
    }

    // Getters et setters
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}

