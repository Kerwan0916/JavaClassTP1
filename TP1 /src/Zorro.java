public class Zorro extends Hero{
    private String name = "Zorro";
    private int category;
    private int price;
    private int lifePoints;

    public Zorro(int category, int price, int lifePoints) {
        this.category = category;
        this.price = price;
        this.lifePoints = lifePoints;
    }

    // Getters et setters
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }
}
