/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.slowshascan;


/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class HashThreadManager {
    private HashSettings settings;
    public HashThreadManager(HashSettings settings){
        this.settings = settings;
    }
    
    public void startThreads(long ipStart, long ipBound){
        //long ipStart = 0L;
        //long ipBound = 4294967296L;
        long ipRange = ipBound - ipStart;
        HashStats.setMaxHashes(ipRange);
        long threadStep = ipRange / settings.hashThreadCount;
        
        HashThread[] threads = new HashThread[(int)(settings.hashThreadCount)];
        
        long threadStart = ipStart;
        int iThread = 0;
        while(threadStart < ipBound){
            long threadEnd = threadStart+threadStep;
            threads[iThread] = new HashThread(settings,threadStart,threadEnd);
            iThread++;
            threadStart = threadEnd;
        }
        
        for(HashThread thread : threads){
            thread.start();
        }
        HashStatsThread statsThread = new HashStatsThread();
        statsThread.schedule(settings.reportSeconds);
    }
}
