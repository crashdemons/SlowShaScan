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
public class ByteUtil {
    
    private ByteUtil(){}
    public static byte[] str2bytes(String string){
        try{
            return string.getBytes("ISO-8859-1");
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static String bytes2hex(byte[] bytes){
        String out = "";
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
