package com.likanug.dual.game;

import com.likanug.dual.App;

import java.util.ArrayList;

public class GameBackground {

    App app;
    final ArrayList<BackgroundLine> lineList = new ArrayList<>();
    final float maxAccelerationMagnitude;
    final float lineColor;

    GameBackground(float col, float maxAcc, App app) {

        lineColor = col;
        maxAccelerationMagnitude = maxAcc;
        for (int i = 0; i < 10; i++) {
            lineList.add(new HorizontalLine(app));
        }
        for (int i = 0; i < 10; i++) {
            lineList.add(new VerticalLine());
        }
    }

    void update() {
        for (BackgroundLine eachLine : lineList) {
            eachLine.update(app.random(-maxAccelerationMagnitude, maxAccelerationMagnitude));
        }
    }

    void display() {
        app.stroke(lineColor);
        for (BackgroundLine eachLine : lineList) {
            eachLine.display();
        }
    }
}
