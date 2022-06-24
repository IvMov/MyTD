package scenes;

import core.Game;
import core.GameStates;
import ui.MyButton;

import java.awt.*;
import java.util.Random;

public class Menu extends GameScene implements SceneMethods {

    private Random random;
    private MyButton bPlaying, bEditing, bSettings, bQuit;


    public Menu(Game game) {
        super(game);
        random = new Random();
        initButtons();
    }


    @Override
    public void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 70;
        bPlaying = new MyButton(x, y, w, h, "Play");
        bEditing = new MyButton(x, y + yOffset, w, h, "Edit level");
        bSettings = new MyButton(x, y + yOffset * 2, w, h, "Settings");
        bQuit = new MyButton(x, y + yOffset * 3, w, h, "Quit");
    }

    @Override
    public void drawContent(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, 640, 740);
    }

    @Override
    public void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEditing.draw(g);
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

        } else if (bEditing.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.EDIT);

        } else if (bSettings.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.SETTINGS);

        } else if (bQuit.getBounds().contains(x, y)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {

        bPlaying.setMouseOver(bPlaying.getBounds().contains(x, y));
        bEditing.setMouseOver(bEditing.getBounds().contains(x, y));
        bSettings.setMouseOver(bSettings.getBounds().contains(x, y));
        bQuit.setMouseOver(bQuit.getBounds().contains(x, y));

    }

    @Override
    public void mousePressed(int x, int y) {

        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);

        } else if (bEditing.getBounds().contains(x, y)) {
            bEditing.setMousePressed(true);

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
        bEditing.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    private int getRandomInt() {
        return random.nextInt(100);
    }

}
