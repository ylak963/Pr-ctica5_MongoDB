package es.studium.MongoCRUD;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class MongoCRUD
{

	// Función para insertar documentos en la base de datos
	public static void insertarDocumentos(MongoCollection<Document> personajes)
	{
		// Creamos un documento mediante new, al que llamamos documento con append()
		// y añadimos cada propiedad al documento creado.
		Document documento = new Document("name", "Dobby").append("species", "elfo").append("gender", "male")
				.append("house", "hogwarts").append("dateOfBirth", "20-01-1855").append("yearOfBirth", 1855)
				.append("ancestry", "unknown").append("eyeColour", "azules").append("hairColour", "ninguno");

		// Añadimos un único documento
		personajes.insertOne(documento);

		// Insertamos varios documentos creados guardando los documentos en una Lista
		List<Document> arg0 = Arrays.asList(
				Document.parse("{ name: 'Neville', species: 'humano', eyeColour: 'blue', patronus: '' }"),
				Document.parse(
						"{ nombre: 'Elieen Prince', species: 'bruja', gender: 'femenino', eyeColour: 'marrones' }"),
				Document.parse("{ name: 'Miguel Angel', age: 31, especie: 'humano', eyeColour: 'verdes' }"));
		personajes.insertMany(arg0);

	}

	public static void consultarDocumentos(MongoCollection<Document> personajes)
	{
		// Buscar todos los los personajes cuyo atributo "species" tenga como
		// valor"human"
		FindIterable<Document> buscaHumanos = personajes.find(eq("species", "human"));

		System.out.println("Mostramos todos los personajes cuyo que sea de especie humano: ");

		// Mostrar todos los personajes cuyo atributo "species" tenga como valor"human".
		for (Document personajeBuscado : buscaHumanos)
		{
			System.out.println("\t" + personajeBuscado.toJson());
		}

		// Buscar todos los personajes cuyo atributo "yeartOfBirth" sea anterior a 1979
		FindIterable<Document> buscaPersonajesAnio = personajes.find(lte("yearOfBirth", 1979));

		System.out.println("Mostramos todos los personajes cuyo año de nacimiento sea anterior a 1979: ");

		// Mostrar todos los personajes cuyo año año de nacimiento sea anterior a 1979".
		for (Document personajeBuscadoAnio : buscaPersonajesAnio)
		{
			System.out.println("\t" + personajeBuscadoAnio.toJson());
		}

		// Buscar todos los los personajes cuyo atributo "wood" de la propiedad "wand"
		// sea "holly"
		FindIterable<Document> buscaHumanosSagrados = personajes.find(eq("wand.wood", "holly"));

		System.out.println("Mostramos todos los personajes cuyo atributo wood sea de tipo holly: ");

		// Mostrar todos los personajes cuyo atributo "species" tenga como valor"human".
		for (Document personajeSagradoBuscado : buscaHumanosSagrados)
		{
			System.out.println("\t" + personajeSagradoBuscado.toJson());
		}

		// Buscar todos los los personajes que esten vivos y ademas sean estudiantes de
		// hogwarts
		FindIterable<Document> buscaPersonajeVivoH = personajes
				.find(and(eq("alive", true), eq("hogwartsStudent", true)));

		System.out.println("Mostramos todos los personajes que esten vivos y sea estudiante de Hogwarts: ");

		// Mostrar todos los personajes cuyo esten vivos sea true y su propiedad de
		// estudiante de hogwars sea true.
		for (Document personajeVivoEstudiante : buscaPersonajeVivoH)
		{
			System.out.println("\t" + personajeVivoEstudiante.toJson());
		}

	}

	public static void actualizarDocumentos(MongoCollection<Document> personajes)
	{
		// Actualizamos el primer documento que cumpla estas condiciones
		personajes.updateOne(eq("name", "Miguel Angel"),
				combine(set("ancestry", "homo sapies"), set("yearOfBirth", 1989)));
		personajes.updateOne(eq("name", "Neville"), combine(set("hairColour", "yellow"), set("house", "unknow")));

	}

	public static void eliminarDocumentos(MongoCollection<Document> personajes)
	{
		// Eliminamos el primer documento que cumpla esta condición.
		personajes.deleteOne(eq("age", 31));

	}
}
