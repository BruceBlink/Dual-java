package com.likanug.dual.actor;

import java.util.ArrayList;

public class ActorGroup {
    public ActorGroup enemyGroup;

    public AbstractPlayerActor player;
    public final ArrayList<AbstractArrowActor> arrowList = new ArrayList<AbstractArrowActor>();
    public final ArrayList<AbstractArrowActor> removingArrowList = new ArrayList<AbstractArrowActor>();

    public void update() {
        player.update();

        if (!removingArrowList.isEmpty()) {
            arrowList.removeAll(removingArrowList);
            removingArrowList.clear();
        }

        for (AbstractArrowActor eachArrow : arrowList) {
            eachArrow.update();
        }
    }

    public void act() {
        player.act();
        for (AbstractArrowActor eachArrow : arrowList) {
            eachArrow.act();
        }
    }

    public void setPlayer(PlayerActor newPlayer) {
        player = newPlayer;
        newPlayer.group = this;
    }

    public void addArrow(AbstractArrowActor newArrow) {
        arrowList.add(newArrow);
        newArrow.group = this;
    }

    public void displayPlayer() {
        player.display();
    }

    public void displayArrows() {
        for (AbstractArrowActor eachArrow : arrowList) {
            eachArrow.display();
        }
    }
}