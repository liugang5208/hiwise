package com.sky.hiwise.study;

import org.pcollections.HashTreePMap;
import org.pcollections.HashTreePSet;
import org.pcollections.PMap;
import org.pcollections.PSet;

public class TestPCollections {

    public static void main(String[] args) {
        PMap<String, String> meterMap = HashTreePMap.empty();
        meterMap = meterMap.plus("hello", "world");
        System.out.println(meterMap);


        PSet<String> set = HashTreePSet.empty();
        set = set.plus("something");
        System.out.println(set.plus("hello"));
        System.out.println(set);
        System.out.println(set.plus("something else"));
        System.out.println(set);
    }
}
