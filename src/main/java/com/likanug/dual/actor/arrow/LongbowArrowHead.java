package com.likanug.dual.actor.arrow;

import com.likanug.dual.App;

public class LongbowArrowHead extends LongbowArrowComponent {

    private final float halfHeadLength = 24;
    private final float halfHeadWidth = 24;

    public LongbowArrowHead(App app) {
        super(app);
    }

    public void display() {
        app.strokeWeight(5);
        app.stroke(0);
        app.fill(0);
        app.pushMatrix();
        app.translate(xPosition, yPosition);
        app.rotate(rotationAngle);
        app.line(-halfLength, 0, 0, 0);
        app.quad(
                0, 0,
                -halfHeadLength, -halfHeadWidth,
                +halfHeadLength, 0,
                -halfHeadLength, +halfHeadWidth
        );
        app.popMatrix();
        app.strokeWeight(1);

    }
}
