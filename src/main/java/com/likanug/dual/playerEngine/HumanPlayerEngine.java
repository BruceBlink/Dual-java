package com.likanug.dual.playerEngine;

import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.inputDevice.KeyInput;

public class HumanPlayerEngine extends PlayerEngine {

    private final KeyInput currentKeyInput;

    public HumanPlayerEngine(KeyInput _keyInput) {
        currentKeyInput = _keyInput;
    }


    public KeyInput getCurrentKeyInput() {
        return currentKeyInput;
    }

    public void run(PlayerActor player) {
        final int intUp = currentKeyInput.isUpPressed ? -1 : 0;
        final int intDown = currentKeyInput.isDownPressed ? 1 : 0;
        final int intLeft = currentKeyInput.isLeftPressed ? -1 : 0;
        final int intRight = currentKeyInput.isRightPressed ? 1 : 0;

        controllingInputDevice.operateMoveButton(intLeft + intRight, intUp + intDown);
        controllingInputDevice.operateShotButton(currentKeyInput.isZPressed);
        controllingInputDevice.operateLongShotButton(currentKeyInput.isXPressed);
    }

}
