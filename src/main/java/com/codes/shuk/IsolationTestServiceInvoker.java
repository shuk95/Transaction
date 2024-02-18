package com.codes.shuk;

import com.codes.shuk.service.IsolationTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class IsolationTestServiceInvoker {

    @Autowired
    private IsolationTestService isolationTestService;
    public void execute() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()->{
            isolationTestService.readOperations();
            isolationTestService.getReadWriteLatch().countDown();
        });

        executorService.execute(()->{
            isolationTestService.writeOperations();
            isolationTestService.getFourthLatch().countDown();
            isolationTestService.getReadWriteLatch().countDown();
        });
        isolationTestService.getReadWriteLatch().await(10, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
