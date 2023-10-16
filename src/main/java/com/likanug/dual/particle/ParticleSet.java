package com.likanug.dual.particle;

import com.likanug.dual.App;
import com.likanug.dual.pool.ObjectPool;

import java.util.ArrayList;

public class ParticleSet {

    public final ArrayList<Particle> particleList;
    public final ArrayList<Particle> removingParticleList;
    public final ObjectPool<Particle> particlePool;
    public final ParticleBuilder builder;


    public ParticleSet(int capacity, App app) {
        particlePool = new ObjectPool<>(capacity);
        for (int i = 0; i < capacity; i++) {
            particlePool.pool.add(new Particle(app));
        }

        particleList = new ArrayList<>(capacity);
        removingParticleList = new ArrayList<>(capacity);
        builder = new ParticleBuilder(app);
    }

    public void update() {
        particlePool.update();

        for (Particle eachParticle : particleList) {
            eachParticle.update();
        }

        if (!removingParticleList.isEmpty()) {
            for (Particle eachInstance : removingParticleList) {
                particlePool.deallocate(eachInstance);
            }
            particleList.removeAll(removingParticleList);
            removingParticleList.clear();
        }
    }

    public void display() {
        for (Particle eachParticle : particleList) {
            eachParticle.display();
        }
    }

    Particle allocate() {
        return particlePool.allocate();
    }

}
