package com.codes.shuk.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.codes.shuk.Utils.CountDownLatchUtils.*;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class IsolationTestService extends AbstractIsolationTestService{

    public void readOperations() {
        System.out.println("Starting the read operation");
        System.out.println("Starting the first read operation before write");
        readOperation();
        countDown(firstLatch);
        await(secondLatch);
        System.out.println("Starting the second read operation after write but before committing");
        readOperation();
        countDown(thirdLatch);
        await(fourthLatch);
        System.out.println("Starting the third read operation after committing");
        readOperation();
    }
    public void writeOperations(){
        await(firstLatch);
        System.out.println("Starting the write operation");
        writeOperation();
        countDown(secondLatch);
        await(thirdLatch);
        System.out.println("Completing the read operation");
    }

}
