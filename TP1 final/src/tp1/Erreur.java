package tp1;
import java.util.ArrayList;

public class Erreur {
    public ArrayList<String> erreur;
    public Erreur() {
        erreur = new ArrayList<String>();
    }
    public void addToErreur(String phrase) {
        erreur.add(phrase);
    }
    public ArrayList<String> getErreur() {
        return erreur;
    }

    public boolean isEmpty() {
        if (erreur.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
