package com.likanug.dual.actor;

import com.likanug.dual.App;
import com.likanug.dual.playerEngine.PlayerEngine;

import static com.likanug.dual.App.FPS;
import static com.likanug.dual.App.INTERNAL_CANVAS_SIDE_LENGTH;
import static processing.core.PApplet.constrain;
import static processing.core.PApplet.sq;
import static processing.core.PConstants.TWO_PI;

public class PlayerActor extends AbstractPlayerActor {

    final float bodySize = 32.0F;
    final float halfBodySize = bodySize * 0.5F;
    final int fillColor;

    public float aimAngle;
    public int chargedFrameCount;
    public int damageRemainingFrameCount;

    public PlayerActor(PlayerEngine _engine, int col, App app) {
        super(16, _engine, app);
        fillColor = col;
    }

    public void addVelocity(float xAcceleration, float yAcceleration) {
        xVelocity = constrain(xVelocity + xAcceleration, -10, 10);
        yVelocity = constrain(yVelocity + yAcceleration, -7, 7);
    }

    public void act() {
        engine.run(this);
        state.act(this);
    }

    public void update() {
        super.update();

        if (xPosition < halfBodySize) {
            xPosition = halfBodySize;
            xVelocity = (float) (-0.5 * xVelocity);
        }
        if (xPosition > INTERNAL_CANVAS_SIDE_LENGTH - halfBodySize) {
            xPosition = INTERNAL_CANVAS_SIDE_LENGTH - halfBodySize;
            xVelocity = (float) (-0.5 * xVelocity);
        }
        if (yPosition < halfBodySize) {
            yPosition = halfBodySize;
            yVelocity = (float) (-0.5 * yVelocity);
        }
        if (yPosition > INTERNAL_CANVAS_SIDE_LENGTH - halfBodySize) {
            yPosition = INTERNAL_CANVAS_SIDE_LENGTH - halfBodySize;
            yVelocity = (float) (-0.5 * yVelocity);
        }

        xVelocity = (float) (xVelocity * 0.92);
        yVelocity = (float) (yVelocity * 0.92);

        rotationAngle += (float) ((0.1 + 0.04 * (sq(xVelocity) + sq(yVelocity))) * TWO_PI / FPS);
    }

    public void display() {
        app.stroke(0);
        app.fill(fillColor);
        app.pushMatrix();
        app.translate(xPosition, yPosition);
        app.pushMatrix();
        app.rotate(rotationAngle);
        app.rect(0, 0, 32, 32);
        app.popMatrix();
        state.displayEffect(this);
        app.popMatrix();
    }
}
