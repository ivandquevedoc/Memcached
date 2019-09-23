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
public class MemcachedJavaAdd {
    
    public static void main(String[] args)throws Exception{
      
      // Connecting to Memcached server on localhost
      MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
      System.out.println("Connection to server successful");
      System.out.println("add status:"+mcc.add("tutorialspoint", 900, "redis").get());
      System.out.println("add status:"+mcc.add("tp", 900, "redis").get());
      
      // Get value from cache
      System.out.println("Get from Cache tp:"+mcc.get("tp"));
      
      mcc.shutdown();
   }
}
