package com.likanug.dual.inputDevice;

public abstract class AbstractInputDevice {

    public int horizontalMoveButton;
    public int verticalMoveButton;
    public boolean shotButtonPressed;
    public boolean longShotButtonPressed;

    public void operateMoveButton(int horizontal, int vertical) {
        horizontalMoveButton = horizontal;
        verticalMoveButton = vertical;
    }

    public void operateShotButton(boolean pressed) {
        shotButtonPressed = pressed;
    }

    public void operateLongShotButton(boolean pressed) {
        longShotButtonPressed = pressed;
    }

}
