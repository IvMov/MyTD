package ui;

import java.awt.*;

public class MyButton {

    private int x, y, width, height;
    private String text;

    private Rectangle bounds;

    public MyButton(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;

    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        //body
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);

        //border
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);

        //text
        drawText(g);

    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x + width / 2 - w / 2, y + h / 2 + height / 2);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
