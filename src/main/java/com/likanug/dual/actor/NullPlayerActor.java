package com.likanug.dual.actor;

import com.likanug.dual.App;

public class NullPlayerActor extends AbstractPlayerActor {

    public NullPlayerActor(App app) {
        super(0, null, app);
    }

    public void act() {
    }

    public void display() {
    }

    public boolean isNull() {
        return true;
    }
}
