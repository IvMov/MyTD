package core;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable {

    private static final int FPS_SET = 120;
    private static final int UPS_SET = 60;

    private GameScreen gameScreen;
    private BufferedImage img;
    private Thread gameThread;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public Game() {

        importImg();

        initInputs();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen(img);
        add(gameScreen);
        setVisible(true);
        pack();
    }

    private void initInputs() {
        myMouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void start() { //for me - method to create new Thread and start it
        gameThread = new Thread(this) {
        };
        gameThread.start();
    }

    public static void main(String[] args) {
        System.out.println("Start of game");
        Game game = new Game();
        game.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        int frames = 0;
        int updates = 0;

        long lastTimeCheck = System.currentTimeMillis();

        long lastUpdate = System.nanoTime();
        long lastFrame = System.nanoTime();
        long now;

        while (true) {
            now = System.nanoTime();

            //render
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }

            //update
            if (now - lastUpdate >= timePerUpdate) {
                lastUpdate = now;
                updates++;
            }

            //checking FPS and UPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }

        }

    }
}
