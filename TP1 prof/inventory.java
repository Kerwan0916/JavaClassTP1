package ca.udem.ift1025.tp1.corrige;
import java.util.ArrayList;
public class inventory {
    public ArrayList<Hero> inventoryList;
    public inventory() {
        inventoryList = new ArrayList<Hero>();
    }
    public void addToList(Hero hero) {
        inventoryList.add(hero);
    }
    public void removefromlist(Hero hero) {
        inventoryList.remove((hero));
    }

    public ArrayList<Hero> getInventoryList() {
        return inventoryList;
    }
}
