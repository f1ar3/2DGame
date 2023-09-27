package main;

import monster.Monster;
import object.ObjectBoots;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setObject() {
        gamePanel.object[0] = new ObjectKey(gamePanel);
        gamePanel.object[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.object[0].worldY = 7 * gamePanel.tileSize;

        gamePanel.object[1] = new ObjectKey(gamePanel);
        gamePanel.object[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.object[1].worldY = 40 * gamePanel.tileSize;

        gamePanel.object[2] = new ObjectKey(gamePanel);
        gamePanel.object[2].worldX = 38 * gamePanel.tileSize;
        gamePanel.object[2].worldY = 8 * gamePanel.tileSize;

        gamePanel.object[3] = new ObjectDoor(gamePanel);
        gamePanel.object[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.object[3].worldY = 12 * gamePanel.tileSize;

        gamePanel.object[4] = new ObjectDoor(gamePanel);
        gamePanel.object[4].worldX = 8 * gamePanel.tileSize;
        gamePanel.object[4].worldY = 28 * gamePanel.tileSize;

        gamePanel.object[5] = new ObjectDoor(gamePanel);
        gamePanel.object[5].worldX = 12 * gamePanel.tileSize;
        gamePanel.object[5].worldY = 22 * gamePanel.tileSize;

        gamePanel.object[6] = new ObjectChest(gamePanel);
        gamePanel.object[6].worldX = 10 * gamePanel.tileSize;
        gamePanel.object[6].worldY = 7 * gamePanel.tileSize;

        gamePanel.object[7] = new ObjectBoots(gamePanel);
        gamePanel.object[7].worldX = 37 * gamePanel.tileSize;
        gamePanel.object[7].worldY = 42 * gamePanel.tileSize;

    }
    public void setMonster() {
        gamePanel.monster[0] = new Monster(gamePanel);
        gamePanel.monster[0].worldX = gamePanel.tileSize * 21;
        gamePanel.monster[0].worldY = gamePanel.tileSize * 21;

        gamePanel.monster[1] = new Monster(gamePanel);
        gamePanel.monster[1].worldX = gamePanel.tileSize * 37;
        gamePanel.monster[1].worldY = gamePanel.tileSize * 8;
    }
}
