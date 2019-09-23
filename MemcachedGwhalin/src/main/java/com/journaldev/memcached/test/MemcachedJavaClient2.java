/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.memcached.test;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 *
 * @author yoha
 */
public class MemcachedJavaClient2 {
    
        public static void main(String[] args) {
 
		log("===================================== Approach-1: Using SpyMemcahed: https://crunchify.com/memcached-java-client-net-spy-spymemcached/ ===================================== \n");
		log("===================================== Approach-2: Using com.whalin.Memcached-Java-Client =====================================\n");
		implementWhalinMemcachedJavaClient();
 
        }
 
	// Approach-1: Using SpyMemcahed: https://crunchify.com/memcached-java-client-net-spy-spymemcached/
 
	// Approach-2: Using com.whalin.Memcached-Java-Client
	private static void implementWhalinMemcachedJavaClient() {
		String[] servers = { "localhost:11211" };
 
		// This class is a connection pool for maintaning a pool of persistent connections to memcached servers.
		// The pool must be initialized prior to use. This should typically be early on in the lifecycle of the JVM instance.
		SockIOPool crunchfiyPool = SockIOPool.getInstance("Crunchify");
 
		// Sets the minimum number of spare connections to maintain in our available pool.
		crunchfiyPool.setMinConn(2);
 
		// Sets the maximum number of spare connections allowed in our available pool.
		crunchfiyPool.setMaxConn(20);
 
		// Sets the list of all cache servers.
		crunchfiyPool.setServers(servers);
 
		// Sets the failover flag for the pool. If this flag is set to true, and a socket fails to connect, the pool will attempt to return a socket from
		// another server if one exists.
		// If set to false, then getting a socket will return null if it fails to connect to the requested server.
		crunchfiyPool.setFailover(true);
 
		// Sets the initial number of connections per server in the available pool.
		crunchfiyPool.setInitConn(30);
 
		// Set the sleep time between runs of the pool maintenance thread. If set to 0, then the maint thread will not be started.
		crunchfiyPool.setMaintSleep(90);
 
		// Sets the socket timeout for reads.
		crunchfiyPool.setSocketTO(2000);
 
		// Sets the aliveCheck flag for the pool. When true, this will attempt to talk to the server on every connection checkout to make sure the connection is
		// still valid.
		crunchfiyPool.setAliveCheck(true);
 
		crunchfiyPool.initialize();
 
		// Creates a new instance of MemCachedClient accepting a passed in pool name.
		MemCachedClient crunchifyWhalinClient = new MemCachedClient("Crunchify");
 
		// Adds data to the server; only the key and the value are specified.
		crunchifyWhalinClient.add("Java", "Crunchify.com");
		crunchifyWhalinClient.add("WordPress", "WordPress.com");
		crunchifyWhalinClient.add("Social", "Facebook.com");
 
		log("==> Total 3 Records added to MemCached using com.whalin.Memcached-Java-Client Method\n");
 
		// Retrieve a key from the server, using a specific hash. 
		// If the data was compressed or serialized when compressed, it will automatically be decompressed or serialized, as appropriate..
		log("Key: Java, Value: " + crunchifyWhalinClient.get("Java"));
		log("Key: WordPress, Value: " + crunchifyWhalinClient.get("WordPress"));
		log("Key: Social, Value: " + crunchifyWhalinClient.get("Social"));
 
		log("==> Total 3 Records Retrieved from MemCached using com.whalin.Memcached-Java-Client Method\n");
 
		// Deletes an object from cache given cache key.
		crunchifyWhalinClient.delete("Social");
		log("==> Record deleted using com.whalin.Memcached-Java-Client Method\n");
 
		log("Key: Social, Value: " + crunchifyWhalinClient.get("Social"));
		log("==> Failure to get record Social as we deleted that before\n");
 
	}
 
	// Simple log utility
	private static void log(Object object) {
		System.out.println(object);
 
	}
}
