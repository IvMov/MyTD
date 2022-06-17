package core;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame {

    private GameScreen gameScreen;

    private BufferedImage img;

    private double timePerFrame;
    private long lastFrame;

    public Game() {

        timePerFrame = 1000000000.0 / 60;

        importImg();

        setSize(640, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen(img);
        add(gameScreen);
        setVisible(true);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loopGame() {
        while (true) {
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                repaint();
            } else {
                //nothing
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Start of game");
        Game game = new Game();
        game.loopGame();
    }
}
