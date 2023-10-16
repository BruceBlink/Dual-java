package com.likanug.dual.playerEngine;

import com.likanug.dual.App;
import com.likanug.dual.actor.AbstractPlayerActor;
import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.inputDevice.AbstractInputDevice;

import static processing.core.PApplet.abs;
import static processing.core.PApplet.radians;
import static processing.core.PConstants.*;

public class KillPlayerPlan extends PlayerPlan {

    PlayerPlan movePlan, escapePlan;

    public KillPlayerPlan(App app) {
        this.app = app;
    }

    void execute(PlayerActor player, AbstractInputDevice input) {
        int horizontalMove;
        final float relativeAngle = player.getAngle(player.group.enemyGroup.player) - player.aimAngle;
        if (abs(relativeAngle) < radians(1)) horizontalMove = 0;
        else {
            if ((relativeAngle + TWO_PI) % TWO_PI > PI) horizontalMove = -1;
            else horizontalMove = +1;
        }
        input.operateMoveButton(horizontalMove, 0);

        input.operateShotButton(false);

        input.operateLongShotButton(!player.state.hasCompletedLongBowCharge(player) || !(app.random(1) < 0.05));
    }

    PlayerPlan nextPlan(PlayerActor player) {
        final AbstractPlayerActor enemy = player.group.enemyGroup.player;

        if (abs(player.getAngle(player.group.enemyGroup.player) - player.aimAngle) > QUARTER_PI) return movePlan;
        if (player.getDistance(enemy) < 400.0) return movePlan;
        if (!player.engine.controllingInputDevice.longShotButtonPressed) return movePlan;

        return this;
    }

}
