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
    private static final float ENEMY_SPEED = 0.5f;
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
            if (tileIsRoad(e, 1)) {
                e.move(ENEMY_SPEED, e.getDirection());
            } else if (findPath(e)) {
                e.move(ENEMY_SPEED, e.getDirection());
            } else {
//                do nothing
                System.out.println("No ways to move for enemy: " + e.getId());
            }
        });
    }

    public Enemy addEnemy(int x, int y) {
        Enemy enemy = new Enemy(x, y, enemyImages[2], 0);
        enemies.add(enemy);

        return enemy;
    }

    public void draw(Graphics g) {
        enemies.forEach(e -> drawEnemy(e, g));
    }


    private boolean findPath(Enemy e) {
        EnemyDirection oldDirection = e.getDirection();
        EnemyDirection restrictedDirection = calculateRestrictedDirection(oldDirection);

        e.changeDirection();

        while (oldDirection != e.getDirection()) {
            if (e.getDirection() != restrictedDirection && tileIsRoad(e, 2)) {
                return true;
            } else {
                e.changeDirection();
                System.out.println("Direction changed to: " + e.getDirection().name());
            }
        }

        System.out.println("No way found found for: " + e.getDirection().name());
        return false;
    }

    private EnemyDirection calculateRestrictedDirection(EnemyDirection oldDirection) {
        switch (oldDirection) {
            case LEFT -> {
                return EnemyDirection.RIGHT;
            }
            case RIGHT -> {
                return EnemyDirection.LEFT;
            }
            case DOWN -> {
                return EnemyDirection.UP;
            }
            case UP -> {
                return EnemyDirection.DOWN;
            }
            default -> throw new IllegalStateException("Unexpected value: " + oldDirection);
        }
    }

    private boolean tileIsRoad(Enemy e, int tile) { //tile - multiplier to check next tile (1) or second after next (2)
        float nextX = e.getX() + calculateSpeedForCoord(e.getDirection(), 'x') * tile;
        float nextY = e.getY() + calculateSpeedForCoord(e.getDirection(), 'y') * tile;

        int tileId = playing.getTileIdByCoordinates((int) nextY, (int) nextX);
        boolean goodToGo = tileManager.isSpriteRoad(tileId);
        System.out.println("/");
        System.out.printf("== %s == Enemy x: %f, y: %f. Direction: %s, nextX: %f, nextY: %f",
                goodToGo, e.getX(), e.getY(), e.getDirection(), nextX, nextY);

        return goodToGo;
    }

    private float calculateSpeedForCoord(EnemyDirection direction, char coord) {
        char x = 'x';
        char y = 'y';
        float speed = ENEMY_SPEED + (float) SPRITE_SIZE / 2;

        switch (direction) {
            case RIGHT -> {
                return coord == x ? speed : 0;
            }
            case DOWN -> {
                return coord == y ? speed : 0;
            }
            case LEFT -> {
                return coord == x ? -speed : 0;
            }
            case UP -> {
                return coord == y ? -speed : 0;
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(e.getSprite(), (int) e.getX() - SPRITE_SIZE / 2, (int) e.getY() - SPRITE_SIZE / 2, null);
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
