package ca.udem.ift1025.tp1.corrige;

import java.util.*;
import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommand;
import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommandSystem;

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
        inventory inventory = new inventory();

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
                        System.out.println("pas assez d'argent");
                    } else if (armor > bank.getArmor()) {
                        System.out.println("pass assez d'armures");
                    } else {

                        switch (categorie) {
                            case 0 -> {
                                Cat0 cat0 = new Cat0(name, cost, armor, hp);
                                inventory.addToList(cat0);
                            }
                            case 1 -> {
                                Cat1 cat1 = new Cat1(name, cost, armor, hp);
                                inventory.addToList(cat1);
                            }
                            case 2 -> {
                                Cat2 cat2 = new Cat2(name, cost, armor, hp);
                                inventory.addToList(cat2);
                            }

                            case 3 -> {
                                Cat3 cat3 = new Cat3(name, cost, armor, hp);
                                inventory.addToList(cat3);
                            }
                            case 4 -> {
                                Cat4 cat4 = new Cat4(name, cost, armor, hp);
                                inventory.addToList(cat4);
                            }
                        }
                    }

                    // TODO
                }
                case "buy-armor" -> {
                    // TODO
                }
                case "do-quest" -> {
                    int categorie = command.nextInt();
                    double hp = command.nextDouble();
                    int money = command.nextInt();
                    int armor = command.nextInt();
                    // trouvé le héro avec la meme categorie que notre quest, il va itere chaque hero de notre liste
                    // si le hero est egale ou plus petit il va le prendre comme quest hero. Mais a la fin de notre loop
                    // on va avoir le hero avec la categorie la plus proche de celle de la quest
                    Hero heroQuest = null;
                    for (Hero hero : inventory.getInventoryList()) {
                        if (hero.getCategory() <= categorie) {
                            if ( hero.getCategory() > heroQuest.getCategory() || heroQuest == null) {
                                heroQuest = hero;
                            }
                        }
                    }
                    // si il avait aucun hero avec une categorie <= a la quest
                    if (heroQuest == null) {
                        System.out.println("Pas de héroes compatible pour cette quete");
                    }
                    //  si le hero a une categorie plus petit que la quest il faut lui enleve des hp
                    if (heroQuest.getCategory() < categorie) {
                        double newHp = hp - ((heroQuest.getCategory()) - categorie)*1.5;
                        for (Hero hero : inventory.getInventoryList()) {
                            if (heroQuest.getName().equals(hero.getName())) {
                                hero.setHp(newHp);
                            }
                    }
                    }
                    // si le hero a pas assez de hp pour la quest il faut echoue la quest
                    if (heroQuest.getHp() < hp) {
                        System.out.println("Quete echoué, Hero n'avait pas assez de hp");
                        inventory.removefromlist(heroQuest);
                    }

                }
                case "train-hero" -> {
                    String name = command.nextString();
                    // on va chercher dans notre inventory pour un hero avec le meme nom qque le parametre
                    for (Hero hero : inventory.getInventoryList()) {
                        if (hero.getName().equals(name)) {
                            //calcul du cout d'un upgrade en argent
                            double cost = 20*(Math.log(hero.getCategory()+10));
                            // calcul du cout d'un upgrade en armure
                            double armCost = Math.log(hero.getCategory()+10);
                            if(cost < bank.getArgent() && armCost < bank.getArmor()) {
                                // update le hp du hero
                                double maxHp = hero.getHp() * 1.5;
                                hero.setHp(maxHp);
                                // enleve les couts du update a notre banque
                                bank.enleveArgent(cost);
                                bank.enleveArmore(armCost);
                            } else {
                                System.out.println("pas assez de ressource pour effectué l'amelioration");
                            }
                        }
                    }
                    System.out.println("pas de héroes avec ce nom dans l'inventaire");
                }
            }
        }
    }


        public static Guilde makeGuilde (GuildCommand command){
            double montantInitial = command.nextDouble();
            int nbArmures = command.nextInt();
            return new Guilde(montantInitial, nbArmures);
        }
    }

