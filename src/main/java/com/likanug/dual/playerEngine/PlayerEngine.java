package com.likanug.dual.playerEngine;

import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;
import com.likanug.dual.inputDevice.InputDevice;

public abstract class PlayerEngine {

    public final AbstractInputDevice controllingInputDevice;

    PlayerEngine() {
        controllingInputDevice = new InputDevice();
    }

    public abstract void run(PlayerActor player);

}
