package com.likanug.dual.common;

import com.likanug.dual.App;

import static processing.core.PApplet.*;

public abstract class GameObject extends AbstractGameObject {
    protected App app;
    public float xPosition;
    public float yPosition;
    public float xVelocity;
    public float yVelocity;
    public float directionAngle;
    public float speed;

    public void update() {
        xPosition += xVelocity;
        yPosition += yVelocity;
    }

    public abstract void display();

    public void setVelocity(float dir, float spd) {
        directionAngle = dir;
        speed = spd;
        xVelocity = speed * cos(dir);
        yVelocity = speed * sin(dir);
    }

    public float getDistance(GameObject other) {
        return dist(this.xPosition, this.yPosition, other.xPosition, other.yPosition);
    }

    public float getDistancePow2(GameObject other) {
        return sq(other.xPosition - this.xPosition) + sq(other.yPosition - this.yPosition);
    }

    public float getAngle(GameObject other) {
        return atan2(other.yPosition - this.yPosition, other.xPosition - this.xPosition);
    }
}
