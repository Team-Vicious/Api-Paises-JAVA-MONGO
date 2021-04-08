package com.example.demo;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MetodosMongo {
	
	
	public static void PaisesPorRegionLimite10(String region) {
		// Crea un cliente mongo 
	      MongoClient mongo = new MongoClient("localhost" ,27017 ); 
	   
	      // Accede a la base de datos 
	      MongoDatabase database = mongo.getDatabase("paises_db"); 
	           
	      MongoCollection<Document> paises = database.getCollection("paises");
		  System.out.println("Coleccion paises obtenida correctamente");
		  	
		  FindIterable<Document> docs;
		  	
		  docs =paises.find(Filters.eq("region", region)).limit(10);
		  	
		  System.out.println("\nPaises por region:"+region);
		  for(Document doc : docs){
             System.out.println(doc);
         }
		  	  	
         //cierro la conexion
         mongo.close();
     
	}
	

	public static void PaisesPorRegionPoblacion(String region) {
		// Crea un cliente mongo 
	      MongoClient mongo = new MongoClient("localhost" ,27017 ); 
	   
	      // Accede a la base de datos 
	      MongoDatabase database = mongo.getDatabase("paises_db"); 
	           
	      MongoCollection<Document> paises = database.getCollection("paises");
		  System.out.println("Coleccion paises obtenida correctamente");
		  	
		  FindIterable<Document> docs;
		  	
		  docs = paises.find(Filters.and(Filters.eq("region", region),Filters.gt("poblacion", 100000000)));
		  	
		  System.out.println("\nPaises por poblacion mayor a 100000000: ");
		  for(Document doc : docs){
             System.out.println(doc);
         }
		  	  	
         //cierro la conexion
         mongo.close();
     
	}

	
	public static void PaisesDistintoAfrica(String region) {
		// Crea un cliente mongo 
	      MongoClient mongo = new MongoClient("localhost" ,27017 ); 
	   
	      // Accede a la base de datos 
	      MongoDatabase database = mongo.getDatabase("paises_db"); 
	           
	      MongoCollection<Document> paises = database.getCollection("paises");
		  System.out.println("Coleccion paises obtenida correctamente");
		  	
		  FindIterable<Document> docs;
		  	
		  docs = paises.find(Filters.ne("region", region));
		  	
		  System.out.println("\nPaises distintos a África: ");
		  for(Document doc : docs){
             System.out.println(doc);
         }
		  	  	
         //cierro la conexion
         mongo.close();
     
	}
	
	public static void PaisesMenorMayor() {
		// Crea un cliente mongo 
	      MongoClient mongo = new MongoClient("localhost" ,27017 ); 
	   
	      // Accede a la base de datos 
	      MongoDatabase database = mongo.getDatabase("paises_db"); 
	           
	      MongoCollection<Document> paises = database.getCollection("paises");
		  System.out.println("Coleccion paises obtenida correctamente");
		  	
		  FindIterable<Document> docs;
		  	
		  docs = paises.find(Filters.and(Filters.lt("poblacion", 150000000),Filters.gt("poblacion", 50000000)));
		  	
		  System.out.println("\nPaises con poblacion mayor a 50000000 y menor a 150000000: ");
		  for(Document doc : docs){
             System.out.println(doc);
         }
		  	  	
         //cierro la conexion
         mongo.close();
     
	}
	
	
	public static void PaisesFormaAsc() {
		// Crea un cliente mongo 
	      MongoClient mongo = new MongoClient("localhost" ,27017 ); 
	   
	      // Accede a la base de datos 
	      MongoDatabase database = mongo.getDatabase("paises_db"); 
	           
	      MongoCollection<Document> paises = database.getCollection("paises");
		  System.out.println("Coleccion paises obtenida correctamente");
		  	
		  FindIterable<Document> docs;
		  	
		  docs = paises.find().sort(new BasicDBObject("nombrePais", 1));
		  	
		  System.out.println("\nPaises en forma ascendiente ");
		  for(Document doc : docs){
             System.out.println(doc);
         }
		  	  	
         //cierro la conexion
         mongo.close();
     
	}
}
