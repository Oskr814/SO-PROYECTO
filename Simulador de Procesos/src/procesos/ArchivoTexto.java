package procesos;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedWriter;
import java.nio.charset.Charset;

public class ArchivoTexto {
	BufferedWriter br;
	
	public void crearArchivoPlano() { //Metodo empleado para la creacion del archivo plano de texto
		try{
			Path path = Paths.get("procesos.txt");
			
		if(Files.exists(path)){
			br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.APPEND);
		}else
			br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE);
		}catch(IOException e){
			System.out.println("Error en la creacion del archivo");
		}
		
	}
	
	public void escribirEnArchivoPlano( String informacionProceso) { //Metodo empleado para escribir en el archivo plano
		try {
		br.write(informacionProceso); //Aqui se debe llamar para crear una nueva linea de descripcion del proceso.
		br.newLine();
		br.close();
		}catch(IOException e) {
			System.out.println("Error en la escritura del archivo");
		}
	}
	
	
}