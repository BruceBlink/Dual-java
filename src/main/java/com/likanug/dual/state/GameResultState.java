package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.game.GameSystem;

import static com.likanug.dual.App.FPS;
import static com.likanug.dual.App.smallFont;

public class GameResultState extends GameSystemState {

    final String resultMessage;
    final int durationFrameCount = FPS;

    GameResultState(App app, String msg) {
        super(app);
        resultMessage = msg;
    }

    void runSystem(GameSystem system) {
        system.myGroup.update();
        system.otherGroup.update();
        system.myGroup.displayPlayer();
        system.otherGroup.displayPlayer();

        system.commonParticleSet.update();
        system.commonParticleSet.display();
    }

    void displayMessage(GameSystem system) {
        if (system.demoPlay) return;

        app.fill(0);
        app.text(resultMessage, 0, 0);
        if (properFrameCount > durationFrameCount) {
            app.pushStyle();
            app.textFont(smallFont, 20);
            app.text("Press X key to reset.", 0, 80);
            app.popStyle();
        }
    }

    void checkStateTransition(GameSystem system) {
        if (system.demoPlay) {
            if (properFrameCount > durationFrameCount * 3) {
                app.newGame(true, system.showsInstructionWindow);
            }
        } else {
            if (properFrameCount > durationFrameCount && app.currentKeyInput.isXPressed) {
                app.newGame(true, true);  // back to demoplay with instruction window
            }
        }
    }
}
