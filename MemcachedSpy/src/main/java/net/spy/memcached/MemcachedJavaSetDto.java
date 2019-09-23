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
public class MemcachedJavaSetDto {
    
    public static void main(String[] args)throws Exception{
      
      // Connecting to Memcached server on localhost
      MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
      
      PruebaDto dto=new PruebaDto();
      dto.setApellido("Quevedo");
      dto.setNombre("Ivan");
      dto.setIdentificacion("80726072");
      
      System.out.println("Connection to server sucessfully");
      System.out.println("set status:"+mcc.set("dto", 900, dto).get());
      
      // Get value from cache
      PruebaDto dtoRecuperado=(PruebaDto)mcc.get("dto");
      System.out.println("Recuperado:"+dtoRecuperado.getNombre()+" "+dtoRecuperado.getApellido()+" "+dtoRecuperado.getIdentificacion());
   
      mcc.shutdown();
    }
}
