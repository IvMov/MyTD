package com.ivmov.mytd.objects;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EnemyDirection {
    RIGHT,
    DOWN,
    LEFT,
    UP;

    private static final EnemyDirection[] directions = values();

    public EnemyDirection next() {
        return directions[(this.ordinal() + 1) % directions.length];
    }
}
