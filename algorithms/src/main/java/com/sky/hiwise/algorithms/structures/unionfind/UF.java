package com.sky.hiwise.algorithms.structures.unionfind;

public interface UF {

    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
