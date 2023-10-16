package com.likanug.dual.playerEngine;

import com.likanug.dual.App;
import com.likanug.dual.actor.PlayerActor;

public class ComputerPlayerEngine extends PlayerEngine {

    private final App app;
    final int planUpdateFrameCount = 10;
    PlayerPlan currentPlan;

    public ComputerPlayerEngine(App app) {
        this.app = app;
        // There shoud be a smarter way!!!
        final MovePlayerPlan move = new MovePlayerPlan(app);
        final JabPlayerPlan jab = new JabPlayerPlan(app);
        final KillPlayerPlan kill = new KillPlayerPlan(app);
        move.movePlan = move;
        move.jabPlan = jab;
        move.killPlan = kill;
        jab.movePlan = move;
        jab.jabPlan = jab;
        jab.killPlan = kill;
        kill.movePlan = move;

        currentPlan = move;
    }

    public void run(PlayerActor player) {
        currentPlan.execute(player, controllingInputDevice);

        if (app.frameCount % planUpdateFrameCount == 0) currentPlan = currentPlan.nextPlan(player);
    }

}
