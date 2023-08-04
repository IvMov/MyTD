package com.ivmov.mytd.ui.impl;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class CommonButton {

    private int id;
    private int x, y, width, height;
    private String text;
    private boolean mouseOver;
    private boolean mousePressed;

    private Rectangle bounds;

    //constructor for common buttons
    public CommonButton(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.id = -1; //it's for (when loop - to except nullpointer - interesting)
        initBounds();
    }

    //constructor for buttons with id (tile buttons)
    public CommonButton(int id,
                        int x,
                        int y,
                        int width,
                        int height,
                        String text) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        initBounds();
    }

    public void draw(Graphics g) {
        drawBody(g);
        drawBorder(g);
        drawText(g);
    }

    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    private void drawBorder(Graphics g) {
        if (mousePressed) {
            g.setColor(Color.black);
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        } else {
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);
        }

    }

    private void drawBody(Graphics g) {
        g.setColor(mouseOver ? Color.gray: Color.pink);

        g.fillRect(x, y, width, height);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x + width / 2 - w / 2, y + height / 2 + h/3);
    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

}
