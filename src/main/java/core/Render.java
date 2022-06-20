package core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Render {

    private Random random;
    private BufferedImage img;
    private GameScreen gameScreen;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public Render(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        random = new Random();
        importImg();
        loadSprites();
    }

    public void render(Graphics g) {
        switch (GameStates.gameState) {
            case MENU:
                for (int y = 0; y < 20; y++) {
                    for (int x = 0; x < 20; x++) {
                        g.drawImage(sprites.get(getRandomInt()), x * 32, y * 32, null);
                    }
                }
                break;
            case PLAYING:
                break;
            case SETTINGS:
                break;
        }
    }

    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                BufferedImage subImage = img.getSubimage(x * 32, y * 32, 32, 32);
                sprites.add(subImage);
            }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getRandomInt() {
        return random.nextInt(100);
    }

}
