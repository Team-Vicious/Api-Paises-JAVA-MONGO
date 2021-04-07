package com.example.demo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@SpringBootApplication
public class JavaMongoApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(JavaMongoApplication.class, args);
	
		// Crea un cliente mongo 
	      MongoClient mongo = new MongoClient("localhost" ,27017 ); 
	   
	      // Crea credenciales
	      MongoCredential credential; 
	      credential = MongoCredential.createCredential("user", "paises_db", 
	         "password".toCharArray()); 
	      System.out.println("Conexion con la base de datos exitosa");  
	      
	      // Accede a la base de datos 
	      MongoDatabase database = mongo.getDatabase("paises_db"); 
	      System.out.println("Credenciales ::"+ credential);  
	      
	      JSONParser parser = new JSONParser();
	      
	      String url;
			
	  	  String hola = "";
	  	  
	  	MongoCollection<Document> collection = database.getCollection("paises");
	  	System.out.println("Coleccion paises obtenida correctamente");
	      
	     
	      //recorre las 300 direcciones
	      for(int i= 1; i<301; i++) {
	  		
	  		url = "https://restcountries.eu/rest/v2/callingcode/"+i;
	  		hola = peticionHttpGet(url);
	  		
	  		if(hola != null) {
	  		
	  		//pasamos a json 
	  		JSONArray jsonArray = (JSONArray) parser.parse(hola);
		  	JSONObject json = (JSONObject) jsonArray.get(0);
		    JSONArray jsonArray2 = (JSONArray) json.get("callingCodes");
		    JSONArray jsonArray3 = (JSONArray) json.get("latlng");

	  		
	  		Document document = new Document("nombrePais", json.get("name"))
	  		.append("capitalPais", json.get("capital"))
	  		.append("region", json.get("region"))
	  		.append("poblacion", json.get("population"))
	  		.append("latitud", jsonArray3.get(0))
	  		.append("longitud", jsonArray3.get(1))
	  		.append("codigoPais", jsonArray2.get(0));
	  		
	  		//Insertar documento en la colleccion
	  		collection.insertOne(document);
	  		System.out.println("Pais número "+i+" Agregado");      
	  		
	  		}
	  		}
	      
	      System.out.println("300 Calling code recorridos");
	  	
	  	
	  	collection.find(eq("nombrePais"));
	  	
	  	
	  	
	      
    }
	
	//metodo para visitar una url
	public static String peticionHttpGet(String urlParaVisitar) {
		
		StringBuilder resultado = new StringBuilder();
		try {
		URL url = new URL(urlParaVisitar);

		
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;
		
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}

		rd.close();
		
		return resultado.toString();
		}
		catch(Exception e){	
			
		return null;
		
		}
		
		}
}
