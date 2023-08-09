package com.ivmov.mytd.managers;

import com.ivmov.mytd.helper.LoadSave;
import com.ivmov.mytd.objects.Enemy;
import com.ivmov.mytd.objects.EnemyDirection;
import com.ivmov.mytd.scenes.impl.Playing;
import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
public class EnemyManager {

    private Playing playing;
    private TileManager tileManager;
    private BufferedImage[] enemyImages;
    private List<Enemy> enemies = new ArrayList<>();
    private BufferedImage atlas;
    private static final float SPEED = 0.5f;
    private static final int SPRITE_SIZE = 32;


    public EnemyManager(Playing playing, TileManager tileManager) {
        this.playing = playing;
        this.tileManager = tileManager;
        enemyImages = new BufferedImage[4];

        loadAtlas();
        loadEnemyImages();
    }

    public void update() {
        enemies.forEach(e -> {
            if (nextTileIsRoad(e)) {
                e.move(SPEED, e.getDirection());
            } else {
//                findPath(e)
            }
        });
    }

    public void draw(Graphics g) {
        enemies.forEach(e -> drawEnemy(e, g));
    }

    public boolean nextTileIsRoad(Enemy e) {
        float nextX = e.getX() + SPRITE_SIZE/2 + calculateSpeedX(e.getDirection());
        float nextY = e.getY() + SPRITE_SIZE/2 + calculateSpeedY(e.getDirection());
        int tileId = playing.getTileIdByCoordinates((int) nextY, (int) nextX);

        return tileManager.isSpriteRoad(tileId);
    }

    public float calculateSpeedX(EnemyDirection direction) {
        if (direction == EnemyDirection.LEFT) {
            return -SPEED;
        }
        if (direction == EnemyDirection.RIGHT) {
            return SPEED;
        }
        return 0;
    }

    public float calculateSpeedY(EnemyDirection direction) {
        if (direction == EnemyDirection.UP) {
            return SPEED;
        }
        if (direction == EnemyDirection.DOWN) {
            return -SPEED;
        }
        return 0;
    }

    public Enemy addEnemy(int x, int y) {
        Enemy enemy = new Enemy(x - SPRITE_SIZE/2, y - SPRITE_SIZE/2, enemyImages[2], 0);
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
