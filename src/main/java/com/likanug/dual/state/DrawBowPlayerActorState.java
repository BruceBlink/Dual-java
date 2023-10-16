package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;

public abstract class DrawBowPlayerActorState extends PlayerActorState {

    public PlayerActorState moveState;

    public DrawBowPlayerActorState(App app) {
        super(app);
    }

    public void act(PlayerActor parentActor) {
        final AbstractInputDevice input = parentActor.engine.controllingInputDevice;
        aim(parentActor, input);

        parentActor.addVelocity((float) (0.25 * input.horizontalMoveButton), (float) (0.25 * input.verticalMoveButton));

        if (triggerPulled(parentActor)) fire(parentActor);

        if (!buttonPressed(input)) {
            parentActor.state = moveState.entryState(parentActor);
        }
    }

    abstract void aim(PlayerActor parentActor, AbstractInputDevice input);

    abstract void fire(PlayerActor parentActor);

    abstract boolean buttonPressed(AbstractInputDevice input);

    abstract boolean triggerPulled(PlayerActor parentActor);

}
