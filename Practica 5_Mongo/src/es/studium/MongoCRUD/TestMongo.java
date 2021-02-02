package es.studium.MongoCRUD;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TestMongo 
{
	public static void main(String[] args) 
	{
		//Creamos un objeto de tipo MongoClient, con el que establecemos la conexión a la base de datos harry
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		
		//Creamos la base datos
		MongoDatabase database = conexion.getDatabase("harry");
		
		//Creamos la coleccion de documentos
		MongoCollection<Document> personajes =database.getCollection("characters");
				
		//MongoCRUD.consultarDocumentos(personajes);
		//MongoCRUD.insertarDocumentos(personajes);
		//MongoCRUD.actualizarDocumentos(personajes);
		//MongoCRUD.eliminarDocumentos(personajes);
	}
}	
