package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.actor.LongbowArrowHead;
import com.likanug.dual.actor.LongbowArrowShaft;
import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;
import com.likanug.dual.particle.Particle;

import static com.likanug.dual.App.FPS;
import static processing.core.PApplet.*;

public class DrawLongbowPlayerActorState extends DrawBowPlayerActorState {

    final float unitAngleSpeed = (float) (0.1 * TWO_PI / FPS);
    final int chargeRequiredFrameCount = (int) (0.5 * FPS);
    final int effectColor = app.color(192, 64, 64);
    final int ringSize = 80;
    final float ringStrokeWeight = 5;

    public DrawLongbowPlayerActorState(App app) {
        super(app);
    }

    public PlayerActorState entryState(PlayerActor parentActor) {
        parentActor.chargedFrameCount = 0;
        return this;
    }

    public void aim(PlayerActor parentActor, AbstractInputDevice input) {
        parentActor.aimAngle += input.horizontalMoveButton * unitAngleSpeed;
    }

    public void fire(PlayerActor parentActor) {
        final float arrowComponentInterval = 24;
        final int arrowShaftNumber = 5;
        for (int i = 0; i < arrowShaftNumber; i++) {
            LongbowArrowShaft newArrow = new LongbowArrowShaft(app);
            newArrow.xPosition = parentActor.xPosition + i * arrowComponentInterval * cos(parentActor.aimAngle);
            newArrow.yPosition = parentActor.yPosition + i * arrowComponentInterval * sin(parentActor.aimAngle);
            newArrow.rotationAngle = parentActor.aimAngle;
            newArrow.setVelocity(parentActor.aimAngle, 64);

            parentActor.group.addArrow(newArrow);
        }

        LongbowArrowHead newArrow = new LongbowArrowHead(app);
        newArrow.xPosition = parentActor.xPosition + arrowShaftNumber * arrowComponentInterval * cos(parentActor.aimAngle);
        newArrow.yPosition = parentActor.yPosition + arrowShaftNumber * arrowComponentInterval * sin(parentActor.aimAngle);
        newArrow.rotationAngle = parentActor.aimAngle;
        newArrow.setVelocity(parentActor.aimAngle, 64);

        final Particle newParticle = app.system.commonParticleSet.builder
                .type(2)  // Line
                .position(parentActor.xPosition, parentActor.yPosition)
                .polarVelocity(0, 0)
                .rotation(parentActor.aimAngle)
                .particleColor(app.color(192, 64, 64))
                .lifespanSecond(2)
                .weight(16)
                .build();
        app.system.commonParticleSet.particleList.add(newParticle);

        parentActor.group.addArrow(newArrow);

        app.system.screenShakeValue += 10;

        parentActor.chargedFrameCount = 0;
        parentActor.state = moveState.entryState(parentActor);
    }

    public void displayEffect(PlayerActor parentActor) {
        app.noFill();
        app.stroke(0);
        app.arc(0, 0, 100, 100, parentActor.aimAngle - QUARTER_PI, parentActor.aimAngle + QUARTER_PI);

        if (hasCompletedLongBowCharge(parentActor))
            app.stroke(effectColor);
        else
            app.stroke(0, 128);

        app.line(0, 0, 800 * cos(parentActor.aimAngle), 800 * sin(parentActor.aimAngle));

        app.rotate(-HALF_PI);
        app.strokeWeight(ringStrokeWeight);
        app.arc(0, 0, ringSize, ringSize, 0, TWO_PI * min((int) 1.0, parentActor.chargedFrameCount / chargeRequiredFrameCount));
        app.strokeWeight(1);
        app.rotate(+HALF_PI);

        parentActor.chargedFrameCount++;
    }

    public void act(PlayerActor parentActor) {
        super.act(parentActor);

        if (parentActor.chargedFrameCount != chargeRequiredFrameCount) return;

        final Particle newParticle = app.system.commonParticleSet.builder
                .type(3)  // Ring
                .position(parentActor.xPosition, parentActor.yPosition)
                .polarVelocity(0, 0)
                .particleSize(ringSize)
                .particleColor(effectColor)
                .weight(ringStrokeWeight)
                .lifespanSecond(0)
                .build();
        app.system.commonParticleSet.particleList.add(newParticle);
    }

    public boolean isDrawingLongBow() {
        return true;
    }

    public boolean hasCompletedLongBowCharge(PlayerActor parentActor) {
        return parentActor.chargedFrameCount >= chargeRequiredFrameCount;
    }

    public boolean buttonPressed(AbstractInputDevice input) {
        return input.longShotButtonPressed;
    }

    public boolean triggerPulled(PlayerActor parentActor) {
        return !buttonPressed(parentActor.engine.controllingInputDevice) && hasCompletedLongBowCharge(parentActor);
    }

}
