/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xmemcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 *
 * @author yoha
 */
public class MemcachedXClient {
    
    public static void main(String[] args) {
                log("===================================== Approach-3: Using com.googlecode.xmemcached Method =====================================\n");
		implementXMemCachedClient();
		log("===================================== Program Completed ===================================== \n");
 
	}
	
	// Approach-3: Using com.googlecode.xmemcached
	private static void implementXMemCachedClient() {
            
		String city = "New York";
		String city2 = "San Francisco";
                
		try {
 
			// XMemcached constructor,default weight is 1
			XMemcachedClient xMemCachedclient = new XMemcachedClient("localhost", 11211);
 
			// Set a value
			xMemCachedclient.set("Crunchify", 3600, city);
			xMemCachedclient.set("Twitter", 3600, city2);
 
			// Get a value for specify key
			Object myCity = xMemCachedclient.get("Twitter");
			log("=====> Key: Twitter, Value: " + xMemCachedclient.get("Twitter") + "\n");
 
			// Get a value for specify key (set timeout of three seconds)
			myCity = xMemCachedclient.get("Crunchify", 3000);
			log("=====> Key: Crunchify, Value: " + xMemCachedclient.get("Crunchify", 3000) + "\n");
 
			// Set a new expiration time for an existing item,using default opTimeout second.
			//xMemCachedclient.touch("key", 30);
 
			xMemCachedclient.delete("Crunchify");
			log("=====> Key: Crunchify, Value: " + xMemCachedclient.get("Crunchify") + "\n");
 
			// delete value
			xMemCachedclient.delete("key");
 
                        PruebaDto dto=new PruebaDto();
                        dto.setApellido("Quevedo");
                        dto.setNombre("Ivan");
                        dto.setIdentificacion("80726072");
                
                        System.out.println("set dto: "+xMemCachedclient.set("dto",3600,dto));
                        PruebaDto dtoRecuperado=(PruebaDto)xMemCachedclient.get("dto");
		        System.out.println("Recuperado:"+dtoRecuperado.getNombre()+" "+dtoRecuperado.getApellido()+" "+dtoRecuperado.getIdentificacion());
	        
			// let's catch all exceptions
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
	// Simple log utility
	private static void log(Object object) {
		System.out.println(object);
 
	}
}
