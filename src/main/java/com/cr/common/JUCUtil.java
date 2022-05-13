package com.cr.common;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JUCUtil {

    public static void barrierAwait(CyclicBarrier barrier){
        try {
            barrier.await();
        } catch (InterruptedException e) {
            Facility.print(e);
        } catch (BrokenBarrierException e) {
            Facility.print(e);
        }
    }

    public static void barrierAwait(CyclicBarrier barrier, long timeout){
        try {
            barrier.await(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Facility.print(e);
        } catch (BrokenBarrierException e) {
            Facility.print(e);
        } catch (TimeoutException e) {
            Facility.print(e);
        }
    }

}
