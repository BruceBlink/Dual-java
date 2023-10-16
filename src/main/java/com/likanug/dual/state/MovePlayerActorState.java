package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;

public class MovePlayerActorState extends PlayerActorState {

    public PlayerActorState drawShortbowState;
    public PlayerActorState drawLongbowState;

    public MovePlayerActorState(App app) {
        super(app);
    }

    public void act(PlayerActor parentActor) {
        final AbstractInputDevice input = parentActor.engine.controllingInputDevice;
        parentActor.addVelocity(input.horizontalMoveButton, input.verticalMoveButton);

        if (input.shotButtonPressed) {
            parentActor.state = drawShortbowState.entryState(parentActor);
            parentActor.aimAngle = getEnemyPlayerActorAngle(parentActor);
            return;
        }
        if (input.longShotButtonPressed) {
            parentActor.state = drawLongbowState.entryState(parentActor);
            parentActor.aimAngle = getEnemyPlayerActorAngle(parentActor);
        }
    }

    public void displayEffect(PlayerActor parentActor) {
    }

    public PlayerActorState entryState(PlayerActor parentActor) {
        return this;
    }

}
