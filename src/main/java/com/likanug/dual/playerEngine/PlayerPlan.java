package com.likanug.dual.playerEngine;

import com.likanug.dual.App;
import com.likanug.dual.actor.player.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;

public abstract class PlayerPlan {

    protected App app;

    protected abstract void execute(PlayerActor player, AbstractInputDevice input);

    protected abstract PlayerPlan nextPlan(PlayerActor player);

}
