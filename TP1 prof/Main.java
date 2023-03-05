package ca.udem.ift1025.tp1.corrige;

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
                    } else if ( armor > bank.getArmor()) {
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
                case "buy-armor" ->{
                    // TODO
                }
                case "do-quest" -> {
                    // TODO
                }
                case "train-hero" -> {
                    // TODO
                }
            }
        }
    }


    public static Guilde makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}