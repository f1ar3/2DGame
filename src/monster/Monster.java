package monster;

import entity.Entity;
import entity.Player;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Monster extends Entity {
    GamePanel gamePanel;
    public Monster(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        type = 1;
        name = "Skeleton";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        direction = "down";

        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/skeletonlord_up_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/monster/skeletonlord_up_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/monster/skeletonlord_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/monster/skeletonlord_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/monster/skeletonlord_left_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/monster/skeletonlord_left_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/monster/skeletonlord_right_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/monster/skeletonlord_right_2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
