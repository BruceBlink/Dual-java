package com.likanug.dual.playerEngine;

import com.likanug.dual.App;
import com.likanug.dual.actor.PlayerActor;

public class ComputerPlayerEngine extends PlayerEngine {

    private final App app;
    private final int planUpdateFrameCount = 10;
    private PlayerPlan currentPlan;

    public ComputerPlayerEngine(App app) {
        this.app = app;
        // There shoud be a smarter way!!!
        final MovePlayerPlan move = new MovePlayerPlan(app);
        final JabPlayerPlan jab = new JabPlayerPlan(app);
        final KillPlayerPlan kill = new KillPlayerPlan(app);
        move.setMovePlan(move);
        move.setJabPlan(jab);
        move.setKillPlan(kill);
        jab.setMovePlan(move);
        jab.setJabPlan(jab);
        jab.setKillPlan(kill);
        kill.setMovePlan(move);

        currentPlan = move;
    }

    public App getApp() {
        return app;
    }

    public int getPlanUpdateFrameCount() {
        return planUpdateFrameCount;
    }

    public PlayerPlan getCurrentPlan() {
        return currentPlan;
    }

    public void setCurrentPlan(PlayerPlan currentPlan) {
        this.currentPlan = currentPlan;
    }

    public void run(PlayerActor player) {
        currentPlan.execute(player, controllingInputDevice);

        if (app.frameCount % planUpdateFrameCount == 0) currentPlan = currentPlan.nextPlan(player);
    }

}
