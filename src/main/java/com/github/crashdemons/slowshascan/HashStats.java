/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.slowshascan;

import java.util.AbstractMap;
import java.util.Map;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class HashStats {
    private HashStats(){}
    
    public static final Object statsLock = new Object();
    private static long totalHashCount = 0;
    public static long maxHashes = 0;
    public static boolean foundMatch = false;
    
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
    public static void recordMatch(){
        synchronized(statsLock){
            foundMatch = true;
        }
    }
    
    
    
    public static synchronized void println(String s){
        System.out.println(s);
    }
}
