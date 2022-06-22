package ui;

import core.GameStates;

import java.awt.*;

public class BottomBar {


    private int x, y, width, height;
    private MyButton bMenu;

    public BottomBar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
}

