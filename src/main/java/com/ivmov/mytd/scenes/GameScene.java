package com.ivmov.mytd.scenes;

import com.ivmov.mytd.core.Game;
import lombok.Getter;

@Getter
public abstract class GameScene implements SceneMethods{

    protected Game game;

    public GameScene(Game game) {
        this.game = game;
    }

}
