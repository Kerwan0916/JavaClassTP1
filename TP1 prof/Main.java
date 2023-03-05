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
        erreur erreur = new erreur();
        // il reste a trouvé des bugs et checker comment output comme dans le fichier d'instruction
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
                        erreur.addToErreur("-pas assez d'argent");
                    } else if (armor > bank.getArmor()) {
                        erreur.addToErreur("-pas assez d'armures");
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
                        if (hero.getCategory() >= categorie) {
                            if (heroQuest == null){
                                heroQuest = hero;
                            }
                            else if ( hero.getCategory() > heroQuest.getCategory()) {
                                heroQuest = hero;
                            }
                        }
                    }
                    // si il avait aucun hero avec une categorie <= a la quest
                    if (heroQuest == null) {
                        erreur.addToErreur("-Pas de héroes compatible pour cette quete");
                    }
                    //  si le hero a une categorie plus petit que la quest il faut lui enleve des hp
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
                    // si le hero a pas assez de hp pour la quest il faut echoue la quest
                    if (heroQuest != null) {
                        if (heroQuest.getHp() < hp) {
                            erreur.addToErreur("-Quete echoué, Hero n'avait pas assez de hp");
                            inventory.removefromlist(heroQuest);
                        }
                    }

                }
                case "train-hero" -> {
                    String name = command.nextString();
                    boolean upgradeHero = false;
                    // on va chercher dans notre inventory pour un hero avec le meme nom qque le parametre
                    for (Hero hero : inventory.getInventoryList()) {
                        if (hero.getName().equals(name)) {
                            upgradeHero = true;
                            //calcul du cout d'un upgrade en argent
                            double cost = 20*(Math.log(hero.getCategory()+10));
                            // calcul du cout d'un upgrade en armure
                            double armCost = Math.ceil(Math.log(hero.getCategory()+10));
                            if(cost < bank.getArgent() && armCost < bank.getArmor()) {
                                // update le hp du hero
                                double maxHp = hero.getHp() * 1.5;
                                hero.setHp(maxHp);
                                hero.setCategory(hero.getCategory()+1);
                                // enleve les couts du update a notre banque
                                bank.enleveArgent(cost);
                                bank.enleveArmore(armCost);
                            } else {
                                erreur.addToErreur("-Pas assez de ressource pour effectué l'amelioration de" + hero.getName());
                            }

                        }

                    }
                    if (upgradeHero == false) {
                        erreur.addToErreur("-" + name+ " ne fait pas partie de l'inventaire");
                    }

                }
            }
        }
        System.out.println("guilde bank account: gold :" + Math.round(bank.getArgent()) + " armours : " + bank.getArmor());
        System.out.println("heroes:");
        for (Hero hero : inventory.getInventoryList()) {
            System.out.println("-"+hero.getName()+ " level: " + hero.getCategory() + ",Hp: " + hero.getHp());
        }
        System.out.println("Erreurs:");
        for (String s : erreur.geterreur()){
            System.out.println(s);
        }
    }



        public static Guilde makeGuilde (GuildCommand command){
            double montantInitial = command.nextDouble();
            int nbArmures = command.nextInt();
            return new Guilde(montantInitial, nbArmures);
        }
    }

