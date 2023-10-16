package com.likanug.dual.particle;

import com.likanug.dual.pool.ObjectPool;

import java.util.ArrayList;

public class ParticleSet {

    public final ArrayList<Particle> particleList;
    public final ArrayList<Particle> removingParticleList;
    public final ObjectPool<Particle> particlePool;
    public final ParticleBuilder builder;

    public ParticleSet(int capacity) {
        particlePool = new ObjectPool<Particle>(capacity);
        for (int i = 0; i < capacity; i++) {
            particlePool.pool.add(new Particle());
        }

        particleList = new ArrayList<Particle>(capacity);
        removingParticleList = new ArrayList<Particle>(capacity);
        builder = new ParticleBuilder();
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
