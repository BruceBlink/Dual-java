package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;

public abstract class DrawBowPlayerActorState extends PlayerActorState {

    protected PlayerActorState moveState;

    public DrawBowPlayerActorState(App app) {
        super(app);
    }

    public void act(PlayerActor parentActor) {
        final AbstractInputDevice input = parentActor.getEngine().getControllingInputDevice();
        aim(parentActor, input);

        parentActor.addVelocity((float) (0.25 * input.getHorizontalMoveButton()), (float) (0.25 * input.getVerticalMoveButton()));

        if (triggerPulled(parentActor)) fire(parentActor);

        if (!buttonPressed(input)) {
            parentActor.setState(moveState.entryState(parentActor));
        }
    }

    protected abstract void aim(PlayerActor parentActor, AbstractInputDevice input);

    protected abstract void fire(PlayerActor parentActor);

    protected abstract boolean buttonPressed(AbstractInputDevice input);

    protected abstract boolean triggerPulled(PlayerActor parentActor);

    public PlayerActorState getMoveState() {
        return moveState;
    }

    public void setMoveState(PlayerActorState moveState) {
        this.moveState = moveState;
    }
}
