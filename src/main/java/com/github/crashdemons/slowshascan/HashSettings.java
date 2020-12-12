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
public class HashSettings {
    public final String hashAlgo; 
    public final String targetHash;
    public final long hashRecordingStepCount;
    public final long reportSeconds;
    public final long hashThreadCount;
    public HashSettings(String hashAlgo, String targetHash, long hashRecordingStepCount, long reportSeconds, long hashThreadCount){
        this.hashAlgo = hashAlgo;
        this.targetHash = targetHash;
        this.hashRecordingStepCount = hashRecordingStepCount;
        this.reportSeconds = reportSeconds;
        this.hashThreadCount=hashThreadCount;
    }
}
