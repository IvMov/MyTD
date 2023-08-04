package scenes;

import core.Game;
import lombok.Getter;

@Getter
public class GameScene {

    protected Game game;

    public GameScene(Game game) {
        this.game = game;
    }

}
