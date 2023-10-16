package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.game.GameSystem;
import com.likanug.dual.particle.Particle;

import static com.likanug.dual.App.FPS;
import static com.likanug.dual.App.INTERNAL_CANVAS_SIDE_LENGTH;
import static processing.core.PConstants.HALF_PI;
import static processing.core.PConstants.TWO_PI;

public class StartGameState extends GameSystemState {

    final int frameCountPerNumber = FPS;
    final float ringSize = 200;
    final float ringColor = app.color(0);
    final float ringStrokeWeight = 5;
    int displayNumber = 4;

    public StartGameState(App app) {
        super(app);
    }

    public void runSystem(GameSystem system) {
        system.myGroup.update();
        system.otherGroup.update();
        system.myGroup.displayPlayer();
        system.otherGroup.displayPlayer();
    }

    public void displayMessage(GameSystem system) {
        final int currentNumberFrameCount = properFrameCount % frameCountPerNumber;
        if (currentNumberFrameCount == 0) displayNumber--;
        if (displayNumber <= 0) return;

        app.fill(ringColor);
        app.text(displayNumber, 0, 0);

        app.rotate(-HALF_PI);
        app.strokeWeight(3.0F);
        app.stroke(ringColor);
        app.noFill();
        app.arc(0, 0, ringSize, ringSize, 0, TWO_PI * properFrameCount % frameCountPerNumber / frameCountPerNumber);
        app.strokeWeight(1.0F);
    }

    public void checkStateTransition(GameSystem system) {
        if (properFrameCount >= frameCountPerNumber * 3) {
            final Particle newParticle = system.commonParticleSet.builder
                    .type(3)  // Ring
                    .position(INTERNAL_CANVAS_SIDE_LENGTH * 0.5F, INTERNAL_CANVAS_SIDE_LENGTH * 0.5F)
                    .polarVelocity(0, 0)
                    .particleSize((int) ringSize)
                    .particleColor((int) ringColor)
                    .weight(ringStrokeWeight)
                    .lifespanSecond(1)
                    .build();
            system.commonParticleSet.particleList.add(newParticle);

            system.currentState = new PlayGameState(app);
        }
    }

}
