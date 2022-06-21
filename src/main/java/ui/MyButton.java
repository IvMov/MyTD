package ui;

import java.awt.*;

public class MyButton {

    private int x, y, width, height;
    private String text;
    private boolean mouseOver;
    private boolean mousePressed;

    private Rectangle bounds;

    public MyButton(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        initBounds();

    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        //body
        drawBody(g);

        //border
        drawBodrder(g);


        //text
        drawText(g);

    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    private void drawBodrder(Graphics g) {
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
        if (mouseOver) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.white);
        }

        g.fillRect(x, y, width, height);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x + width / 2 - w / 2, y + h / 2 + height / 2);
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
}
