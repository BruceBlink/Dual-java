package com.likanug.dual.particle;

import com.likanug.dual.App;
import processing.core.PApplet;

import static com.likanug.dual.App.FPS;

public class ParticleBuilder {

    private final App app;
    int particleTypeNumber;

    float xPosition, yPosition;
    float xVelocity, yVelocity;
    float directionAngle, speed;

    float rotationAngle;
    int displayColor;
    float strokeWeightValue;
    int displaySize;

    int lifespanFrameCount;

    public ParticleBuilder(App app) {
        this.app = app;
    }

    ParticleBuilder initialize() {
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

    ParticleBuilder lifespan(int v) {
        lifespanFrameCount = v;
        return this;
    }

    public ParticleBuilder lifespanSecond(int v) {
        lifespan(v * FPS);
        return this;
    }

    public Particle build() {
        final Particle newParticle = app.system.commonParticleSet.allocate();
        newParticle.particleTypeNumber = this.particleTypeNumber;
        newParticle.xPosition = this.xPosition;
        newParticle.yPosition = this.yPosition;
        newParticle.xVelocity = this.xVelocity;
        newParticle.yVelocity = this.yVelocity;
        newParticle.directionAngle = this.directionAngle;
        newParticle.speed = this.speed;
        newParticle.rotationAngle = this.rotationAngle;
        newParticle.displayColor = this.displayColor;
        newParticle.strokeWeightValue = this.strokeWeightValue;
        newParticle.displaySize = this.displaySize;
        newParticle.lifespanFrameCount = this.lifespanFrameCount;
        return newParticle;
    }

}
