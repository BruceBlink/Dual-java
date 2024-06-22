package com.likanug.dual.game;

import com.likanug.dual.App;

import static com.likanug.dual.App.INTERNAL_CANVAS_SIDE_WIDTH;

public class HorizontalLine extends BackgroundLine {

    public HorizontalLine(App app) {
        super(app, app.random(INTERNAL_CANVAS_SIDE_WIDTH));
    }

    @Override
    public void display() {
        app.line(0.0F, position, INTERNAL_CANVAS_SIDE_WIDTH, position);
    }

    @Override
    public float getMaxPosition() {
        return INTERNAL_CANVAS_SIDE_WIDTH;
    }
}
