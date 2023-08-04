package com.ivmov.mytd.scenes;

import com.ivmov.mytd.core.Game;
import com.ivmov.mytd.managers.TileManager;
import lombok.Getter;

@Getter
public abstract class GameScene extends Animation implements SceneMethods {

    protected Game game;
    protected TileManager tileManager;

    public GameScene(Game game, TileManager tileManager) {
        this.game = game;
        this.tileManager = tileManager;
    }

}
