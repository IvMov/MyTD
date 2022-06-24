package core;

import helpz.LoadSave;
import managers.TileManager;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.swing.*;

public class Game extends JFrame implements Runnable {

    private static final int FPS_SET = 120;
    private static final int UPS_SET = 60;

    private GameScreen gameScreen;
    private Thread gameThread;

    //Classes
    private Render render;
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

    private void start() { //for me - method to create new Thread and start it
        gameThread = new Thread(this) {
        };
        gameThread.start();
    }

    public static void main(String[] args) {
        System.out.println("Start of game");
        Game game = new Game();
        game.gameScreen.initInputs();
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

    private void createDefaultLvl() {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        LoadSave.CreateLevel("new_lvl", arr);
    }


    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        tileManager = new TileManager();

        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
        editing = new Editing(this);
    }

    //getters and setters

    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

    public Editing getEditing() {
        return editing;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
