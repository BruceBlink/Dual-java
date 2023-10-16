package com.likanug.dual.common;

import processing.core.PVector;

import static processing.core.PApplet.*;

public abstract class AbstractGameObject implements Show {

    protected PVector position;

    protected PVector velocity;


    @Override
    public void update() {
        position.add(velocity);
    }

    @Override
    public abstract void display();

    public float getDistance(AbstractGameObject other) {
        return dist(this.position.x, this.position.y, other.position.x, other.position.y);
    }

    public float getDistancePow2(AbstractGameObject other) {
        return sq(other.position.x - this.position.x) + sq(other.position.y - this.position.y);
    }

    public float getAngle(AbstractGameObject other) {
        return atan2(other.position.y - this.position.y, other.position.x - this.position.x);
    }
}
