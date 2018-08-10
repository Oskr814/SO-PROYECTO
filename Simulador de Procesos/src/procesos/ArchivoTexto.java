package procesos;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.nio.charset.Charset;

public class ArchivoTexto {
	
	
	public void escrbirArchivoPlanoEstado(ArrayList<AtributosProceso> lista) { //Metodo empleado para la creacion del archivo plano de texto donde se listan todos los procesos que se encuentran en cada lista de estado.
		BufferedWriter br;
		String estado = "";
		try{
			Path path;
			switch(lista.get(0).getEstadoProceso()) {
			case 0: //Estado ejecucion nuevo
				path = Paths.get("procesos/Nuevo/Procesos[Nuevos].txt");
				estado = "Nuevo";
				break;
			case 1: //Estado ejecucion listo
				path = Paths.get("procesos/Listo/Procesos[Listos].txt");
				estado = "Listo";
				break;
			case 2: //Estado ejecucion ejecucion
				path = Paths.get("procesos/Ejecucion/Procesos[Ejecucion].txt");
				estado = "Ejecucion";
				break;
			case 3: //Estado ejecucion bloqueado
				path = Paths.get("procesos/Bloqueado/Procesos[Bloqueados].txt");
				estado = "Bloqueado";
				break;
			case 4: ////Estado ejecucion terminado
				path = Paths.get("procesos/Terminado/Procesos[Terminados].txt");
				estado = "Terminados";
				break;
			default:
				path = Paths.get("");
				break;
			}
			
		if(Files.exists(path)){
			br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.TRUNCATE_EXISTING);
		}else
			br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE);
		
		for(int i = 0; i<lista.size() ; i++) {
			br.write(lista.get(i).toString()); //Aqui se debe llamar para crear una nueva linea de descripcion del proceso.
			br.newLine();
		}
		
		br.close();
		escribirEnArchivoPlanoProceso(lista, estado);
		
		}catch(IOException e){
			System.out.println("Error en la creacion del archivo estado");
		}
		
	}
	
	protected void escribirEnArchivoPlanoProceso(ArrayList<AtributosProceso> lista, String estado) { //Metodo empleado para escribir en el archivo plano
		BufferedWriter br;
		try {
		
		for(int i = 0; i<lista.size() ; i++ ) { //Limpiamos todo
			for(int j = 1; j<=Integer.parseInt(lista.get(i).getCantidadInstrucciones()); j++) {
				Path path = Paths.get("procesos/"+estado+"/"+lista.get(i).getIdentificadorProceso()+".txt");
				if(Files.exists(path)) {
				br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.DELETE_ON_CLOSE);
				br.close();
				}
				
			}
		}
			
		for(int i = 0; i<lista.size() ; i++) {	//Creamos todo
		Path path = Paths.get("procesos/"+estado+"/"+lista.get(i).getIdentificadorProceso()+".txt");
			br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE);
		
			
			
		
			for(int j = 1; j<=Integer.parseInt(lista.get(i).getCantidadInstrucciones()); j++) {
				br.write(Integer.toString(j)); //Aqui se debe llamar para crear una nueva linea de descripcion del proceso.
				br.newLine();
			}
			br.close();
		}
		
		
		}catch(IOException e) {
			System.out.println("Error en la escritura del archivo proceso");
		}
	}
	
	
}