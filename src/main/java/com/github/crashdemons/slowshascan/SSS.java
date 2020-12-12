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
    public static final String CICADA_DEEP_WEB_HASH = "36367763ab73783c7af284446c59466b4cd653239a311cb7116d4618dee09a8425893dc7500b464fdaf1672d7bef5e891c6e2274568926a49fb4f45132c2a8b4".toUpperCase();
    
    
    public static void main(String[] args){
        if(args.length<3){
            System.out.println("sss <threads> <start> <bound>");
            return;
        }
        long threads = Long.valueOf(args[0]);
        long ipstart = Long.valueOf(args[1]);
        long ipbound = Long.valueOf(args[2]);
        
        System.out.println("SlowShaScan "+threads+" threads IP range ["+ipstart+","+ipbound+")");
        
        HashSettings settings = new HashSettings("SHA-512", CICADA_DEEP_WEB_HASH, 10000L, 5*60, threads);
        
        HashThreadManager threadManager = new HashThreadManager(settings);
        
        threadManager.startThreads(ipstart,ipbound);
        
        System.out.println("end main");
        
        
        
    }
}
