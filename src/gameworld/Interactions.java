package gameworld;

import auxiliary.ConsoleColors;
import knight.TheKnight;

import java.util.ArrayList;
import java.util.Scanner;

public class Interactions {
    private static int iteratorInfo;
    private static final ArrayList<String> scoutInfo = new ArrayList<>();
    private static int iteratorWeaponsmith = 1;
    private static int iteratorArmorsmith = 1;

    public static void initializeDialogues() {
        scoutInfo.add("Hail, Knight of the King! *He hits his chest-plate with his right arm*");
        scoutInfo.add("""
                       We are in the southernmost part of the area.
                       All of the monsters should be up north.
                       """);
        scoutInfo.add("""
                        Monsters will be fast, you better be ready to fight to the end, once you find them.
                        (You cannot escape combat, once it begins.)
                        """);
        scoutInfo.add("""
                        Monster attacks disrupted a lot of trade to the west.
                        You might find useful items in that direction.
                        """);
        scoutInfo.add("""
                        Whatever was there, was big.
                        It had to take out well guarded convoys.
                        Be sure to not engage, whatever it is, until you gathered some strength!
                        """);
        scoutInfo.add("""
                        If you are uncertain, head east first.
                        Something feels wrong with that area, but
                        no attacks were reported there.
                        """);
        scoutInfo.add("""
                        Be sure the familiarize yourself with your spell list.
                        You don't want to make a mistake in the heat of the moment.
                        If you cast the wrong spell at the wrong time, the tables might turn
                        very dramatically!
                        """);
        scoutInfo.add("""
                        You could always just avoid combat and survey the area first, you might find resources to salvage.
                        There are some merchants due east, which might help you with that.
                        """);
        scoutInfo.add("One last thing. Ecclesiastic chaplains prepared this blessed concoction for your quest, good luck!");
    }

    public static void attemptInteraction (Scanner input) {
        RoomType type = Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomBehavior;

        switch (type) {
            case RECON -> System.out.println("Hmm, no one is around.");
            case TALKABLE -> getInfo();
            case REST_AREA -> restAtInn(input);
            case TRADABLE -> trade(input);
            case HOSTILE -> System.out.println("Shivers run down your spine. Some unnatural darkness still looms here.");
            default -> System.out.println("Roomtype.error!");
        }
    }

    public static void restAtInn (Scanner input) {
        int restingPrice = 300;
        System.out.println("Do you want to rest and regain strength?");
        System.out.println("It will cost you " +ConsoleColors.YELLOW + "300 gold" + ConsoleColors.RESET);
        System.out.println("Y/N");
        String command = input.nextLine().toLowerCase();
        command = command.replaceAll("\\s","");

        switch (command) {
            case "y" -> {
                if (TheKnight.goldHeld < restingPrice) {
                    System.out.println("You do not hav enough gold!");
                } else {
                    TheKnight.currentHealth = TheKnight.MAX_HEALTH;
                    TheKnight.currentMana = TheKnight.MAX_MANA;
                    TheKnight.damage += 1;
                    System.out.println("You feel well rested and ready to fight!");
                    System.out.println("Your HP and MP have been replenished, and you are slightly stronger!");
                    TheKnight.goldHeld -= restingPrice;
                }
            }
            case "n" -> System.out.println("Some other time then!");
            default -> System.out.println("Unknown input, please answer with Y or N.");
        }

    }

    public static void getInfo () {
        try {
            System.out.println(scoutInfo.get(iteratorInfo));
            iteratorInfo++;
            if (iteratorInfo >= scoutInfo.size()) iteratorInfo = 0;
        } catch (Exception e) {
            System.out.println("Incorrect array handling, forcing iteration restart.");
            iteratorInfo = 0;
        }
    }

    public static void trade (Scanner input) {

        System.out.println("Locals do not have much, but they can help you.");
        System.out.println("There is an armorsmith who might help you retrofit and pad your"+ ConsoleColors.GREEN + " armor." + ConsoleColors.RESET);
        System.out.println("There is also a huntsman who can help you sharpen your" + ConsoleColors.PURPLE + " arms." + ConsoleColors.RESET);
        System.out.println("For a price... of course.");

        while (true) {
            System.out.println("Who do you want to trade with?");
            System.out.println("You could always LEAVE and come back later.");

            String command = input.nextLine().toLowerCase();
            command = command.replaceAll("\\s", "");

            switch (command) {
                case "armorsmith" -> armorUpgrade(input);
                case "huntsman" -> weaponUpgrade(input);
                case "leave" ->
                {   System.out.println("They will be here, if you change your mind");
                    return;
                }
                default -> System.out.println("Unknown command!");
            }
        }
    }

    private static void armorUpgrade (Scanner input) {
        int defaultPrice = 125;
        int price = defaultPrice * iteratorArmorsmith;
        System.out.println("Do you want to pay for armor upgrades?");
        System.out.println("It will cost you " + ConsoleColors.YELLOW + price + ConsoleColors.RESET + " gold. Each upgrade will be more expensive");
        System.out.println("Y/N");
        String command = input.nextLine().toLowerCase();
        command = command.replaceAll("\\s", "");

        switch (command) {
            case "y" -> {
                if (TheKnight.goldHeld < price) {
                    System.out.println("You do not hav enough gold!");
                } else {
                    TheKnight.armor += 2;
                    TheKnight.goldHeld -= price;
                    iteratorArmorsmith++;
                }
            }
            case "n" -> System.out.println("Some other time then!");
            default -> System.out.println("Unknown input, please answer with Y or N.");
        }
    }

    private static void weaponUpgrade (Scanner input) {
        int defaultPrice = 125;
        int price = defaultPrice * iteratorWeaponsmith;
        System.out.println("Do you want to pay for weapon upgrades?");
        System.out.println("It will cost you " + ConsoleColors.YELLOW + price + ConsoleColors.RESET +" gold. Each upgrade will be more expensive");
        System.out.println("Y/N");
        String command = input.nextLine().toLowerCase();
        command = command.replaceAll("\\s", "");

        switch (command) {
            case "y" -> {
                if (TheKnight.goldHeld < price) {
                    System.out.println("You do not hav enough gold!");
                } else {
                    TheKnight.damage += 5;
                    TheKnight.goldHeld -= price;
                    iteratorWeaponsmith++;
                }
            }
            case "n" -> System.out.println("Some other time then!");
            default -> System.out.println("Unknown input, please answer with Y or N.");
        }
    }


}


