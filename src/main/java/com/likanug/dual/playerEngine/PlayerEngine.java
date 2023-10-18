package com.likanug.dual.playerEngine;

import com.likanug.dual.actor.player.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;
import com.likanug.dual.inputDevice.InputDevice;

public abstract class PlayerEngine {

    protected final AbstractInputDevice controllingInputDevice;

    protected PlayerEngine() {
        controllingInputDevice = new InputDevice();
    }

    public abstract void run(PlayerActor player);

    public AbstractInputDevice getControllingInputDevice() {
        return controllingInputDevice;
    }
}
