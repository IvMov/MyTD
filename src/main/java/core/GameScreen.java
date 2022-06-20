package core;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private Game game;
    private Dimension size;


    public GameScreen(Game game) {

        this.game = game;

        setPanelSize();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g);
    }

    private void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


}
