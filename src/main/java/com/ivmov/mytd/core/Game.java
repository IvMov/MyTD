package com.ivmov.mytd.core;

import com.ivmov.mytd.helper.LoadSave;
import lombok.Getter;
import lombok.Setter;
import com.ivmov.mytd.managers.TileManager;
import com.ivmov.mytd.scenes.impl.Editing;
import com.ivmov.mytd.scenes.impl.Menu;
import com.ivmov.mytd.scenes.impl.Playing;
import com.ivmov.mytd.scenes.impl.Settings;

import javax.swing.*;

@Getter
@Setter
public class Game extends JFrame implements Runnable {

    private static final int FPS_SET = 120;
    private static final int UPS_SET = 60;

    private GameScreen gameScreen;
    private Thread gameThread;
    private GameRender gameRender;
    private GameState gameState;

    //game stages/screens
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;
    private TileManager tileManager;

    public Game() {

        initClasses();
        createDefaultLvl();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        add(gameScreen);
        pack();
        setVisible(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (this.gameState) {
            case PLAYING -> playing.update();
            case MENU -> {
            }
            case SETTINGS -> {
            }
            case EDIT -> {
            }
        }
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

    private void createDefaultLvl() {
        int[] arr = new int[400];
        LoadSave.CreateLevel("new_lvl", arr);
    }

    private void initClasses() {
        gameState = GameState.MENU;
        gameRender = new GameRender(this);
        gameScreen = new GameScreen(this);
        tileManager = new TileManager();

        menu = new Menu(this, tileManager);
        playing = new Playing(this, tileManager);
        settings = new Settings(this, tileManager);
        editing = new Editing(this, tileManager);
    }

}
