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
public class MemcachedJavaReplace {
    
    public static void main(String[] args)throws Exception{
      
      // Connecting to Memcached server on localhost
      MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
      
      System.out.println("Connection to server sucessfully");
      System.out.println("set status:"+mcc.set("tutorialspoint", 900, "memcached").get());
      
      // Get value from cache
      System.out.println("Get from Cache:"+mcc.get("tutorialspoint"));
      
      // now replace the existing data
      System.out.println("Replace cache:"+mcc.replace("tutorialspoint", 900, "redis").get());
      
      // get the updated data
      System.out.println("Get from Cache:"+mcc.get("tutorialspoint"));
      
      mcc.shutdown();
   }
}
