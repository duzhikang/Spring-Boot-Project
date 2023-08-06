package com.rudy.SpringBootProject.JUC;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter {

    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

}
