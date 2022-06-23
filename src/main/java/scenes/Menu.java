package scenes;

import core.Game;
import core.GameStates;
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

    @Override
    public void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;
        bPlaying = new MyButton(x, y, w, h, "Play");
        bSettings = new MyButton(x, y + yOffset, w, h, "Settings");
        bQuit = new MyButton(x, y + yOffset * 2, w, h, "Quit");
    }

    @Override
    public void drawContent(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, 640, 640);
    }

    @Override
    public void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);

    }


    @Override
    public void render(Graphics g) {

        drawContent(g);
        drawButtons(g);

    }

    //mouse events
    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.PLAYING);
        } else if (bSettings.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.SETTINGS);
        } else if (bQuit.getBounds().contains(x, y)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        //simplified if else statement
        bPlaying.setMouseOver(bPlaying.getBounds().contains(x, y));
        bSettings.setMouseOver(bSettings.getBounds().contains(x, y));
        bQuit.setMouseOver(bQuit.getBounds().contains(x, y));

    }

    @Override
    public void mousePressed(int x, int y) {

        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);

        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);

        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }

    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }


    private void resetButtons() {
        bPlaying.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
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
