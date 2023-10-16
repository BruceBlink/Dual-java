package com.likanug.dual.playerEngine;

import com.likanug.dual.App;
import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;

public abstract class PlayerPlan {

    protected App app;

    abstract void execute(PlayerActor player, AbstractInputDevice input);

    abstract PlayerPlan nextPlan(PlayerActor player);

}
