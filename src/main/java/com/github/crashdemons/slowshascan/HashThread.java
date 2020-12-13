/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.slowshascan;

import java.security.MessageDigest;
import java.util.AbstractMap;
import java.util.Map;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class HashThread implements Runnable{
    private Thread t;
    private final String threadName;
    
    private final long start,end;
    
    private final HashSettings settings;
    private final MessageDigest digest;

    


    
    public HashThread(HashSettings settings, long start, long end){
        this.settings = settings;
        threadName = "HashThread "+start+"-"+end;
        this.start=start;
        this.end=end;
        
        MessageDigest digestTemp = null;
        try{
            digestTemp = MessageDigest.getInstance(settings.hashAlgo);
        }catch(Exception e){
            digestTemp = null;
            e.printStackTrace();
        }
        
        digest = digestTemp;

        System.out.println("Prepared "+threadName+" "+System.identityHashCode(digest));
    }
    
    @Override
    public void run(){
        long hashes = 0;
        String ipv4 = "XXXXXXXXXXXXXXXXXXXXX";
        for(long i=start;i<end && !HashStats.allThreadsCompleted;i++){

            ipv4 = 
                    (((byte)(i>>>24))&0xff)+"."+
                    (((byte)(i>>>16))&0xff)+"."+
                    (((byte)(i>>>8))&0xff)+"."+
                    (((byte)i)&0xff);
            
           
           String hash =  ByteUtil.bytes2hex( digest.digest( ByteUtil.str2bytes(ipv4) ) );
            
            
            //String hash = HashUtil.hash(ipv4);
            
            
            /*if(ipv4.equals("127.0.0.1")){
                HashStats.recordMatch();
                HashStats.println("test-match "+ipv4);
            }*/
            
            if(settings.targetHash.equals(hash)){
                HashStats.markThreadsCompleted();
                HashStats.println("hash-match "+ipv4);
                break;
            }
            
            hashes++;
            if(hashes==settings.hashRecordingStepCount){
                HashStats.recordIncrease(hashes);
                hashes=0;
            }
            
            //System.out.println(addr.getHostAddress());

            //System.out.println(ipv4);
        }
        HashStats.recordThreadCompleted();
        System.out.println("Finished " +  threadName );
    }
    
    public void start () {
       HashStats.recordThreadStarted();
       System.out.println("Starting " +  threadName );
       if (t == null) {
          t = new Thread (this, threadName);
          t.start ();
       }
    }
    
}
