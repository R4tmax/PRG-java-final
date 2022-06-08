package gameworld;

import enemies.TheBrute;

public class Map {
    public static Room [][] gameMap = new Room[5][5];

    public static Room[][] getGameMap() {
        return gameMap;
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
        Map.gameMap[0][0] = new Room("Severozápadní pahorkatina", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[0][1] = new Room("Jeskynní úbočí", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[0][2] = new Room("HNÍZDO MONSTER", "Odsud pochází všechna monstra, co zužují kraj! Poraž matariarchu a skonči s tím!", true , null, null, RoomType.HOSTILE);
        Map.gameMap[0][3] = new Room("Roklinka", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[0][4] = new Room("Severovýchodní pahorkatina", "placeholder", false , null, null, RoomType.RECON);

        //Second row
        Map.gameMap[1][0] = new Room("Okraj lesa", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[1][1] = new Room("Hluboký les", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[1][2] = new Room("Hluboký les", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[1][3] = new Room("Hluboký les", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[1][4] = new Room("Podmáčený les", "placeholder", false , null, null, RoomType.RECON);

        //third row
        Map.gameMap[2][0] = new Room("PODIVNÁ MÝTINA", "placeholder", false , null, new TheBrute("Brute",50,2), RoomType.HOSTILE);
        Map.gameMap[2][1] = new Room("Královská cesta", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[2][2] = new Room("Královská cesta", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[2][3] = new Room("Královská cesta", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[2][4] = new Room("MOČÁL", "placeholder", false , null, null, RoomType.HOSTILE);

        //fourth row
        Map.gameMap[3][0] = new Room("Polní cesta", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[3][1] = new Room("Pšeničné pole", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[3][2] = new Room("Louka", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[3][3] = new Room("Nížina", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[3][4] = new Room("Říční meandr", "placeholder", false , null, null, RoomType.RECON);

        //fifth row
        Map.gameMap[4][0] = new Room("Královská cesta", "placeholder", false , null, null, RoomType.RECON);
        Map.gameMap[4][1] = new Room("Hostinec", "placeholder", false , null, null, RoomType.REST_AREA);
        Map.gameMap[4][2] = new Room("Nádvoří vesnice", "Nádvoří zdejší vesnice, momentálně zde táboří členové a zvědi družiny. Severním směrem se rozléhají zdejší louky a pole. Většina zdejších proudí buďto do nedalekého hostince u cesty, nebo směrem k tržišti, hlouběji ve vsi, směrem na východ.", false , null, null, RoomType.TALKABLE);
        Map.gameMap[4][3] = new Room("Tržiště", "placeholder", false , null, null, RoomType.TRADABLE);
        Map.gameMap[4][4] = new Room("Břeh řeky", "placeholder", false , null, null, RoomType.RECON);


    }
}
