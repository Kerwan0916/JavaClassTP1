public class Bank extends inventory{
    private int argent;
    private int armor;

    public Bank(int initialArgent, int armor) {
        this.argent = initialArgent;
        this.armor = armor;
    }
    public int getArgent() {
        return argent;
    }
    public void displayArgent() {
        System.out.println(argent);
    }
    public void enleveArgent(int montant) {
        if (argent >= montant) {
            argent -= montant;
        } else {
            System.out.println("pas assez!");
        }
    }
    public void buyArmor(int number, int cost) {
        int coutTotal = number * cost;
        if (argent >= coutTotal) {
            argent -= coutTotal;
            armor += number;
        } else {
            System.out.println("Pas assez d'argent.");
        }
    }
}
