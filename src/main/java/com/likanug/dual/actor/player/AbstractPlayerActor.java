package com.likanug.dual.actor.player;

import com.likanug.dual.App;
import com.likanug.dual.actor.Actor;
import com.likanug.dual.playerEngine.PlayerEngine;
import com.likanug.dual.state.PlayerActorState;

public abstract class AbstractPlayerActor extends Actor {
    protected final PlayerEngine engine;

    protected PlayerActorState state;

    public AbstractPlayerActor(float _collisionRadius, PlayerEngine _engine, App app) {
        super(_collisionRadius, app);
        engine = _engine;
    }

    public boolean isNull() {
        return false;
    }

    public PlayerEngine getEngine() {
        return engine;
    }

    public PlayerActorState getState() {
        return state;
    }

    public void setState(PlayerActorState state) {
        this.state = state;
    }
}
