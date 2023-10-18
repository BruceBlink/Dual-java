package com.likanug.dual.particle;

import com.likanug.dual.App;
import processing.core.PApplet;

import static com.likanug.dual.App.FPS;

public class ParticleBuilder {

    private final App app;
    private int particleTypeNumber;

    private float xPosition, yPosition;
    private float xVelocity, yVelocity;
    private float directionAngle, speed;

    private float rotationAngle;
    private int displayColor;
    private float strokeWeightValue;
    private int displaySize;

    private int lifespanFrameCount;

    public ParticleBuilder(App app) {
        this.app = app;
    }

    public ParticleBuilder initialize() {
        particleTypeNumber = 0;
        xPosition = 0;
        yPosition = 0;
        xVelocity = 0;
        yVelocity = 0;
        directionAngle = 0;
        speed = 0;
        rotationAngle = 0;
        displayColor = 0;
        strokeWeightValue = 1;
        displaySize = 10;
        lifespanFrameCount = 60;
        return this;
    }

    public ParticleBuilder type(int v) {
        particleTypeNumber = v;
        return this;
    }

    public ParticleBuilder position(float x, float y) {
        xPosition = x;
        yPosition = y;
        return this;
    }

    public ParticleBuilder polarVelocity(float dir, float spd) {
        directionAngle = dir;
        speed = spd;
        xVelocity = spd * PApplet.cos(dir);
        yVelocity = spd * PApplet.sin(dir);
        return this;
    }

    public ParticleBuilder rotation(float v) {
        rotationAngle = v;
        return this;
    }

    public ParticleBuilder particleColor(int c) {
        displayColor = c;
        return this;
    }

    public ParticleBuilder weight(float v) {
        strokeWeightValue = v;
        return this;
    }

    public ParticleBuilder particleSize(int v) {
        displaySize = v;
        return this;
    }

    public ParticleBuilder lifespan(int v) {
        lifespanFrameCount = v;
        return this;
    }

    public ParticleBuilder lifespanSecond(int v) {
        lifespan(v * FPS);
        return this;
    }

    public Particle build() {
        final Particle newParticle = app.getSystem().getCommonParticleSet().allocate();
        newParticle.setParticleTypeNumber(this.particleTypeNumber);
        newParticle.setxPosition(this.xPosition);
        newParticle.setyPosition(this.yPosition);
        newParticle.setxVelocity(this.xVelocity);
        newParticle.setyVelocity(this.yVelocity);
        newParticle.setDirectionAngle(this.directionAngle);
        newParticle.setSpeed(this.speed);
        newParticle.setRotationAngle(this.rotationAngle);
        newParticle.setDisplayColor(this.displayColor);
        newParticle.setStrokeWeightValue(this.strokeWeightValue);
        newParticle.setDisplaySize(this.displaySize);
        newParticle.setLifespanFrameCount(this.lifespanFrameCount);
        return newParticle;
    }

}
