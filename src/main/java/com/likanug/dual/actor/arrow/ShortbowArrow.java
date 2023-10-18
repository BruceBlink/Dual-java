package com.likanug.dual.actor.arrow;

import com.likanug.dual.App;
import com.likanug.dual.particle.Particle;
import processing.core.PApplet;

import static processing.core.PApplet.sin;
import static processing.core.PConstants.PI;
import static processing.core.PConstants.QUARTER_PI;

public class ShortbowArrow extends AbstractArrowActor {

    private final float terminalSpeed;

    private final float halfHeadLength = 8.0F;
    private final float halfHeadWidth = 4.0F;
    private final float halfFeatherWidth = 4.0F;
    private final float featherLength = 8.0F;

    public ShortbowArrow(App app) {
        super(8.0F, 20.0F, app);
        terminalSpeed = 8.0F;
    }

    public void update() {
        xVelocity = speed * PApplet.cos(directionAngle);
        yVelocity = speed * sin(directionAngle);
        super.update();

        speed += (float) ((terminalSpeed - speed) * 0.1);
    }

    public void act() {
        if (app.random(1) >= 0.5) return;

        final float particleDirectionAngle = this.directionAngle + PI + app.random(-QUARTER_PI, QUARTER_PI);
        for (int i = 0; i < 3; i++) {
            final float particleSpeed = app.random(0.5F, 2.0F);
            final Particle newParticle = app.system.getCommonParticleSet().getBuilder()
                    .type(1)  // Square
                    .position(this.xPosition, this.yPosition)
                    .polarVelocity(particleDirectionAngle, particleSpeed)
                    .particleSize(2)
                    .particleColor(app.color(192))
                    .lifespanSecond((int) 0.5)
                    .build();
            app.system.getCommonParticleSet().getParticleList().add(newParticle);
        }
    }

    public void display() {
        app.stroke(0);
        app.fill(0);
        app.pushMatrix();
        app.translate(xPosition, yPosition);
        app.rotate(rotationAngle);
        app.line(-halfLength, 0, halfLength, 0);
        app.quad(
                halfLength, 0,
                halfLength - halfHeadLength, -halfHeadWidth,
                halfLength + halfHeadLength, 0,
                halfLength - halfHeadLength, +halfHeadWidth
        );
        app.line(-halfLength, 0, -halfLength - featherLength, -halfFeatherWidth);
        app.line(-halfLength, 0, -halfLength - featherLength, +halfFeatherWidth);
        app.line(-halfLength + 4, 0, -halfLength - featherLength + 4, -halfFeatherWidth);
        app.line(-halfLength + 4, 0, -halfLength - featherLength + 4, +halfFeatherWidth);
        app.line(-halfLength + 8, 0, -halfLength - featherLength + 8, -halfFeatherWidth);
        app.line(-halfLength + 8, 0, -halfLength - featherLength + 8, +halfFeatherWidth);
        app.popMatrix();
    }

    public boolean isLethal() {
        return false;
    }

}
