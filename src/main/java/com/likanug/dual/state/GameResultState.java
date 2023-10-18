package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.game.GameSystem;

import static com.likanug.dual.App.FPS;
import static com.likanug.dual.App.smallFont;

public class GameResultState extends GameSystemState {

    private final String resultMessage;
    private final int durationFrameCount = FPS;

    public GameResultState(App app, String msg) {
        super(app);
        resultMessage = msg;
    }

    public void runSystem(GameSystem system) {
        system.getMyGroup().update();
        system.getOtherGroup().update();
        system.getMyGroup().displayPlayer();
        system.getOtherGroup().displayPlayer();

        system.getCommonParticleSet().update();
        system.getCommonParticleSet().display();
    }

    public void displayMessage(GameSystem system) {
        if (system.isDemoPlay()) return;

        app.fill(0);
        app.text(resultMessage, 0, 0);
        if (properFrameCount > durationFrameCount) {
            app.pushStyle();
            app.textFont(smallFont, 20);
            app.text("Press X key to reset.", 0, 80);
            app.popStyle();
        }
    }

    public void checkStateTransition(GameSystem system) {
        if (system.isDemoPlay()) {
            if (properFrameCount > durationFrameCount * 3) {
                app.newGame(true, system.isShowsInstructionWindow());
            }
        } else {
            if (properFrameCount > durationFrameCount && app.getCurrentKeyInput().isXPressed) {
                app.newGame(true, true);  // back to demoplay with instruction window
            }
        }
    }
}
