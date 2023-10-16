package com.likanug.dual.game;

import com.likanug.dual.App;
import com.likanug.dual.actor.ActorGroup;
import com.likanug.dual.actor.PlayerActor;
import com.likanug.dual.particle.Particle;
import com.likanug.dual.particle.ParticleBuilder;
import com.likanug.dual.particle.ParticleSet;
import com.likanug.dual.playerEngine.ComputerPlayerEngine;
import com.likanug.dual.playerEngine.HumanPlayerEngine;
import com.likanug.dual.playerEngine.PlayerEngine;
import com.likanug.dual.state.*;

import static com.likanug.dual.App.FPS;
import static com.likanug.dual.App.INTERNAL_CANVAS_SIDE_LENGTH;
import static processing.core.PConstants.*;

public class GameSystem {
    private final App app;
    public final ActorGroup myGroup;
    public final ActorGroup otherGroup;
    public final ParticleSet commonParticleSet;
    public GameSystemState currentState;
    public float screenShakeValue;
    public final DamagedPlayerActorState damagedState;
    public final GameBackground currentBackground;
    public final boolean demoPlay;
    public boolean showsInstructionWindow;

    public GameSystem(boolean demo, boolean instruction, App app) {
        this.app = app;
        // prepare ActorGroup
        this.myGroup = new ActorGroup();
        this.otherGroup = new ActorGroup();
        this.myGroup.setEnemyGroup(otherGroup);
        this.otherGroup.setEnemyGroup(myGroup);

        // prepare PlayerActorState
        final MovePlayerActorState moveState = new MovePlayerActorState(app);
        final DrawBowPlayerActorState drawShortbowState = new DrawShortbowPlayerActorState(app);
        final DrawBowPlayerActorState drawLongbowState = new DrawLongbowPlayerActorState(app);
        this.damagedState = new DamagedPlayerActorState(app);
        moveState.setDrawShortbowState(drawShortbowState);
        moveState.setDrawLongbowState(drawLongbowState);
        drawShortbowState.setMoveState(moveState);
        drawLongbowState.setMoveState(moveState);
        this.damagedState.setMoveState(moveState);

        // prepare PlayerActor
        PlayerEngine myEngine;
        if (demo) myEngine = new ComputerPlayerEngine(app);
        else myEngine = new HumanPlayerEngine(app.currentKeyInput);
        PlayerActor myPlayer = new PlayerActor(myEngine, 255, app);
        myPlayer.setxPosition(INTERNAL_CANVAS_SIDE_LENGTH * 0.5F);
        myPlayer.setyPosition(INTERNAL_CANVAS_SIDE_LENGTH - 100);
        myPlayer.setState(moveState);
        this.myGroup.setPlayer(myPlayer);
        PlayerEngine otherEngine = new ComputerPlayerEngine(app);
        PlayerActor otherPlayer = new PlayerActor(otherEngine, 0, app);
        otherPlayer.setxPosition((float) (INTERNAL_CANVAS_SIDE_LENGTH * 0.5));
        otherPlayer.setyPosition(100);
        otherPlayer.setState(moveState);
        this.otherGroup.setPlayer(otherPlayer);

        // other
        this.commonParticleSet = new ParticleSet(2048, app);
        this.currentState = new StartGameState(app);
        this.currentBackground = new GameBackground(224, 0.1F, app);
        this.demoPlay = demo;
        this.showsInstructionWindow = instruction;
    }

    GameSystem(App app) {
        this(false, false, app);
    }

    public void run() {
        if (demoPlay) {
            if (app.currentKeyInput.isZPressed) {
                app.system = new GameSystem(app);  // stop demo and start game
                return;
            }
        }

        app.pushMatrix();

        if (screenShakeValue > 0) {
            app.translate(app.random(-screenShakeValue, screenShakeValue), app.random(-screenShakeValue, screenShakeValue));
            screenShakeValue -= 50F / FPS;
        }
        currentBackground.update();
        currentBackground.display();
        currentState.run(this);

        app.popMatrix();
        if (demoPlay && showsInstructionWindow)
            displayDemo();
    }

    public void displayDemo() {
        app.pushStyle();

        app.stroke(0);
        app.strokeWeight(2);
        app.fill(255, 240);
        app.rect(
                INTERNAL_CANVAS_SIDE_LENGTH * 0.5F,
                INTERNAL_CANVAS_SIDE_LENGTH * 0.5F,
                INTERNAL_CANVAS_SIDE_LENGTH * 0.7F,
                INTERNAL_CANVAS_SIDE_LENGTH * 0.6F
        );

        app.textFont(App.smallFont, 20);
        app.textLeading(26);
        app.textAlign(RIGHT, BASELINE);
        app.fill(0);
        app.text("Z key:", 280, 180);
        app.text("X key:", 280, 250);
        app.text("Arrow key:", 280, 345);
        app.textAlign(LEFT);
        app.text("Weak shot\n (auto aiming)", 300, 180);
        app.text("Lethal shot\n (manual aiming,\n  requires charge)", 300, 250);
        app.text("Move\n (or aim lethal shot)", 300, 345);
        app.textAlign(CENTER);
        app.text("- Press Z key to start -", INTERNAL_CANVAS_SIDE_LENGTH * 0.5F, 430);
        app.text("(Click to hide this window)", INTERNAL_CANVAS_SIDE_LENGTH * 0.5F, 475);
        app.popStyle();

        app.strokeWeight(1);
    }

    public void addSquareParticles(float x, float y, int particleCount, int particleSize, float minSpeed, float maxSpeed, int lifespanSecondValue) {
        final ParticleBuilder builder = app.system.commonParticleSet.getBuilder()
                .type(1)  // Square
                .position(x, y)
                .particleSize(particleSize)
                .particleColor(app.color(0))
                .lifespanSecond(lifespanSecondValue);
        for (int i = 0; i < particleCount; i++) {
            final Particle newParticle = builder
                    .polarVelocity(app.random(TWO_PI), app.random(minSpeed, maxSpeed))
                    .build();
            app.system.commonParticleSet.getParticleList().add(newParticle);
        }
    }
}
