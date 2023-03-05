package ca.udem.ift1025.tp1.corrige;
import java.util.ArrayList;

public class erreur {
    public ArrayList<String> erreur;
    public erreur() {
        erreur = new ArrayList<String>();
    }
    public void addToErreur(String phrase) {
        erreur.add(phrase);
    }
    public ArrayList<String> geterreur() {
        return erreur;
    }
}
