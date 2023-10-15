package com.likanug.game;

import processing.core.PApplet;

public class App extends PApplet {


    public static int WIDTH = 640;
    public static int HEIGHT = 640;

    public static final int FPS = 60;

    public static void main(String[] args) {
        PApplet.main("com.likanug.game.App");
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }


    @Override
    public void setup() {
        frameRate(FPS);
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