package com.ivmov.mytd.objects;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class Enemy {

    private static int id;
    private float x, y;
    private int health;
    private int type;
    private BufferedImage sprite;
    private Rectangle bounds;

    public Enemy(float x, float y, BufferedImage sprite, int type) {
        id++;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.type = type;
    }

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }
}
