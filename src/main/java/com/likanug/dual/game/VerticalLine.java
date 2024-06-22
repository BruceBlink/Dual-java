package com.likanug.dual.game;

import com.likanug.dual.App;

import static com.likanug.dual.App.INTERNAL_CANVAS_SIDE_WIDTH;

public class VerticalLine extends BackgroundLine {

    public VerticalLine(App app) {
        super(app, app.random(INTERNAL_CANVAS_SIDE_WIDTH));
        //super(app, app.random(INTERNAL_CANVAS_SIDE_LENGTH));
    }

    public void display() {
        app.line(position, 0.0F, position, INTERNAL_CANVAS_SIDE_WIDTH);
    }

    public float getMaxPosition() {
        return INTERNAL_CANVAS_SIDE_WIDTH;
    }
}
