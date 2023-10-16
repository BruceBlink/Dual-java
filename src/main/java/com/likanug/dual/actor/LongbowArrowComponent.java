package com.likanug.dual.actor;

import com.likanug.dual.App;
import com.likanug.dual.particle.Particle;

import static processing.core.PConstants.HALF_PI;
import static processing.core.PConstants.PI;

public abstract class LongbowArrowComponent extends AbstractArrowActor {

    public LongbowArrowComponent(App app) {
        super(16, 16, app);
    }

    public void act() {
        final float particleDirectionAngle = this.directionAngle + PI + app.random(-HALF_PI, HALF_PI);
        for (int i = 0; i < 5; i++) {
            final float particleSpeed = app.random(2, 4);
            final Particle newParticle = app.system.commonParticleSet.builder
                    .type(1)  // Square
                    .position(this.xPosition, this.yPosition)
                    .polarVelocity(particleDirectionAngle, particleSpeed)
                    .particleSize(4)
                    .particleColor(64)
                    .lifespanSecond(1)
                    .build();
            app.system.commonParticleSet.particleList.add(newParticle);
        }
    }

    public boolean isLethal() {
        return true;
    }

}
