package com.sky.hiwise.design.lesson.network.nio;

public class CommonUtil {

    /**
     * byte数组拼接
     */
    public static byte[] concat(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

}
