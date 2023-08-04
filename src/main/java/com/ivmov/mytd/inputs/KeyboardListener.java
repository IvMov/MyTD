package com.ivmov.mytd.inputs;

import com.ivmov.mytd.core.Game;
import com.ivmov.mytd.core.GameState;
import lombok.RequiredArgsConstructor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@RequiredArgsConstructor
public class KeyboardListener implements KeyListener {

    private final Game game;

    @Override
    public void keyTyped(KeyEvent e) {
        //Temporary empty. Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.getGameState() == GameState.EDIT) {
            game.getEditing().keyPressed(e);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Temporary empty. Do nothing
    }
}
