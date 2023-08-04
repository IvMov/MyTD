package com.ivmov.mytd.scenes;

import com.ivmov.mytd.core.Game;
import com.ivmov.mytd.helper.LoadSave;
import com.ivmov.mytd.ui.PlayingBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Playing extends GameScene implements SceneMethods {

    private int mouseX, mouseY; //will be used later
    private int[][] lvl;
    private PlayingBar playingBar;


    public Playing(Game game) {
        super(game);

        loadDefaultLvl();
        playingBar = new PlayingBar(0, 640, 640, 100, this);

    }


    @Override
    public void render(Graphics g) {
        drawContent(g);
        drawPlayingBar(g);
    }

    @Override
    public void initButtons() {
    }

    @Override
    public void drawButtons(Graphics g) {
    }

    @Override
    public void drawContent(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }


    //mouse events
    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            playingBar.mouseClicked(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            playingBar.mouseMoved(x, y);
        } else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            playingBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        playingBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }


    //private methods

    private void drawPlayingBar(Graphics g) {
        playingBar.draw(g);
    }

    private void loadDefaultLvl() {
        lvl = LoadSave.GetLevelData("new_lvl");
    }

    private BufferedImage getSprite(int spriteId) {
        return getGame().getTileManager().getSprite(spriteId);
    }

    //getters and setters


    public void setLvl(int[][] lvl) {
        this.lvl = lvl;
    }
}
