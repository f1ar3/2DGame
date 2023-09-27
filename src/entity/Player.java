package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";

        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

            up1 = setup("/player/boy_up_1", gamePanel.tileSize, gamePanel.tileSize);
            up2 = setup("/player/boy_up_2", gamePanel.tileSize, gamePanel.tileSize);
            down1 = setup("/player/boy_down_1", gamePanel.tileSize, gamePanel.tileSize);
            down2 = setup("/player/boy_down_2", gamePanel.tileSize, gamePanel.tileSize);
            left1 = setup("/player/boy_left_1", gamePanel.tileSize, gamePanel.tileSize);
            left2 = setup("/player/boy_left_2", gamePanel.tileSize, gamePanel.tileSize);
            right1 = setup("/player/boy_right_1", gamePanel.tileSize, gamePanel.tileSize);
            right2 = setup("/player/boy_right_2", gamePanel.tileSize, gamePanel.tileSize);
    }
    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/boy_attack_up_1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackUp2 = setup("/player/boy_attack_up_2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDown1 = setup("/player/boy_attack_down_1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDown2 = setup("/player/boy_attack_down_2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackLeft1 = setup("/player/boy_attack_left_1", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2", gamePanel.tileSize * 2, gamePanel.tileSize);
    }
    public void update() {
        if (attacking == true) {
            attacking();
        }

        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {

            if (keyHandler.upPressed == true) {
                direction = "up";
            } else if (keyHandler.downPressed == true) {
                direction = "down";
            } else if (keyHandler.leftPressed == true) {
                direction = "left";
            } else if (keyHandler.rightPressed == true) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gamePanel.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gamePanel.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gamePanel.cChecker.checkEntity(this, gamePanel.monster);
            interactMonster(monsterIndex);
            contactMonster(monsterIndex);


            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
        //CHANGING IMAGES OF PLAYER
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

            switch (direction) {
                case "up":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackUp1;
                        }
                        if (spriteNum == 2) {
                            image = attackUp1;
                        }
                    }
                    break;
                case "down":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackDown1;
                        }
                        if (spriteNum == 2) {
                            image = attackDown2;
                        }
                    }
                    break;
                case "left":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackLeft1;
                        }
                        if (spriteNum == 2) {
                            image = attackLeft2;
                        }
                    }
                    break;
                case "right":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackRight1;
                        }
                        if (spriteNum == 2) {
                            image = attackRight2;
                        }
                    }
                    break;
            }
            if (invincible == true) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
            g2.drawImage(image, screenX, screenY, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gamePanel.object[i].name;

            switch (objectName) {
                case "Key":
                    gamePanel.playSE(1);
                    hasKey++;
                    gamePanel.object[i] = null;
                    gamePanel.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gamePanel.playSE(3);
                        gamePanel.object[i] = null;
                        hasKey--;
                        gamePanel.ui.showMessage("You opened the door!");
                    } else {
                        gamePanel.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    gamePanel.playSE(2);
                    speed +=2;
                    gamePanel.object[i] = null;
                    gamePanel.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSE(4);
                    break;
            }
        }
    }
    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false) {
                life -= 1;
                invincible = true;
            }
        }
    }
    public void interactMonster(int i) {
        if (gamePanel.keyHandler.enterPressed == true) {
            if (i != 999) {

            }else {
                attacking = true;
            }
        }
    }
    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }
}
