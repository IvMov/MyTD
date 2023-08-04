package core;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class GameScreen extends JPanel {

    private Game game;
    private Dimension size;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
    }

    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getGameRender().render(g);
    }

    private void setPanelSize() {
        size = new Dimension(640, 740);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

}
