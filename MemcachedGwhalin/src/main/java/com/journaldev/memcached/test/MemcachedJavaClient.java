/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.memcached.test;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import java.util.HashMap;

/**
 *
 * @author yoha
 */
public class MemcachedJavaClient {
    
    /**
	 * MemcachedJavaClient program to show the usage of different functions
	 * that can be performed on Memcached server with Java Client
	 * @param args
	 */
	public static void main(String[] args) {
		//initialize the SockIOPool that maintains the Memcached Server Connection Pool
		String[] servers = {"localhost:11211","localhost:11212"};
		SockIOPool pool = SockIOPool.getInstance("Test1");
		pool.setServers( servers );
		pool.setFailover( true );
		pool.setInitConn( 10 );
		pool.setMinConn( 5 );
		pool.setMaxConn( 250 );
		pool.setMaintSleep( 30 );
		pool.setNagle( false );
		pool.setSocketTO( 3000 );
		pool.setAliveCheck( true );
		pool.initialize();
		//Get the Memcached Client from SockIOPool named Test1
		MemCachedClient mcc = new MemCachedClient("Test1");
		//add some value in cache
		System.out.println("add status: "+mcc.add("1", "Original"));
		//Get value from cache
		System.out.println("Get from Cache: "+mcc.get("1"));

		System.out.println("add status: "+mcc.add("1", "Modified"));
		System.out.println("Get from Cache: "+mcc.get("1"));

		//use set function to add/update value, use replace to update and not add
		System.out.println("set status: "+mcc.set("1","Modified"));
		System.out.println("Get from Cache after set: "+mcc.get("1"));

		//use delete function to delete key from cache
		System.out.println("remove status: "+mcc.delete("1"));
		System.out.println("Get from Cache after delete: "+mcc.get("1"));

		//Use getMulti function to retrieve multiple keys values in one function
		// Its helpful in reducing network calls to 1
		mcc.set("2", "2");
		mcc.set("3", "3");
		mcc.set("4", "4");
		mcc.set("5", "5");
		String [] keys = {"1", "2","3","INVALID","5"};
		HashMap<String,Object> hm = (HashMap<String, Object>) mcc.getMulti(keys);

		for(String key : hm.keySet()){
			System.out.println("KEY: "+key+" VALUE: "+hm.get(key));
		}
                
                PruebaDto dto=new PruebaDto();
                dto.setApellido("Quevedo");
                dto.setNombre("Ivan");
                dto.setIdentificacion("80726072");
                
                System.out.println("set dto: "+mcc.set("dto",dto));
                PruebaDto dtoRecuperado=(PruebaDto)mcc.get("dto");
		System.out.println("Recuperado:"+dtoRecuperado.getNombre()+" "+dtoRecuperado.getApellido()+" "+dtoRecuperado.getIdentificacion());
	        
                //pool.shutDown();
        }
}