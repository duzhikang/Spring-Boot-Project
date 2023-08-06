package com.rudy.SpringBootProject.JUC;

import com.rudy.SpringBootProject.SpringBootProjectApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReentrantLockCounterTest extends SpringBootProjectApplicationTests {

    @Test
    public void testCounter() throws InterruptedException, ExecutionException {
        ReentrantLockCounter counter = new ReentrantLockCounter();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Future<?> future = executorService.submit(counter::increment);
            futures.add(future);
        }

        for (Future<?> future : futures) {
            future.get();  // wait for task to complete
        }

        executorService.shutdown();
        Assert.isTrue(counter.getCount() == 1000, "Count should be 1000");
    }
}
