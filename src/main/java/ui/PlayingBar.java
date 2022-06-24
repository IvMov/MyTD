package ui;

import core.GameStates;
import managers.TileManager;
import scenes.Playing;

import java.awt.*;

public class PlayingBar extends Bar{

    private MyButton bMenu;
    private Playing playing;

    public PlayingBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;

        initButtons();
    }

    public void initButtons() {
        bMenu = new MyButton(2, 642, 120, 120 / 3, "Menu");
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);
        drawButtons(g);
    }

    public void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(bMenu.getBounds().contains(x, y));
    }

    public void mousePressed(int x, int y) {
        bMenu.setMousePressed(bMenu.getBounds().contains(x, y));
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }

    //private methods

    private TileManager getTileManager() {
        return playing.getGame().getTileManager();
    }
}

