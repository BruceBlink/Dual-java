package com.likanug.dual.inputDevice;

public abstract class AbstractInputDevice {

    protected int horizontalMoveButton;
    protected int verticalMoveButton;
    protected boolean shotButtonPressed;
    protected boolean longShotButtonPressed;

    public int getHorizontalMoveButton() {
        return horizontalMoveButton;
    }

    public void setHorizontalMoveButton(int horizontalMoveButton) {
        this.horizontalMoveButton = horizontalMoveButton;
    }

    public int getVerticalMoveButton() {
        return verticalMoveButton;
    }

    public void setVerticalMoveButton(int verticalMoveButton) {
        this.verticalMoveButton = verticalMoveButton;
    }

    public boolean isShotButtonPressed() {
        return shotButtonPressed;
    }

    public void setShotButtonPressed(boolean shotButtonPressed) {
        this.shotButtonPressed = shotButtonPressed;
    }

    public boolean isLongShotButtonPressed() {
        return longShotButtonPressed;
    }

    public void setLongShotButtonPressed(boolean longShotButtonPressed) {
        this.longShotButtonPressed = longShotButtonPressed;
    }

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
