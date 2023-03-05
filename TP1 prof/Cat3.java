package ca.udem.ift1025.tp1.corrige;

public class Cat3 extends Hero {
    private int category = 3;

    public Cat3(String name, double cost, int armor, double hp) {
        super(name, cost, armor, hp);
        this.category = category;
    }

    // Getters et setters
    public int getCategory() {
        return category;
    }

}

