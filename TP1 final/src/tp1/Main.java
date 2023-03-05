package tp1;

import guildcommands.GuildCommand;
import guildcommands.GuildCommandSystem;
public class Main {
    /**
     * Args: array with
     * <ol>
     *     <li>guild:&lt;montant initial&gt;,&lt;armures initiales&gt;</li>
     * </ol>
     *
     * @param args
     */
    public static void main(String[] args) {
        GuildCommandSystem guildCommandSystem = new GuildCommandSystem(args);

        Guilde maGuilde = makeGuilde(guildCommandSystem.actualCommand());
        Bank bank = new Bank(maGuilde.getMontantInitial(), maGuilde.getNbArmure());
        Inventory inventory = new Inventory();
        Erreur erreur = new Erreur();

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                    String name = command.nextString();
                    int categorie = command.nextInt();
                    double cost = command.nextDouble();
                    int armor = command.nextInt();
                    double hp = command.nextDouble();

                    if (cost > bank.getArgent()) {
                        erreur.addToErreur("-Pas assez d'argent");
                    } else if (armor > bank.getArmor()) {
                        erreur.addToErreur("-Pas assez d'armures");
                    } else {

                        switch (categorie) {
                            case 0 -> {
                                Cat0 cat0 = new Cat0(name, cost, armor, hp);
                                inventory.addToList(cat0);
                                bank.enleveArgent(cost);
                                bank.enleveArmore(armor);
                            }
                            case 1 -> {
                                Cat1 cat1 = new Cat1(name, cost, armor, hp);
                                inventory.addToList(cat1);
                                bank.enleveArgent(cost);
                                bank.enleveArmore(armor);
                            }
                            case 2 -> {
                                Cat2 cat2 = new Cat2(name, cost, armor, hp);
                                inventory.addToList(cat2);
                                bank.enleveArgent(cost);
                                bank.enleveArmore(armor);
                            }

                            case 3 -> {
                                Cat3 cat3 = new Cat3(name, cost, armor, hp);
                                inventory.addToList(cat3);
                                bank.enleveArgent(cost);
                                bank.enleveArmore(armor);
                            }
                            case 4 -> {
                                Cat4 cat4 = new Cat4(name, cost, armor, hp);
                                inventory.addToList(cat4);
                                bank.enleveArgent(cost);
                                bank.enleveArmore(armor);
                            }
                        }
                    }
                }
                case "buy-armor" -> {
                    int armorQuantity = command.nextInt();
                    int armorPrice = command.nextInt();
                    int armorCost = armorQuantity * armorPrice;

                    if (armorCost < bank.getArgent()) {
                        bank.enleveArgent(armorCost);
                        bank.ajouterArmure(armorQuantity);
                    } else {
                        erreur.addToErreur("Pas assez d'argent pour acheter autant d'armures!");
                    }

                }
                case "do-quest" -> {
                    int categorie = command.nextInt();
                    double hp = command.nextDouble();
                    double money = command.nextInt();
                    int armor = command.nextInt();
                    // trouver le héro avec la même catégorie que notre quête, il va itéré chaque hero de notre liste
                    // si la catégorie du héro est égale ou plus petite il va le prendre comme quest hero. Mais à la fin de notre loop
                    // on va avoir le héro avec la catégorie la plus proche de celle de la quête
                    Hero heroQuest = null;
                    for (Hero hero : inventory.getInventoryList()) {
                        if (hero.getCategory() >= categorie) {
                            if (heroQuest == null){
                                heroQuest = hero;
                            }
                            else if ( hero.getCategory() > heroQuest.getCategory()) {
                                heroQuest = hero;
                            }
                        }
                    }
                    // s'il n'y avait aucun héro avec une catégorie >= a la quest
                    if (heroQuest == null) {
                        erreur.addToErreur("-Pas de héros compatibles pour cette quête");
                    }
                    //  si le héro a une catégorie plus petite que la quête il faut lui enlève des HP
                    if(heroQuest != null) {
                        if (heroQuest.getCategory() < categorie) {
                            double newHp = hp - ((heroQuest.getCategory()) - categorie)*1.5;
                            for (Hero hero : inventory.getInventoryList()) {
                                if (heroQuest.getName().equals(hero.getName())) {
                                    hero.setHp(newHp);
                                }
                            }
                        }
                        for (Hero hero : inventory.getInventoryList()) {
                            if (heroQuest.getName().equals(hero.getName())) {
                                hero.setHp(hero.getHp()-hp);
                            }
                        }
                        bank.ajouterArgent(money);
                        bank.ajouterArmure(armor);
                    }
                    // si le hero n'a pas assez de HP pour la quête il faut échoue la quête
                    if (heroQuest != null) {
                        if (heroQuest.getHp() < hp) {
                            erreur.addToErreur("-Quête echouée, le héro n'avait pas assez de HP");
                            inventory.removefromlist(heroQuest);
                        }
                    }

                }
                case "train-hero" -> {
                    String name = command.nextString();
                    boolean upgradeHero = false;
                    // on va chercher dans notre inventory pour un héro avec le meme nom que le paramètre
                    for (Hero hero : inventory.getInventoryList()) {
                        if (hero.getName().equals(name)) {
                            upgradeHero = true;
                            //calcul du coût d'un upgrade en argent
                            double cost = 20*(Math.log(hero.getCategory()+10));
                            // calcul du coût d'un upgrade en armure
                            double armCost = Math.ceil(Math.log(hero.getCategory()+10));
                            if(cost < bank.getArgent() && armCost < bank.getArmor()) {
                                // update le hp du hero
                                double maxHp = hero.getHp() * 1.5;
                                hero.setHp(maxHp);
                                hero.setCategory(hero.getCategory()+1);
                                // enlève les coûts du update à notre banque
                                bank.enleveArgent(cost);
                                bank.enleveArmore(armCost);
                            } else {
                                erreur.addToErreur("-Pas assez de ressources pour effectuer l'amélioration de " + hero.getName());
                            }

                        }

                    }
                    if (upgradeHero == false) {
                        erreur.addToErreur("-" + name+ " ne fait pas partie de l'inventaire");
                    }

                }
            }
        }
        // opérations pour arrondir le montant de gold de la banque à une décimale près au lieu des 14 que nous avions
        // méthode trouvée en ligne
        double amount = bank.getArgent();
        String formattedAmount = String.format("%.1f", amount);
        double roundedAmount = Double.parseDouble(formattedAmount);

        System.out.println("Guild Bank account: " + roundedAmount + " gold & " + bank.getArmor() + " armours");
        System.out.println("Heroes:");
        for (Hero hero : inventory.getInventoryList()) {
            System.out.println("-"+hero.getName()+ ": level = " + hero.getCategory() + ", Hp = " + hero.getHp());
        }
        if (!erreur.isEmpty()) {
            System.out.println("Erreurs:");
        }

        for (String s : erreur.getErreur()) {
                System.out.println(s);
            }

    }

    public static Guilde makeGuilde (GuildCommand command){
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}