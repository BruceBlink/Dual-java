package com.likanug.dual.playerEngine;

import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;

public class JabPlayerPlan extends DefaultPlayerPlan {
    public void execute(PlayerActor player, AbstractInputDevice input) {
        super.execute(player, input);
        input.operateShotButton(true);
    }

}
