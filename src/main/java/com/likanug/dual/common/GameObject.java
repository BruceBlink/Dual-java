package com.likanug.dual.common;

import com.likanug.dual.App;

import static processing.core.PApplet.*;

public abstract class GameObject extends AbstractGameObject {

    protected App app;

    protected float xPosition;

    protected float yPosition;
    protected float xVelocity;
    protected float yVelocity;
    protected float directionAngle;
    protected float speed;

    public void update() {
        xPosition += xVelocity;
        yPosition += yVelocity;
    }

    public abstract void display();

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public float getxPosition() {
        return xPosition;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public float getDirectionAngle() {
        return directionAngle;
    }

    public void setDirectionAngle(float directionAngle) {
        this.directionAngle = directionAngle;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

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
