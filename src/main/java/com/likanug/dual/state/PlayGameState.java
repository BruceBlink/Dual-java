package com.likanug.dual.state;

import com.likanug.dual.App;
import com.likanug.dual.actor.*;
import com.likanug.dual.game.GameSystem;

import static com.likanug.dual.App.FPS;
import static processing.core.PApplet.atan2;
import static processing.core.PConstants.HALF_PI;

public class PlayGameState extends GameSystemState {

    public PlayGameState(App app) {
        super(app);
    }

    public void runSystem(GameSystem system) {
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

    public void displayMessage(GameSystem system) {
        int messageDurationFrameCount = FPS;
        if (properFrameCount >= messageDurationFrameCount) return;
        app.fill(0, (float) (255.0 * (1.0 - properFrameCount / messageDurationFrameCount)));
        app.text("Go", 0.0F, 0.0F);
    }

    public void checkStateTransition(GameSystem system) {
        if (system.myGroup.getPlayer().isNull()) {
            system.currentState = new GameResultState(app, "You lose.");
        } else if (system.otherGroup.getPlayer().isNull()) {
            system.currentState = new GameResultState(app, "You win.");
        }
    }

    public void checkCollision() {
        final ActorGroup myGroup = app.system.myGroup;
        final ActorGroup otherGroup = app.system.otherGroup;

        for (AbstractArrowActor eachMyArrow : myGroup.getArrowList()) {
            for (AbstractArrowActor eachEnemyArrow : otherGroup.getArrowList()) {
                if (eachMyArrow.isNotCollided(eachEnemyArrow)) continue;
                breakArrow(eachMyArrow, myGroup);
                breakArrow(eachEnemyArrow, otherGroup);
            }
        }

        if (!otherGroup.getPlayer().isNull()) {
            for (AbstractArrowActor eachMyArrow : myGroup.getArrowList()) {

                AbstractPlayerActor enemyPlayer = otherGroup.getPlayer();
                if (eachMyArrow.isNotCollided(enemyPlayer)) continue;

                if (eachMyArrow.isLethal()) killPlayer(otherGroup.getPlayer());
                else thrustPlayerActor(eachMyArrow, (PlayerActor) enemyPlayer);

                breakArrow(eachMyArrow, myGroup);
            }
        }

        if (!myGroup.getPlayer().isNull()) {
            for (AbstractArrowActor eachEnemyArrow : otherGroup.getArrowList()) {
                if (eachEnemyArrow.isNotCollided(myGroup.getPlayer())) continue;

                if (eachEnemyArrow.isLethal()) killPlayer(myGroup.getPlayer());
                else thrustPlayerActor(eachEnemyArrow, (PlayerActor) myGroup.getPlayer());

                breakArrow(eachEnemyArrow, otherGroup);
            }
        }
    }

    public void killPlayer(AbstractPlayerActor player) {
        app.system.addSquareParticles(player.getxPosition(), player.getyPosition(), 50, 16, 2, 10, 4);
        player.getGroup().setPlayer(new NullPlayerActor(app));
        app.system.screenShakeValue = 50;
    }

    public void breakArrow(AbstractArrowActor arrow, ActorGroup group) {
        app.system.addSquareParticles(arrow.getxPosition(), arrow.getyPosition(), 10, 7, 1, 5, 1);
        group.getRemovingArrowList().add(arrow);
    }

    public void thrustPlayerActor(Actor referenceActor, PlayerActor targetPlayerActor) {
        final float relativeAngle = atan2(targetPlayerActor.getyPosition() - referenceActor.getyPosition(), targetPlayerActor.getxPosition() - referenceActor.getxPosition());
        final float thrustAngle = relativeAngle + app.random((float) (-0.5 * HALF_PI), (float) (0.5 * HALF_PI));
        targetPlayerActor.setxVelocity(targetPlayerActor.getxVelocity() * 20);
        targetPlayerActor.setyVelocity(targetPlayerActor.getyVelocity() * 20);
        targetPlayerActor.setState(app.system.damagedState.entryState(targetPlayerActor));
        app.system.screenShakeValue += 10;
    }
}
