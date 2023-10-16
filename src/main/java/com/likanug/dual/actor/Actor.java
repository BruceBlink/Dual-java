package com.likanug.dual.actor;

import com.likanug.dual.App;
import com.likanug.dual.common.GameObject;

public abstract class Actor extends GameObject {

    public ActorGroup group;
    public float rotationAngle;
    protected final float collisionRadius;

    protected Actor(float _collisionRadius, App app) {
        this.app = app;
        collisionRadius = _collisionRadius;
    }

    protected abstract void act();

    public boolean isNotCollided(Actor other) {
        return !(getDistance(other) < this.collisionRadius + other.collisionRadius);
    }
}
