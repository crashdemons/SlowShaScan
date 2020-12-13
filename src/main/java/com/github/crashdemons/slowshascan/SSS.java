/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.slowshascan;

import java.net.InetAddress;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class SSS {
    public static final String VALIDATION_HASH = "7ac2a0346e5f13a070e78167e3b96c7ddd121289dfe8276a9112c7db7f3882052575cb83b001a07e66d29575ddff0b70fbc07fd3f64bc44c5101dfe7eb6352e6".toUpperCase();//1.2.3.4
    
    
    public static void main(String[] args){
        if(args.length<3){
            System.out.println("sss <threads> <start> <bound>");
            return;
        }
        long threads = Long.valueOf(args[0]);
        long ipstart = Long.valueOf(args[1]);
        long ipbound = Long.valueOf(args[2]);
        
        System.out.println("SlowShaScan "+threads+" threads IP range ["+ipstart+","+ipbound+")");
        
        HashSettings settings = new HashSettings("SHA-512", VALIDATION_HASH, 10000L, 5*60, threads);
        
        HashThreadManager threadManager = new HashThreadManager(settings);
        
        threadManager.startThreads(ipstart,ipbound);
        
        System.out.println("end main");
        
        
        
    }
}
