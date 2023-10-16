package com.likanug.dual.actor;

import com.likanug.dual.App;

import static com.likanug.dual.App.INTERNAL_CANVAS_SIDE_LENGTH;

public abstract class AbstractArrowActor extends Actor {

    protected final float halfLength;

    public AbstractArrowActor(float _collisionRadius, float _halfLength, App app) {
        super(_collisionRadius, app);
        halfLength = _halfLength;
    }

    public void update() {
        super.update();
        if (
                xPosition < -halfLength ||
                        xPosition > INTERNAL_CANVAS_SIDE_LENGTH + halfLength ||
                        yPosition < -halfLength ||
                        yPosition > INTERNAL_CANVAS_SIDE_LENGTH + halfLength
        ) {
            group.getRemovingArrowList().add(this);
        }
    }

    public abstract boolean isLethal();
}
