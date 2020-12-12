/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.slowshascan;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class IPUtil {
    private IPUtil(){}
    public static byte[] toIPByteArray(long addr){
            return new byte[]{(byte)addr,(byte)(addr>>>8),(byte)(addr>>>16),(byte)(addr>>>24)};
    }

    public static InetAddress toInetAddress(long addr){
        try {
            return InetAddress.getByAddress(toIPByteArray(addr));
        } catch (UnknownHostException e) {
            //should never happen
            return null;
        }
    }
    
    
}
