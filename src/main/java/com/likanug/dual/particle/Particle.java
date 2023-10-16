package com.likanug.dual.particle;

import com.likanug.dual.App;
import com.likanug.dual.common.GameObject;
import com.likanug.dual.pool.ObjectPool;
import com.likanug.dual.pool.Poolable;
import processing.core.PApplet;

import static com.likanug.dual.App.FPS;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PConstants.TWO_PI;

public class Particle extends GameObject implements Poolable<Particle> {

    boolean allocatedIndicator;
    ObjectPool<Particle> belongingPool;
    int allocationIdentifier;

    float rotationAngle;
    int displayColor;
    float strokeWeightValue;
    float displaySize;

    int lifespanFrameCount;
    int properFrameCount;
    int particleTypeNumber;

    public Particle(App app) {
        this.app = app;
    }

    // override methods of Poolable
    public boolean isAllocated() {
        return allocatedIndicator;
    }

    public void setAllocated(boolean indicator) {
        allocatedIndicator = indicator;
    }

    public ObjectPool<Particle> getBelongingPool() {
        return belongingPool;
    }

    public void setBelongingPool(ObjectPool<Particle> pool) {
        belongingPool = pool;
    }

    public int getAllocationIdentifier() {
        return allocationIdentifier;
    }

    public void setAllocationIdentifier(int id) {
        allocationIdentifier = id;
    }

    public void initialize() {
        xPosition = 0;
        yPosition = 0;
        xVelocity = 0;
        yVelocity = 0;
        directionAngle = 0;
        speed = 0;

        rotationAngle = 0;
        displayColor = app.color(0);
        strokeWeightValue = 1;
        displaySize = 10;

        lifespanFrameCount = 0;
        properFrameCount = 0;
        particleTypeNumber = 0;
    }


    public void update() {
        super.update();

        xVelocity = xVelocity * 0.98F;
        yVelocity = yVelocity * 0.98F;

        properFrameCount++;
        if (properFrameCount > lifespanFrameCount)
            app.system.commonParticleSet.removingParticleList.add(this);

        if (particleTypeNumber == 1) {    // Square
            rotationAngle += 1.5F * TWO_PI / FPS;
        }
    }

    float getProgressRatio() {
        return PApplet.min(1, properFrameCount / lifespanFrameCount);
    }

    float getFadeRatio() {
        return 1 - getProgressRatio();
    }

    public void display() {
        switch (particleTypeNumber) {
            case 0 ->  // Dot
                    app.set((int) xPosition, (int) yPosition, app.color(128 + 127 * getProgressRatio()));
            case 1 -> {  // Square
                app.noFill();
                app.stroke(displayColor, 255 * getFadeRatio());
                app.pushMatrix();
                app.translate(xPosition, yPosition);
                app.rotate(rotationAngle);
                app.rect(0, 0, displaySize, displaySize);
                app.popMatrix();
            }
            case 2 -> {  // Line
                app.stroke(displayColor, 128 * getFadeRatio());
                app.strokeWeight(strokeWeightValue * PApplet.pow(getFadeRatio(), 4));
                app.line(xPosition, yPosition, xPosition + 800 * cos(rotationAngle), yPosition + 800 * sin(rotationAngle));
                app.strokeWeight(1);
            }
            case 3 -> {  // Ring
                final float ringSizeExpandRatio = 2 * (PApplet.pow(getProgressRatio() - 1, 5) + 1);
                app.noFill();
                app.stroke(displayColor, 255 * getFadeRatio());
                app.strokeWeight(strokeWeightValue * getFadeRatio());
                app.ellipse(xPosition, yPosition, displaySize * (1 + ringSizeExpandRatio), displaySize * (1 + ringSizeExpandRatio));
                app.strokeWeight(1);
            }
            default -> {
            }
        }
    }
}
