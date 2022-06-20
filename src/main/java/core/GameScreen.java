package core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {

    private Random random;
    private BufferedImage img;

    private Dimension size;

    private ArrayList<BufferedImage> sprites = new ArrayList<>();


    public GameScreen(BufferedImage img) {
        this.img = img;

        setPanelSize();
        loadSprites();
        random = new Random();
    }

    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                BufferedImage subImage = img.getSubimage(x * 32, y * 32, 32, 32);
                sprites.add(subImage);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                g.drawImage(sprites.get(getRandomInt()), x * 32, y * 32, null);
            }
        }
    }

    private void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    private int getRandomInt() {
        return random.nextInt(100);
    }

}
