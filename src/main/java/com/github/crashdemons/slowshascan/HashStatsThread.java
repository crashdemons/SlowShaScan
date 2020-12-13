/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.slowshascan;

import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class HashStatsThread implements Runnable{
    ScheduledExecutorService executor = null;
    ScheduledFuture task = null;
    private static Map.Entry<Long,Long> lastSnapshot = new AbstractMap.SimpleEntry<Long, Long>(0L, 0L);

    
    @Override
    public void run(){
        if(HashStats.allThreadsCompleted){
            System.out.println("cancelling HashStatsThread");
            //task.cancel(true);
            executor.shutdown();
            return;
        }
        Map.Entry<Long,Long> newSnapshot = HashStats.getStatsSnapshot();
        long diffTimeMilis = newSnapshot.getKey() - lastSnapshot.getKey();
        long diffHashCount = newSnapshot.getValue() - lastSnapshot.getValue();
        lastSnapshot = newSnapshot;
        
        double progress = (lastSnapshot.getValue()/((double)HashStats.maxHashes))*100.00;
        double hashesMega = lastSnapshot.getValue() / 1000000.0;
        
        
       double diffTimeSecs=diffTimeMilis/1000L;
       double hashesPerSecond = diffHashCount / diffTimeSecs;
       
       HashStats.println("Hashes: "+hashesMega+"M - Rate: "+hashesPerSecond+" H/s - Progress: "+progress+"%");
        
    }
    
    public void schedule(long seconds){
        executor = Executors.newSingleThreadScheduledExecutor();
        task = executor.scheduleAtFixedRate(this, 0, seconds, TimeUnit.SECONDS);
    }
}
