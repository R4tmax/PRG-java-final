package gameworld;

import auxiliary.ConsoleColors;
import knight.TheKnight;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Martin Kadlec
 * @version Last refactored on 13.06.2022.
 *
 * <p>
 *     This class holds all of the room oriented
 *     interactions player can execute in the
 *     game world.
 *     As such, it's primary function is to facilitate interactions
 *     with NPCs. Which is marked by corresponding.
 *     Room type attribute in the Room class.
 *     In other cases it will simply provide flavor text to the player.
 * </p>
 * @see Room
 * @see RoomType
 */
public class Interactions {
    private static int iteratorInfo;
    private static final ArrayList<String> scoutInfo = new ArrayList<>();
    private static int iteratorWeaponsmith = 1;
    private static int iteratorArmorsmith = 1;

    /**
     * Factory function for the Scout dialogues.
     */
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

    /**
     * This function is invoked when player enters the
     * 'interact' command in the main control loop.
     * depending on the room occupied, additional functions are called.
     *
     * @param input Scanner object passed from the main
     *
     * @see Room
     * @see RoomType
     */
    public static void attemptInteraction (Scanner input) {
        RoomType type = Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).roomBehavior;

        switch (type) {
            case RECON -> System.out.println("Hmm, no one is around.");
            case TALKABLE -> getInfo();
            case REST_AREA -> restAtInn(input);
            case TRADABLE -> trade(input);
            case HOSTILE -> System.out.println("Shivers run down your spine. Some unnatural darkness still looms here.");
            default -> System.out.println("Roomtype.error!");
        }
    }

    /**
     * This function is called on the 'restable'
     * rooms. It asks the player whether he is okay
     * with the price for using the rest services, and if so
     * resets his health and mana to the maximal values.
     *
     * @param input Scanner object passed from the main.
     * @see TheKnight
     */
    private static void restAtInn (Scanner input) {
        int restingPrice = 300;
        System.out.println("Do you want to rest and regain strength?");
        System.out.println("It will cost you " + ConsoleColors.YELLOW + "300" + ConsoleColors.RESET + " gold" );
        System.out.println("Y/N");
        String command = input.nextLine().toLowerCase();
        command = command.replaceAll("\\s","");

        switch (command) {
            case "y" -> {
                if (TheKnight.getGoldHeld() < restingPrice) {
                    System.out.println("You do not hav enough gold!");
                } else {
                    TheKnight.setCurrentHealth(TheKnight.MAX_HEALTH);
                    TheKnight.setCurrentMana(TheKnight.MAX_MANA);
                    TheKnight.setDamage(TheKnight.getDamage()+1);
                    System.out.println("You feel well rested and ready to fight!");
                    System.out.println("Your HP and MP have been replenished, and you are slightly stronger!");
                    TheKnight.setGoldHeld(TheKnight.getGoldHeld() - restingPrice);
                }
            }
            case "n" -> System.out.println("Some other time then!");
            default -> System.out.println("Unknown input, please answer with Y or N.");
        }

    }

    /**
     * This method is called when interacting with
     * 'talkable' rooms. It simply prints
     * String info for the player, this contains tips
     * about the gameplay of tha game.
     *
     * Take note that the method invokes two calls
     * to static values
     * IteratorInfo - which is simple integer used for iterating over
     * the ArrayList holding the strings.
     * and
     * scoutInfo - the arrayList itself.
     *
     * @throws ArrayIndexOutOfBoundsException nonStandard behavior, should only occur on erroneous builds of the application
     *                                          programme by default checks for iterator position and sets it back to 0
     *                                          on outOfBounds indexes.
     */
    private static void getInfo () {
        try {
            System.out.println(scoutInfo.get(iteratorInfo));
            iteratorInfo++;
            if (iteratorInfo >= scoutInfo.size()) iteratorInfo = 0;
        } catch (Exception e) {
            System.out.println("Incorrect array handling, forcing iteration restart.");
            iteratorInfo = 0;
        }
    }

    /**
     * Method is invoked when interact is called
     * on 'tradeable' rooms.
     *
     * Method uses while loop to simplify repeated trading
     * As such, the method expects specific user input for breaking the loop.
     *
     * @param input Scanner object passed from the main
     */
    private static void trade (Scanner input) {

        System.out.println("Locals do not have much, but they can help you.");
        System.out.println("There is an " + ConsoleColors.SIMPLE_BOLD + ConsoleColors.SIMPLE_UNDERLINE + "armorsmith" + ConsoleColors.RESET +  " who might help you retrofit and pad your"+ ConsoleColors.GREEN + " armor." + ConsoleColors.RESET);
        System.out.println("There is also a " +  ConsoleColors.SIMPLE_BOLD + ConsoleColors.SIMPLE_UNDERLINE + "huntsman" + ConsoleColors.RESET + " who can help you sharpen your" + ConsoleColors.PURPLE + " arms." + ConsoleColors.RESET);
        System.out.println("For a price... of course.");

        while (true) {
            System.out.println("Who do you want to trade with?");
            System.out.println("You could always LEAVE and come back later.");

            String command = input.nextLine().toLowerCase();
            command = command.replaceAll("\\s", "");

            switch (command) {
                case "armorsmith" -> armorUpgrade(input);
                case "huntsman" -> weaponUpgrade(input);
                case "status" -> TheKnight.printKnightStatusExploration();
                case "leave" ->
                {   System.out.println("They will be here, if you change your mind");
                    return;
                }
                default -> System.out.println("You can use commands ARMORSMITH, HUNTSMAN,STATUS or LEAVE.!\n During this interaction.");
            }
        }
    }

    /**
     * Extension of the trade method.
     * If the player specified through input that he is seeking
     * armor upgrades, this method is called and asks for confirmation.
     * If confirmation is given. It modifies TheKnight attributes accordingly.
     *
     * Take note that the function modifies static value - iteratorArmorsmith,
     * which is an integer value used as a price modifier to create diminishing
     * returns on repeated upgrades.
     *
     * @param input Scanner object passed by the main function
     * @see TheKnight
     */
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
                if (TheKnight.getGoldHeld() < price) {
                    System.out.println("You do not hav enough gold!");
                } else {
                    TheKnight.setArmor(TheKnight.getArmor()+2);
                    TheKnight.setGoldHeld(TheKnight.getGoldHeld() - price);
                    iteratorArmorsmith++;
                }
            }
            case "n" -> System.out.println("Some other time then!");
            default -> System.out.println("Unknown input, please answer with Y or N.");
        }
    }


    /**
     * Extension of the trade method.
     * If the player specified through input that he is seeking
     * weapon upgrades, this method is called and asks for confirmation.
     * If confirmation is given. It modifies TheKnight attributes accordingly.
     *
     * Take note that the function modifies static value - iteratorWeaponsmith,
     * which is an integer value used as a price modifier to create diminishing
     * returns on repeated upgrades.
     *
     * @param input Scanner object passed by the main function
     * @see TheKnight
     */
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
                if (TheKnight.getGoldHeld() < price) {
                    System.out.println("You do not hav enough gold!");
                } else {
                    TheKnight.setDamage(TheKnight.getDamage()+5);
                    TheKnight.setGoldHeld(TheKnight.getGoldHeld() - price);
                    iteratorWeaponsmith++;
                }
            }
            case "n" -> System.out.println("Some other time then!");
            default -> System.out.println("Unknown input, please answer with Y or N.");
        }
    }


}


