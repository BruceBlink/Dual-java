package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.actor.ShortbowArrow;
import com.likanug.dual.inputDevice.AbstractInputDevice;

import static com.likanug.dual.App.FPS;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PConstants.QUARTER_PI;

public class DrawShortbowPlayerActorState extends DrawBowPlayerActorState {

    public final int fireIntervalFrameCount = (int) (FPS * 0.2);

    public DrawShortbowPlayerActorState(App app) {
        super(app);
    }

    public void aim(PlayerActor parentActor, AbstractInputDevice input) {
        parentActor.aimAngle = getEnemyPlayerActorAngle(parentActor);
    }

    public void fire(PlayerActor parentActor) {
        ShortbowArrow newArrow = new ShortbowArrow(app);
        final float directionAngle = parentActor.aimAngle;
        newArrow.xPosition = parentActor.xPosition + 24 * cos(directionAngle);
        newArrow.yPosition = parentActor.yPosition + 24 * sin(directionAngle);
        newArrow.rotationAngle = directionAngle;
        newArrow.setVelocity(directionAngle, 24);

        parentActor.group.addArrow(newArrow);
    }

    public void displayEffect(PlayerActor parentActor) {
        app.line(0, 0, 70 * cos(parentActor.aimAngle), 70 * sin(parentActor.aimAngle));
        app.noFill();
        app.arc(0, 0, 100, 100, parentActor.aimAngle - QUARTER_PI, parentActor.aimAngle + QUARTER_PI);
    }

    public PlayerActorState entryState(PlayerActor parentActor) {
        return this;
    }

    public boolean buttonPressed(AbstractInputDevice input) {
        return input.shotButtonPressed;
    }

    public boolean triggerPulled(PlayerActor parentActor) {
        return app.frameCount % fireIntervalFrameCount == 0;
    }

}
