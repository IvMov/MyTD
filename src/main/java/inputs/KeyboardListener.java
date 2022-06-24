package inputs;

import core.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            GameStates.gameState = GameStates.MENU;

        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            GameStates.gameState = GameStates.SETTINGS;

        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            GameStates.gameState = GameStates.PLAYING;

        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            GameStates.gameState = GameStates.EDIT;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
