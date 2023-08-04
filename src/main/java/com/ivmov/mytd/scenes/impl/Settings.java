package com.ivmov.mytd.scenes.impl;

import com.ivmov.mytd.core.Game;
import com.ivmov.mytd.core.GameState;
import com.ivmov.mytd.managers.TileManager;
import com.ivmov.mytd.scenes.GameScene;
import com.ivmov.mytd.ui.impl.CommonButton;

import java.awt.*;

public class Settings extends GameScene {

    private CommonButton bMenu;

    public Settings(Game game, TileManager tileManager) {
        super(game, tileManager);
        initButtons();
    }

    @Override
    public void initButtons() {
        bMenu = new CommonButton(5, 5, 120, 120 / 3, "Menu");
    }

    @Override
    public void drawContent(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, 640, 640);
    }

    @Override
    public void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    @Override
    public void render(Graphics g) {
        drawContent(g);
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            game.setGameState(GameState.MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(bMenu.getBounds().contains(x, y));
    }

    @Override
    public void mousePressed(int x, int y) {
        bMenu.setMousePressed(bMenu.getBounds().contains(x, y));
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        //Temporary empty. Do nothing
    }

}
