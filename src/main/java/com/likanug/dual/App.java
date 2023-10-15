package com.likanug.dual;

import processing.core.PApplet;
import processing.core.PFont;

public class App extends PApplet {


    public static int WIDTH = 640;
    public static int HEIGHT = 640;

    public static final int FPS = 60;

    PFont smallFont, largeFont;

    public static void main(String[] args) {
        PApplet.main("com.likanug.dual.App");
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }


    @Override
    public void setup() {
        frameRate(FPS);
        final String fontFilePath = "Lato-Regular.ttf";
        //final String fontName = "Lato";
        //create text font
        smallFont = createFont(fontFilePath, 20.0F, true);
        largeFont = createFont(fontFilePath, 96.0F, true);
        //set text font
        textFont(smallFont, 48.0F);
        // set text, rect and ellipse draw mode are center
        textAlign(CENTER, CENTER);
        rectMode(CENTER);
        ellipseMode(CENTER);
    }


    @Override
    public void draw() {

    }

    @Override
    public void mousePressed() {
        super.mousePressed();
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
    }
}