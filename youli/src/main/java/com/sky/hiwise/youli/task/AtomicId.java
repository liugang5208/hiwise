package com.sky.hiwise.youli.task;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicId {

    private static AtomicInteger start = new AtomicInteger(9213467);

    public static Integer getId() {
        return start.decrementAndGet();
    }

    public static final Integer MIN_ID = 9210000;//7713466;

    public static final String URL_PRIFIX = "https://dp.nifa.org.cn/HomePage?method=getTargetProjectInfo&sorganation=911101085977302834&sdebtortypeb=01&sfullnames=911101085977302834&stheonlyid=911101085977302834";

}
