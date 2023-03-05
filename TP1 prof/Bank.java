package ca.udem.ift1025.tp1.corrige;


public class Bank {
    private double argent;
    private int armor;

    public Bank(double argent, int armor) {
        this.argent = argent;
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public double getArgent() {
        return argent;
    }
    public void displayArgent() {
        System.out.println(argent);
    }
    // On va toujours verifie si on assez d'argent avant d'utilise la fonction enleveArgent
    public void enleveArgent(double montant) {
        argent -= montant;
    }
    public void enleveArmore(double montant) {
        argent -= montant;
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
