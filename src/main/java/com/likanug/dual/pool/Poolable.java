package com.likanug.dual.pool;

public interface Poolable {
    boolean isAllocated();

    void setAllocated(boolean indicator);

    ObjectPool getBelongingPool();

    void setBelongingPool(ObjectPool pool);

    int getAllocationIdentifier();  // -1 : not allocated

    void setAllocationIdentifier(int id);

    void initialize();
}
