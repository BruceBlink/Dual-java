package com.likanug.dual;

import com.likanug.dual.game.GameSystem;
import com.likanug.dual.inputDevice.KeyInput;
import processing.core.PApplet;
import processing.core.PFont;

public class App extends PApplet {


    public static final int FPS = 60;
    public static final int INTERNAL_CANVAS_SIDE_LENGTH = 640;
    public static PFont smallFont, largeFont;

    private KeyInput currentKeyInput;

    private GameSystem system;

    private boolean paused;

    public static void main(String[] args) {
        App.main("com.likanug.dual.App");
    }

    @Override
    public void settings() {
        size(INTERNAL_CANVAS_SIDE_LENGTH, INTERNAL_CANVAS_SIDE_LENGTH);
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
        textFont(smallFont, 96);
        // set text, rect and ellipse draw mode are center
        textAlign(CENTER, CENTER);
        rectMode(CENTER);
        ellipseMode(CENTER);
        currentKeyInput = new KeyInput();
        newGame(true, true);
    }

    public void newGame(boolean demo, boolean instruction) {
        system = new GameSystem(demo, instruction, this);
    }
    @Override
    public void draw() {
        background(255);
        system.run();
    }

    @Override
    public void mousePressed() {
        system.setShowsInstructionWindow(!system.isShowsInstructionWindow());
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
    }

    @Override
    public void keyPressed() {
        if (key != CODED) {
            if (key == 'z' || key == 'Z') {
                currentKeyInput.isZPressed = true;
                return;
            }
            if (key == 'x' || key == 'X') {
                currentKeyInput.isXPressed = true;
                return;
            }
            if (key == 'p') {
                if (paused) loop();
                else noLoop();
                paused = !paused;
            }
            return;
        }
        switch (keyCode) {
            case UP -> currentKeyInput.isUpPressed = true;
            case DOWN -> currentKeyInput.isDownPressed = true;
            case LEFT -> currentKeyInput.isLeftPressed = true;
            case RIGHT -> currentKeyInput.isRightPressed = true;
        }
    }

    @Override
    public void keyReleased() {
        if (key != CODED) {
            if (key == 'z' || key == 'Z') {
                currentKeyInput.isZPressed = false;
                return;
            }
            if (key == 'x' || key == 'X') {
                currentKeyInput.isXPressed = false;
                return;
            }
            return;
        }
        switch (keyCode) {
            case UP -> currentKeyInput.isUpPressed = false;
            case DOWN -> currentKeyInput.isDownPressed = false;
            case LEFT -> currentKeyInput.isLeftPressed = false;
            case RIGHT -> currentKeyInput.isRightPressed = false;
        }
    }


    public KeyInput getCurrentKeyInput() {
        return currentKeyInput;
    }

    public void setCurrentKeyInput(KeyInput currentKeyInput) {
        this.currentKeyInput = currentKeyInput;
    }

    public GameSystem getSystem() {
        return system;
    }

    public void setSystem(GameSystem system) {
        this.system = system;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}