package gameworld;

import enemies.Monster;
import enemies.TheBrute;
import enemies.TheHag;
import enemies.TheMatriarch;
import items.Consumable;
import items.ConsumableType;
import items.MoneyLoot;
import knight.TheKnight;

public class Map {
    public static Room [][] gameMap = new Room[5][5];

    public static Room getCurrentPosition(int x, int y) {
        return gameMap[x][y];
    }


    public static void printPosition () {
        System.out.println(Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).name);
        System.out.println(Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).description);
    }

    public static void printMap () {
        for (int i = 0; i < Map.gameMap.length; i++) {
            for (int j = 0; j < Map.gameMap.length; j++) {
                System.out.println("Current coordinates: X-" + i + "|" + "Y-" + j + " " + Map.gameMap[i][j]);
            }
            System.out.println();
        }
    }

    public static void fillMap () {

        //First row - Northernmost
        Map.gameMap[0][0] = new Room("Severozápadní pahorkatina", "placeholder", false , new Consumable("Armor-smith tools",1,ConsumableType.ARMOR_BOOST), null, RoomType.RECON);
        Map.gameMap[0][1] = new Room("Jeskynní úbočí", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[0][2] = new Room("HNÍZDO MONSTER", "Odsud pochází všechna monstra, co zužují kraj! Poraž matariarchu a skonči s tím!", true , null, new TheMatriarch("Matriarch",800, 8), RoomType.HOSTILE);
        Map.gameMap[0][3] = new Room("Roklinka", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[0][4] = new Room("Severovýchodní pahorkatina", "placeholder", false , new Consumable("Armor-smith tools",1,ConsumableType.ARMOR_BOOST), null, RoomType.RECON);

        //Second row
        Map.gameMap[1][0] = new Room("Okraj lesa", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[1][1] = new Room("Hluboký les", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[1][2] = new Room("Hluboký les", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[1][3] = new Room("Hluboký les", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[1][4] = new Room("Podmáčený les", "placeholder", false , null, null, RoomType.RECON);

        //third row
        Map.gameMap[2][0] = new Room("PODIVNÁ MÝTINA", "placeholder", false , null, new TheBrute("Brute",1000,5,1000), RoomType.HOSTILE);
        Map.gameMap[2][1] = new Room("Křovinatá pláň", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[2][2] = new Room("Okraj lesa", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[2][3] = new Room("Okraj mokřadu", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[2][4] = new Room("MOČÁL", "placeholder", false , null, new TheHag("Hag", 600,5,750), RoomType.HOSTILE);

        //fourth row
        Map.gameMap[3][0] = new Room("Polní cesta", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[3][1] = new Room("Pšeničné pole", "placeholder", false , new Consumable("Mouldy bread",-5,ConsumableType.HEALTH_FILL), null, RoomType.RECON);
        Map.gameMap[3][2] = new Room("Louka", "placeholder", false , new MoneyLoot("Rare herb",50), null, RoomType.RECON);
        Map.gameMap[3][3] = new Room("Nížina", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[3][4] = new Room("Říční meandr", "placeholder", false , null, null, RoomType.RECON);

        //fifth row
        Map.gameMap[4][0] = new Room("The Kings Road", """
                The Kings Road. Your company arrived through here couple of days ago.
                You turn northward, and notice couple of smashed supply carts bearing
                Kings insignia. They are reduced to splinters, perhaps still looking around though?
                You know, that the Village is straight east from the last crossroad, and the attacks were reported due north.
                """, false , new Consumable("Whetting stone",5,ConsumableType.DAMAGE_BOOST), null, RoomType.RECON);
        Map.gameMap[4][1] = new Room("The Tavern", """
                A bunch of locals from the surrounding fields are drinking
                in front of what seems to be sturdy built tavern.
                You get the impression that you could rest here, if you needed to.
                Kings Road is further westward, while golden field lies to your north.
                You can still make out insignias on the tents in the Village down east.
                """, false , null, null, RoomType.REST_AREA);
        Map.gameMap[4][2] = new Room("Village courtyard", """
                Courtyard of the local village.
                It is distant, isolated, but surprisingly robust and nice looking.
                Close proximity to the Kings Road guarantee good business.
                But fear is in the eyes of the locals. 
                Scout of your expedition is standing near the the tents and the crates marked with
                insignias of the King.
                In front of you, to the north, mountain range stands erect.
                Most of the Villagers are either to the east or west, at the inn, or the market.
                """, false , new Consumable("Blessed Health potion",200 ,ConsumableType.HEALTH_FILL), null, RoomType.TALKABLE);
        Map.gameMap[4][3] = new Room("The Market", """
                The market is full of people, maybe you could talk with them?
                Further east, a vague outline of the river can be seen.
                Some of the villagers are heading back to the village center, to the west.
                """, false , null, null, RoomType.TRADABLE);
        Map.gameMap[4][4] = new Room("River bank", """
                The village commotion can no longer be heard.
                Instead a gentle rush of the river and a singing birds make your company.
                You should head north, you think, when some weird herbs catch your attention.
                Maybe you could try picking them up?
                """, false , new Consumable("Blue herb",25,ConsumableType.MANA_FILL), null, RoomType.RECON);


    }
}
