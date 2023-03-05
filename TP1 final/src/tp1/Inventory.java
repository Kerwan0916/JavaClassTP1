package tp1;

import java.util.ArrayList;
public class Inventory {
    public ArrayList<Hero> inventoryList;
    public Inventory() {
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
