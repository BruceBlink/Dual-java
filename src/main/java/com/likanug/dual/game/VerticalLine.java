package com.likanug.dual.game;

import static com.likanug.dual.App.INTERNAL_CANVAS_SIDE_LENGTH;

public class VerticalLine extends BackgroundLine {

    public VerticalLine() {
        super();
        //super(app, app.random(INTERNAL_CANVAS_SIDE_LENGTH));
    }

    public void display() {
        app.line(position, 0.0F, position, INTERNAL_CANVAS_SIDE_LENGTH);
    }

    public float getMaxPosition() {
        return INTERNAL_CANVAS_SIDE_LENGTH;
    }
}
