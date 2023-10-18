package com.likanug.dual.playerEngine;

import com.likanug.dual.App;
import com.likanug.dual.actor.player.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;

public class MovePlayerPlan extends DefaultPlayerPlan {
    public MovePlayerPlan(App app) {
        super(app);
    }

    public void execute(PlayerActor player, AbstractInputDevice input) {
        super.execute(player, input);
        input.operateShotButton(false);
    }

}
