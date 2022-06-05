package core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {

    private Random random;
    private BufferedImage img;

    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public GameScreen(BufferedImage img) {
        this.img = img;
        random = new Random();
        loadSprites();
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


//        for (int y = 0; y < 20; y++) {
//            for (int x = 0; x < 20; x++) {
//
//
//            }
//        }
    }



    private Color getRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
}
