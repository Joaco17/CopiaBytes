import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamBytes {
	
	//Declaramos atributos de la clase
	private static FileInputStream fi=null;
	private static FileOutputStream fo=null;
	

	public static void main(String[] args) {
		//Si abrir fihero es true se ejecutará el metodo copiaBytes()
		if(abrirFichero("lena.jpg","copiaLena.jpg")){
			copiaBytes();
			System.out.println("Copia realizada correctamente");
		}
	}
	
	/*Se generan dos objetos File con los nombres recibidos por parámetro y a continuación se comprueba que el fichero origen 
	  del cual queremos leer existe, si este existe, declaramos los atributos de la clase y devolvemos true, de lo contrario, devolvemos false.*/
	
	//Se capturan las excepciones con el encapsulado try/catch
	
	public static boolean abrirFichero(String nombreOrigen, String nombreDestino){
		
		File origen = new File(nombreOrigen);
		File destino = new File(nombreDestino);
		
		try{
			if(origen.exists()){
				//FileInputStream: Clase de Java para realizar lectura de bytes (Archivos binarios)
				//FileOutputStream: Clase de Java para realizar escritura de bytes (Archivos binarios)
				fi = new FileInputStream(origen);
				fo = new FileOutputStream(destino);
				return true;
			}
			else{
				System.err.println("No existe el fichero binario espicificado.");
			}
		}
		catch(SecurityException noExiste){
			noExiste.printStackTrace();
		}
		catch (FileNotFoundException noExiste) {
			noExiste.printStackTrace();
		}
		return false;
				
	}
	
	/*Este método solo se ejecuta si existe el fichero binario origen, y se puede efectuar la lectura de bytes sobre él
	  Comienza leyendo byte a byte el fichero origen y por cada byte leido del origen, lo escribe en el fichero destino para obetener una copia exacta*/
	
	public static void copiaBytes(){
		//array de tipo Byte
		byte[] buffer = new byte[1024*1024];
		int cadena;
		
		try {
			//While de copia de Bytes
			while ((cadena = fi.read(buffer)) > 0) {
				fo.write(buffer, 0, cadena);
			}
		}
		catch (IOException errorEscritura) {
			errorEscritura.printStackTrace();
		}
		finally{
			try {
				//Cerramos streams
				fi.close();
				fo.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}