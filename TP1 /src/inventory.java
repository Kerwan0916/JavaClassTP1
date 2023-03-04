import java.util.ArrayList;

public class inventory {
    public ArrayList<Hero> inventoryList;
    public inventory() {
        inventoryList = new ArrayList<Hero>();
    }
    public void addToList(Hero hero) {
        inventoryList.add(hero);
    }



}
