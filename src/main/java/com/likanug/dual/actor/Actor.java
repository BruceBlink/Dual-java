package com.likanug.dual.actor;

import com.likanug.dual.App;
import com.likanug.dual.common.GameObject;

public abstract class Actor extends GameObject {

    protected ActorGroup group;
    protected float rotationAngle;
    protected final float collisionRadius;

    protected Actor(float _collisionRadius, App app) {
        this.app = app;
        collisionRadius = _collisionRadius;
    }

    protected abstract void act();

    public boolean isNotCollided(Actor other) {
        return !(getDistance(other) < this.collisionRadius + other.collisionRadius);
    }

    public ActorGroup getGroup() {
        return group;
    }

    public void setGroup(ActorGroup group) {
        this.group = group;
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public float getCollisionRadius() {
        return collisionRadius;
    }
}
