package com.ivmov.mytd.managers;

import com.ivmov.mytd.helper.LoadSave;
import com.ivmov.mytd.objects.Enemy;
import com.ivmov.mytd.scenes.impl.Playing;
import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImages;
    private List<Enemy> enemies = new ArrayList<>();
    private BufferedImage atlas;
    private static final int SPRITE_SIZE = 32;


    public EnemyManager(Playing playing) {
        this.playing = playing;
        enemyImages = new BufferedImage[4];

        loadAtlas();
        loadEnemyImages();
    }

    public void update() {
        enemies.forEach(e -> e.move(0.5f, 0));
    }

    public void draw(Graphics g) {
        enemies.forEach(e -> drawEnemy(e, g));
    }

    public Enemy addEnemy(int x, int y) {
        Enemy enemy = new Enemy(x, y - SPRITE_SIZE, enemyImages[2], 0);
        enemies.add(enemy);

        return enemy;
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(e.getSprite(), (int) e.getX(), (int) e.getY(), null);
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    private void loadEnemyImages() {
        if (atlas == null) {
            loadAtlas();
        }
        enemyImages[0] = getEnemyByPositionOnAtlas(0, SPRITE_SIZE);
        enemyImages[1] = getEnemyByPositionOnAtlas(SPRITE_SIZE, SPRITE_SIZE);
        enemyImages[2] = getEnemyByPositionOnAtlas(SPRITE_SIZE * 2, SPRITE_SIZE);
        enemyImages[3] = getEnemyByPositionOnAtlas(SPRITE_SIZE * 3, SPRITE_SIZE);
    }

    private BufferedImage getEnemyByPositionOnAtlas(int x, int y) {
        return atlas.getSubimage(x, y, SPRITE_SIZE, SPRITE_SIZE);
    }
}
