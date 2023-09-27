package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectHeart extends SuperObject {
    GamePanel gamePanel;
    public ObjectHeart(GamePanel gamePanel) {

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            image = uTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
            image2 =uTool.scaleImage(image2, gamePanel.tileSize, gamePanel.tileSize);
            image3 = uTool.scaleImage(image3, gamePanel.tileSize, gamePanel.tileSize);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
