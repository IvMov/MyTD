package scenes;

import core.Game;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends GameScene implements SceneMethods {

    private Random random;
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    private MyButton bPlaying, bSettings, bQuit;

    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        loadSprites();
        initButtons();
    }

    private void initButtons() {
        bPlaying = new MyButton(100, 100, 100, 30,"Play");
    }

    @Override
    public void render(Graphics g) {

        drawButtons(g);

    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        
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
