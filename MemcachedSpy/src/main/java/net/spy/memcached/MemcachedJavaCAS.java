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
public class MemcachedJavaCAS {
    
    public static void main(String[] args)throws Exception{
      
      // https://www.tutorialspoint.com/memcached/memcached_cas.htm  
        
      // Connecting to Memcached server on localhost
      MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
      
      System.out.println("Connection to server successful");
      System.out.println("set status:"+mcc.set("tutorialspoint", 900, "memcached").get());

      // Get cas token from cache
      long castToken = mcc.gets("tutorialspoint").getCas();
      System.out.println("Cas token:"+castToken);

      // now set new data in memcached server
      System.out.println("Now set new data:"+mcc.cas("tutorialspoint",castToken, 900, "redis"));
      System.out.println("Get from Cache:"+mcc.get("tutorialspoint"));
      
      mcc.shutdown();
   }
}
