package com.ivmov.mytd.core;

import lombok.AllArgsConstructor;

import java.awt.*;

@AllArgsConstructor
public class GameRender {

    private Game game;

    public void render(Graphics g) {
        switch (game.getGameState()) {
            case MENU -> game.getMenu().render(g);
            case PLAYING -> game.getPlaying().render(g);
            case SETTINGS -> game.getSettings().render(g);
            case EDIT -> game.getEditing().render(g);
        }
    }

}
