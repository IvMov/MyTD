package ui;


import core.Game;

public class Bar {

    protected int xStart, yStart, width, height;
    protected Game game;

    public Bar(int xStart, int y, int width, int height) {
        this.xStart = xStart;
        this.yStart = y;
        this.width = width;
        this.height = height;
    }
}
