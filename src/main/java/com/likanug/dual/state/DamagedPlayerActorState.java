package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.actor.PlayerActor;

import static com.likanug.dual.App.FPS;

public class DamagedPlayerActorState extends PlayerActorState {

    public PlayerActorState moveState;
    public final int durationFrameCount = (int) (0.75 * FPS);

    public DamagedPlayerActorState(App app) {
        super(app);
    }

    public void act(PlayerActor parentActor) {
        parentActor.damageRemainingFrameCount--;
        if (parentActor.damageRemainingFrameCount <= 0) parentActor.state = moveState.entryState(parentActor);
    }

    public void displayEffect(PlayerActor parentActor) {
        app.noFill();
        app.stroke(192, 64, 64, (float) (255 * parentActor.damageRemainingFrameCount) / durationFrameCount);
        app.ellipse(0, 0, 64, 64);
    }

    public PlayerActorState entryState(PlayerActor parentActor) {
        parentActor.damageRemainingFrameCount = durationFrameCount;
        return this;
    }

    public boolean isDamaged() {
        return true;
    }

}
