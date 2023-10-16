package com.likanug.dual.actor;

import com.likanug.dual.App;
import com.likanug.dual.body.Body;

public abstract class Actor extends Body {

    public ActorGroup group;
    public float rotationAngle;
    protected final float collisionRadius;

    Actor(float _collisionRadius, App app) {
        this.app = app;
        collisionRadius = _collisionRadius;
    }

    abstract void act();

    public boolean isCollided(Actor other) {
        return getDistance(other) < this.collisionRadius + other.collisionRadius;
    }
}
