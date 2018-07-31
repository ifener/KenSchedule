package com.wey.schedule.thread.forkjoinpool;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<BigInteger> {
    
    // http://www.importnew.com/29212.html
    private int start = 1;
    private int n;
    private static final int THRESHOLD = 20;
    
    @Override
    protected BigInteger compute() {
        // if ((n - start) >= THRESHOLD) {
        // return ForkJoinTask.invokeAll(createSubtasks()).stream().map(ForkJoinTask::join)
        // .reduce(BigInteger.ONE, BigInteger::multiply);
        // }
        // else {
        // return calculate(start, n);
        // }
        return null;
    }
    
    private Collection<FactorialTask> createSubtasks() {
        List<FactorialTask> dividedTasks = new ArrayList<FactorialTask>();
        int mid = (start + n) / 2;
        // dividedTasks.add(new FactorialTask(start, mid));
        // dividedTasks.add(new FactorialTask(mid + 1, n));
        return dividedTasks;
    }
    
    // private BigInteger calculate(int start, int n) {
    // return IntStream.rangeClosed(start, n).mapToObj(BigInteger::valueOf).reduce(BigInteger.ONE,
    // BigInteger::multiply);
    // }
    
    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        // BigInteger result = pool.invoke(new FactorialTask(100));
    }
    
}
