package ca.udem.ift1025.tp1.corrige;

public class Cat4 extends Hero {
    private int category = 4;

    public Cat4(String name, double cost, int armor, double hp) {
        super(name, cost, armor, hp);
        this.category = category;
    }

    // Getters et setters
    public int getCategory() {
        return category;
    }

}

