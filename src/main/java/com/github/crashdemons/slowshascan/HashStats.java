/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.slowshascan;

import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class HashStats {
    private HashStats(){}
    
    public static final Object statsLock = new Object();
    private static long totalHashCount = 0;
    public static long maxHashes = 0;
    
    public static final AtomicInteger threadsStarted = new AtomicInteger(0);
    public static final AtomicInteger threadsFinished = new AtomicInteger(0);
    public static boolean allThreadsCompleted = false;
    
    public static Map.Entry getStatsSnapshot(){
        synchronized(statsLock){
            return new AbstractMap.SimpleEntry<Long, Long>(System.currentTimeMillis(), totalHashCount);
        }
    }
    
    public static void recordIncrease(long threadCurrentHashes){
        synchronized(statsLock){
            totalHashCount+=threadCurrentHashes;
        }
    }
    public static void setMaxHashes(long hashes){
        synchronized(statsLock){
            maxHashes = hashes;
        }
    }
    
    public static void recordThreadStarted(){
        synchronized(statsLock){
            threadsStarted.incrementAndGet();
        }
    }
    public static void recordThreadCompleted(){
        synchronized(statsLock){
            threadsFinished.incrementAndGet();
            if(threadsFinished.get() == threadsStarted.get()) allThreadsCompleted = true;
        }
    }
    
    public static void markThreadsCompleted(){
        synchronized(statsLock){
            allThreadsCompleted = true;
        }
    }
    
    
    
    public static synchronized void println(String s){
        System.out.println(s);
    }
}
