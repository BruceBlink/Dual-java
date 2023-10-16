package com.likanug.dual.pool;

public interface Poolable<T extends Poolable<T>> {
    boolean isAllocated();

    void setAllocated(boolean indicator);

    ObjectPool<T> getBelongingPool();

    void setBelongingPool(ObjectPool<T> pool);

    int getAllocationIdentifier();  // -1 : not allocated

    void setAllocationIdentifier(int id);

    void initialize();
}
