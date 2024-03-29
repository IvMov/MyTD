package com.ivmov.mytd.scenes.impl;

import com.ivmov.mytd.core.Game;
import com.ivmov.mytd.helper.LoadSave;
import com.ivmov.mytd.managers.EnemyManager;
import com.ivmov.mytd.managers.TileManager;
import com.ivmov.mytd.scenes.GameScene;
import com.ivmov.mytd.ui.impl.PlayingBar;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Playing extends GameScene {

    private int mouseX, mouseY; //will be used later
    private int[][] lvl;
    private PlayingBar playingBar;
    private EnemyManager enemyManager;

    public Playing(Game game, TileManager tileManager) {
        super(game, tileManager);
        loadDefaultLvl();
        playingBar = new PlayingBar(0, 640, 640, 100, this);
        enemyManager = new EnemyManager(this, tileManager);
    }

    public void update() {
        enemyManager.update();
    }

    public int getTileIdByCoordinates(int y, int x) {
        boolean isPositive = y >= 0 && x >= 0;
        if (isPositive && y/32 < lvl.length && x/32 < lvl[y/32].length){
            return lvl[y/32][x/32];
        }
        return 0;
    }

    @Override
    public void render(Graphics g) {
        updateWaterTick();
        update();

        drawContent(g);
        drawPlayingBar(g);
        enemyManager.draw(g);
    }

    @Override
    public void initButtons() {
        //Temporary empty. Do nothing
    }

    @Override
    public void drawButtons(Graphics g) {
        //Temporary empty. Do nothing
    }

    @Override
    public void drawContent(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                if (tileManager.isSpriteAnimation(id)) {
                    g.drawImage(tileManager.getSpriteByIdAndIndex(id, animationIndex), x * 32, y * 32, null);
                } else {
                    g.drawImage(tileManager.getSpriteByCoordinate(id), x * 32, y * 32, null);
                }
            }
        }
    }


    //mouse events
    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            playingBar.mouseClicked(x, y);
        } else {
            enemyManager.addEnemy(x, y);
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
        //Temporary empty. Do nothing
    }


    private void drawPlayingBar(Graphics g) {
        playingBar.draw(g);
    }

    private void loadDefaultLvl() {
        lvl = LoadSave.GetLevelData("new_lvl");
    }

}
