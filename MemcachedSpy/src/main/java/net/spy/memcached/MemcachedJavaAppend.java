/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.spy.memcached;

import java.net.InetSocketAddress;

/**
 *
 * @author yoha
 */
public class MemcachedJavaAppend {
    
    public static void main(String[] args)throws Exception{
      
      // Connecting to Memcached server on localhost
      MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
      
      System.out.println("Connection to server successful");
      System.out.println("set status:"+mcc.set("tutorialspoint", 900, "memcached").get());
      
      // Get value from cache
      System.out.println("Get from Cache:"+mcc.get("tutorialspoint"));
      
      // now append some data into existing key
      System.out.println("Append to cache:"+mcc.append("tutorialspoint", ",redis").get());
      
      // get the updated key
      System.out.println("Get from Cache:"+mcc.get("tutorialspoint"));
      
      mcc.shutdown();
   }
}
