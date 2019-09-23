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
public class MemcachedJavaFlush {
    
    public static void main(String[] args)throws Exception{
      
      // Connecting to Memcached server on localhost
      MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
      
      System.out.println("Connection to server sucessfully");
      System.out.println("set status:"+mcc.set("count", 900, "5").get());
      
      // Get value from cache
      System.out.println("Get from Cache:"+mcc.get("count"));
     
      // now increase the stored value
      System.out.println("Increment value:"+mcc.incr("count", 2));
     
      // now decrease the stored value
      System.out.println("Decrement value:"+mcc.decr("count", 1));
      
      // now get the final stored value
      System.out.println("Get from Cache:"+mcc.get("count"));
      
      // now clear all this data
      System.out.println("Clear data:"+mcc.flush().get());
      
      mcc.shutdown();
   }
}
