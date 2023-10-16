package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.game.GameSystem;

import static com.likanug.dual.App.INTERNAL_CANVAS_SIDE_LENGTH;

public abstract class GameSystemState {

    protected App app;
    protected int properFrameCount;

    public GameSystemState(App app) {
        this.app = app;
    }

    public void run(GameSystem system) {
        runSystem(system);

        app.translate(INTERNAL_CANVAS_SIDE_LENGTH * 0.5F, INTERNAL_CANVAS_SIDE_LENGTH * 0.5F);
        displayMessage(system);

        checkStateTransition(system);

        properFrameCount++;
    }

    abstract void runSystem(GameSystem system);

    abstract void displayMessage(GameSystem system);

    abstract void checkStateTransition(GameSystem system);

}