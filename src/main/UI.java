package main;

import entity.Entity;
import entity.Player;
import object.ObjectHeart;
import object.ObjectKey;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gamePanel;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    Graphics2D g2;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        ObjectKey key = new ObjectKey(gamePanel);
        keyImage = key.image;

        SuperObject heart = new ObjectHeart(gamePanel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    //DRAW FINISH SCREEN
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gameFinished == true) {

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x, y;

            text = "You fond the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize);
            g2.drawString(text, x, y);

            gamePanel.gameThread = null;

        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.screenHeight - (gamePanel.tileSize * 2), gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawString("x " + gamePanel.player.hasKey, 74, 524);

            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gamePanel.tileSize / 2, gamePanel.tileSize * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
        drawPlayerLife();
        pause(g2);
    }
    public void drawPlayerLife() {
        int x = gamePanel.tileSize / 2;
        int y = gamePanel.tileSize / 2;
        int i = 0;

        while (i < gamePanel.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gamePanel.tileSize;
        }

        x = gamePanel.tileSize / 2;
        y = gamePanel.tileSize / 2;
        i = 0;

        while (i < gamePanel.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gamePanel.player.life) {
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x += gamePanel.tileSize;
        }
    }
    public void pause(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if (gamePanel.gameState == gamePanel.playState) {

        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen();
        }
    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSE";
        int x;
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gamePanel.screenWidth / 2 - length / 2;
        int y = gamePanel.screenHeight / 2;
        g2.drawString(text, x, y);


    }
}
