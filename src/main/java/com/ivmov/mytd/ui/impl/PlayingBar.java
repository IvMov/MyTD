package com.ivmov.mytd.ui.impl;

import com.ivmov.mytd.core.GameState;
import com.ivmov.mytd.ui.BarElement;
import lombok.Getter;
import com.ivmov.mytd.scenes.impl.Playing;

import java.awt.*;

@Getter
public class PlayingBar extends BarElement {

    private CommonButton bMenu;
    private final Playing playing;

    public PlayingBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;

        initButtons();
    }

    public void initButtons() {
        bMenu = new CommonButton(2, 642, 120, 120 / 3, "Menu");
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(xStart, yStart, width, height);
        drawButtons(g);
    }

    public void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            playing.getGame().setGameState(GameState.MENU);
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

