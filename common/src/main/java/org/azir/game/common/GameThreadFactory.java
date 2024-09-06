package org.azir.game.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 斗地主线程工厂
 *
 * @author zhangshukun
 * @since 2024/09/06
 */
public class GameThreadFactory implements ThreadFactory {

    private static final AtomicInteger THREAD_NUM = new AtomicInteger(0);

    private final String namePrefix;

    public GameThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, namePrefix + "-" + THREAD_NUM.getAndIncrement());
    }
}
