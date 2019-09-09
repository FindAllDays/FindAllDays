package com.ift.slip.connect.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class HttpURLService {

	private static final Logger log = Logger.getLogger(HttpURLService.class);
	public static String URL = "http://localhost:8080/SlipBackEnd/rest/slipURL/";
	public static <T> T POST_SERVICE(final Class<T> clazz,List<String[]> params,String PATH,Object data) {
		  try {
			  ObjectMapper mapper = new ObjectMapper();
			  String FullPath = URL+PATH;
			  log.info("param.getUrl()="+FullPath);
			  URL url = new URL(FullPath);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
//				conn.setRequestProperty("Accept", param.getMediaType());

				String input = mapper.writeValueAsString((T)data);// data.toString(); //"{\"qty\":100,\"name\":\"iPad 4\"}";

				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
				
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				String output;
				String message = null;
				log.info("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					log.info("output="+output);
					if(message == null){
						message = output;
					}else{
						message += output;
					}
				}
				
			    conn.disconnect();
			    mapper = new ObjectMapper();
			    T obj = mapper.readValue(message,clazz);
			    return obj;				
		  }
		  catch(Exception e) {
			  log.error(e, e);
			  return null;
		  }
	}
	
	public static <T> List<T> POST_RETURN_LIST(final Class<T> clazz,List<String[]> params,String PATH,Object data) {
		  try {
			  ObjectMapper mapper = new ObjectMapper();
			  String FullPath = URL+PATH;
			  log.info("param.getUrl()="+FullPath);
			  URL url = new URL(FullPath);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Accept", "application/json");

				String input = mapper.writeValueAsString(data.toString());// data.toString(); //"{\"qty\":100,\"name\":\"iPad 4\"}";

				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
				
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				String output;
				String message = null;
				log.info("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					log.info("output="+output);
					if(message == null){
						message = output;
					}else{
						message += output;
					}
				}
				
			    conn.disconnect();
			    mapper = new ObjectMapper();
			    
			    CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
			    List<T> ts = mapper.readValue(message, listType);
			     
			    return ts;
		  }
		  catch(Exception e) {
			  log.error(e, e);
			  return null;
		  }
	}
	
	public static <T> T GET_SERVICE(final Class<T> clazz,List<String[]> params,String PATH) {
		  try { 
		  	String FullPath = URL+PATH;
		  	log.info("urlStr="+FullPath);
			URL url = new URL(FullPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			String message=null;
			log.info("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				log.info("output="+output);
				if(message == null){
					message = output;
				}else{
					message += output;
				}
			}

			conn.disconnect();		        
	        
	        Object out = message;
		     if(out==null){
		    	return null; 
		     }
		     ObjectMapper mapper = new ObjectMapper();
		     T obj = mapper.readValue(message,clazz);
		    return obj;
		  }
		  catch(Exception e) {
			  log.error(e, e);
			  return null;
		  }
	}
	
	public static <T> List<T> GET_SERVICE_LIST(final Class<T> clazz,List<String[]> params,String PATH) {
		  try { 
		  	String FullPath = URL+PATH;
		  	log.info("urlStr="+FullPath);
			URL url = new URL(FullPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			String message=null;
			log.info("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				log.info("output="+output);
				if(message == null){
					message = output;
				}else{
					message += output;
				}
			}

			conn.disconnect();		        
	        
	        Object out = message;
		     if(out==null){
		    	return null; 
		     }
		     ObjectMapper mapper = new ObjectMapper();
		  
		     CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		     List<T> ts = mapper.readValue(message, listType);
		     
		     return ts;
		   
		  }
		  catch(Exception e) {
			  //log.error(e, e);
			  System.out.println(e.getMessage());
			  return null;
		  }
	}
	
//	public static <T> String objectToXML(T input,Class clazz) throws JAXBException {
//	    StringWriter writer = new StringWriter();
//	    try {
//	    	JAXBContext jc = JAXBContext.newInstance(clazz);
//			JAXBElement<T> je2 = new JAXBElement<T>(new QName(clazz.getSimpleName()),clazz , input);
//	        Marshaller marshaller = jc.createMarshaller();
//	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
//	        marshaller.marshal(je2, writer);
//	        String temp = writer.toString();
//	        return  temp; //temp.substring(56,temp.length());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//	   return null;
//	}
//	
//	public static <T> List<T> getList(String path, List<String[]> params, final Class<T> clazz) {
//	    GenericType<List<T>> type = getListType(clazz);
//	    ResteasyClient client = new ResteasyClientBuilder().build();
//		Response response = client.target(URL).request(MediaType.APPLICATION_JSON).get(Response.class);	     
//	    return response.readEntity(type);
//		
//	}
//
//	public static <T> T getObject(String path, List<String[]> params, final Class<T> type) {
//	  
//	    ResteasyClient client = new ResteasyClientBuilder().build();
//		Response response = client.target(URL).request(MediaType.APPLICATION_JSON).get(Response.class);
//	    return (T)response.readEntity(new GenericType(type) {});
//		
//		
//	}
//	
//	
//	
//	private static <T> GenericType<List<T>> getListType(final Class<T> clazz) {
//	    ParameterizedType genericType = new ParameterizedType() {
//	        public Type[] getActualTypeArguments() {
//	            return new Type[]{clazz};
//	        }
//
//	        public Type getRawType() {
//	            return List.class;
//	        }
//
//	        public Type getOwnerType() {
//	            return List.class;
//	        }
//	    };
//	    return new GenericType<List<T>>(genericType) { };
//	}
	
	public static void main(String[] args) {

	}
	
}
