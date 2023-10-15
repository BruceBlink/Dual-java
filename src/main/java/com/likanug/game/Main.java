package com.likanug.game;

import processing.core.PApplet;

public class Main extends PApplet {


    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    public static final int FPS = 60;

    public static void main(String[] args) {
        PApplet.main("com.likanug.game.Main");
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
    public void mouseClicked() {
        super.mouseClicked();
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
    }
}