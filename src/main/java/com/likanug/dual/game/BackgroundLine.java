package com.likanug.dual.game;

import com.likanug.dual.App;

public abstract class BackgroundLine {
    public App app;
    public float position;
    public float velocity;

    public BackgroundLine(App app, float initialPosition) {
        this.app = app;
        this.position = initialPosition;
    }

    public BackgroundLine() {

    }

    public void update(float acceleration) {
        position += velocity;
        velocity += acceleration;
        if (position < 0.0 || position > getMaxPosition()) velocity = -velocity;
    }

    public abstract void display();

    public abstract float getMaxPosition();
}
