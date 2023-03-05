package ca.udem.ift1025.tp1.corrige;

public class Guilde {
    private double montantInitial;
    private int nbArmure;

    public Guilde(double montantInitial, int nbArmure) {
        this.montantInitial = montantInitial;
        this.nbArmure = nbArmure;
    }



    public double getMontantInitial() {
        return montantInitial;
    }

    public int getNbArmure() {
        return nbArmure;
    }

}
