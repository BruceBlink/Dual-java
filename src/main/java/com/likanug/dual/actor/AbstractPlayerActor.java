package com.likanug.dual.actor;

import com.likanug.dual.App;
import com.likanug.dual.playerEngine.PlayerEngine;
import com.likanug.dual.state.PlayerActorState;

public abstract class AbstractPlayerActor extends Actor {
    public final PlayerEngine engine;
    public PlayerActorState state;

    AbstractPlayerActor(float _collisionRadius, PlayerEngine _engine, App app) {
        super(_collisionRadius, app);
        engine = _engine;
    }

    public boolean isNull() {
        return false;
    }
}
