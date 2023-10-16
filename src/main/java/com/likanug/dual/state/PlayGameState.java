package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.actor.*;
import com.likanug.dual.game.GameSystem;

import static com.likanug.dual.App.FPS;
import static processing.core.PApplet.*;
import static processing.core.PConstants.HALF_PI;

public class PlayGameState extends GameSystemState {

    int messageDurationFrameCount = FPS;

    public PlayGameState(App app) {
        super(app);
    }

    void runSystem(GameSystem system) {
        system.myGroup.update();
        system.myGroup.act();
        system.otherGroup.update();
        system.otherGroup.act();
        system.myGroup.displayPlayer();
        system.otherGroup.displayPlayer();
        system.myGroup.displayArrows();
        system.otherGroup.displayArrows();

        checkCollision();

        system.commonParticleSet.update();
        system.commonParticleSet.display();
    }

    void displayMessage(GameSystem system) {
        if (properFrameCount >= messageDurationFrameCount) return;
        app.fill(0, (float) (255.0 * (1.0 - properFrameCount / messageDurationFrameCount)));
        app.text("Go", 0.0F, 0.0F);
    }

    void checkStateTransition(GameSystem system) {
        if (system.myGroup.player.isNull()) {
            system.currentState = new GameResultState(app, "You lose.");
        } else if (system.otherGroup.player.isNull()) {
            system.currentState = new GameResultState(app, "You win.");
        }
    }

    void checkCollision() {
        final ActorGroup myGroup = app.system.myGroup;
        final ActorGroup otherGroup = app.system.otherGroup;

        for (AbstractArrowActor eachMyArrow : myGroup.arrowList) {
            for (AbstractArrowActor eachEnemyArrow : otherGroup.arrowList) {
                if (!eachMyArrow.isCollided(eachEnemyArrow)) continue;
                breakArrow(eachMyArrow, myGroup);
                breakArrow(eachEnemyArrow, otherGroup);
            }
        }

        if (!otherGroup.player.isNull()) {
            for (AbstractArrowActor eachMyArrow : myGroup.arrowList) {

                AbstractPlayerActor enemyPlayer = otherGroup.player;
                if (!eachMyArrow.isCollided(enemyPlayer)) continue;

                if (eachMyArrow.isLethal()) killPlayer(otherGroup.player);
                else thrustPlayerActor(eachMyArrow, (PlayerActor) enemyPlayer);

                breakArrow(eachMyArrow, myGroup);
            }
        }

        if (!myGroup.player.isNull()) {
            for (AbstractArrowActor eachEnemyArrow : otherGroup.arrowList) {
                if (!eachEnemyArrow.isCollided(myGroup.player)) continue;

                if (eachEnemyArrow.isLethal()) killPlayer(myGroup.player);
                else thrustPlayerActor(eachEnemyArrow, (PlayerActor) myGroup.player);

                breakArrow(eachEnemyArrow, otherGroup);
            }
        }
    }

    void killPlayer(AbstractPlayerActor player) {
        app.system.addSquareParticles(player.xPosition, player.yPosition, 50, 16, 2, 10, 4);
        player.group.player = new NullPlayerActor(app);
        app.system.screenShakeValue = 50;
    }

    void breakArrow(AbstractArrowActor arrow, ActorGroup group) {
        app.system.addSquareParticles(arrow.xPosition, arrow.yPosition, 10, 7, 1, 5, 1);
        group.removingArrowList.add(arrow);
    }

    void thrustPlayerActor(Actor referenceActor, PlayerActor targetPlayerActor) {
        final float relativeAngle = atan2(targetPlayerActor.yPosition - referenceActor.yPosition, targetPlayerActor.xPosition - referenceActor.xPosition);
        final float thrustAngle = relativeAngle + app.random((float) (-0.5 * HALF_PI), (float) (0.5 * HALF_PI));
        targetPlayerActor.xVelocity += 20 * cos(thrustAngle);
        targetPlayerActor.yVelocity += 20 * sin(thrustAngle);
        targetPlayerActor.state = app.system.damagedState.entryState(targetPlayerActor);
        app.system.screenShakeValue += 10;
    }
}
