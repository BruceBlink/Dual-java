package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.actor.arrow.LongbowArrowHead;
import com.likanug.dual.actor.arrow.LongbowArrowShaft;
import com.likanug.dual.actor.player.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;
import com.likanug.dual.particle.Particle;

import static com.likanug.dual.App.FPS;
import static processing.core.PApplet.*;

public class DrawLongbowPlayerActorState extends DrawBowPlayerActorState {

    private final float unitAngleSpeed = 0.1F * TWO_PI / FPS;
    private final int chargeRequiredFrameCount = (int) (0.5 * FPS);
    private final int effectColor = app.color(192, 64, 64);
    private final int ringSize = 80;
    private final float ringStrokeWeight = 5;

    public DrawLongbowPlayerActorState(App app) {
        super(app);
    }

    public PlayerActorState entryState(PlayerActor parentActor) {
        parentActor.setChargedFrameCount(0);
        return this;
    }

    public void aim(PlayerActor parentActor, AbstractInputDevice input) {
        parentActor.setAimAngle(parentActor.getAimAngle() + input.getHorizontalMoveButton() * unitAngleSpeed);
    }

    public void fire(PlayerActor parentActor) {
        final float arrowComponentInterval = 24;
        final int arrowShaftNumber = 5;
        for (int i = 0; i < arrowShaftNumber; i++) {
            LongbowArrowShaft newArrow = new LongbowArrowShaft(app);
            newArrow.setxPosition(parentActor.getxPosition() + i * arrowComponentInterval * cos(parentActor.getAimAngle()));
            newArrow.setyPosition(parentActor.getyPosition() + i * arrowComponentInterval * sin(parentActor.getAimAngle()));
            newArrow.setRotationAngle(parentActor.getAimAngle());
            newArrow.setVelocity(parentActor.getAimAngle(), 64);

            parentActor.getGroup().addArrow(newArrow);
        }

        LongbowArrowHead newArrow = new LongbowArrowHead(app);
        newArrow.setxPosition(parentActor.getxPosition() + arrowShaftNumber * arrowComponentInterval * cos(parentActor.getAimAngle()));
        newArrow.setyPosition(parentActor.getyPosition() + arrowShaftNumber * arrowComponentInterval * sin(parentActor.getAimAngle()));
        newArrow.setRotationAngle(parentActor.getAimAngle());
        newArrow.setVelocity(parentActor.getAimAngle(), 64);

        final Particle newParticle = app.system.getCommonParticleSet().getBuilder()
                .type(2)  // Line
                .position(parentActor.getxPosition(), parentActor.getyPosition())
                .polarVelocity(0, 0)
                .rotation(parentActor.getAimAngle())
                .particleColor(app.color(192, 64, 64))
                .lifespanSecond(2)
                .weight(16)
                .build();
        app.system.getCommonParticleSet().getParticleList().add(newParticle);
        parentActor.getGroup().addArrow(newArrow);
        app.system.setScreenShakeValue(app.system.getScreenShakeValue() + 10);
        parentActor.setChargedFrameCount(0);
        parentActor.setState(moveState.entryState(parentActor));
    }

    public void displayEffect(PlayerActor parentActor) {
        app.noFill();
        app.stroke(0);
        app.arc(0, 0, 100, 100, parentActor.getAimAngle() - QUARTER_PI, parentActor.getAimAngle() + QUARTER_PI);

        if (hasCompletedLongBowCharge(parentActor))
            app.stroke(effectColor);
        else
            app.stroke(0, 128);

        app.line(0, 0, 800 * cos(parentActor.getAimAngle()), 800 * sin(parentActor.getAimAngle()));

        app.rotate(-HALF_PI);
        app.strokeWeight(ringStrokeWeight);
        app.arc(0, 0, ringSize, ringSize, 0, TWO_PI * min((int) 1.0, parentActor.getChargedFrameCount() / chargeRequiredFrameCount));
        app.strokeWeight(1);
        app.rotate(+HALF_PI);
        parentActor.setChargedFrameCount(parentActor.getChargedFrameCount() + 1);
    }

    public void act(PlayerActor parentActor) {
        super.act(parentActor);

        if (parentActor.getChargedFrameCount() != chargeRequiredFrameCount) return;

        final Particle newParticle = app.system.getCommonParticleSet().getBuilder()
                .type(3)  // Ring
                .position(parentActor.getxPosition(), parentActor.getyPosition())
                .polarVelocity(0, 0)
                .particleSize(ringSize)
                .particleColor(effectColor)
                .weight(ringStrokeWeight)
                .lifespanSecond(0)
                .build();
        app.system.getCommonParticleSet().getParticleList().add(newParticle);
    }

    public boolean isDrawingLongBow() {
        return true;
    }

    public boolean hasCompletedLongBowCharge(PlayerActor parentActor) {
        return parentActor.getChargedFrameCount() >= chargeRequiredFrameCount;
    }

    public boolean buttonPressed(AbstractInputDevice input) {
        return input.isLongShotButtonPressed();
    }

    public boolean triggerPulled(PlayerActor parentActor) {
        return !buttonPressed(parentActor.getEngine().getControllingInputDevice()) && hasCompletedLongBowCharge(parentActor);
    }

}
