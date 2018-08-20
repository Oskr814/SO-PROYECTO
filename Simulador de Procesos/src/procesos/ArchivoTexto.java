package procesos;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.nio.charset.Charset;

public class ArchivoTexto {
	
	
	public void escrbirArchivoPlanoEstado(ArrayList<AtributosProceso> lista) { //Metodo empleado para la creacion del archivo plano de texto donde se listan todos los procesos que se encuentran en cada lista de estado.
		BufferedWriter br;
		
		String estado = "";
		try{
			Path path;
			switch(lista.get(lista.size()-1).getEstadoProceso()) {
			case 0: //Estado  nuevo
				path = Paths.get("Procesos/Nuevo/Procesos[Nuevos].txt");
				estado = "Nuevo";
				break;
			case 1: //Estado  listo
				path = Paths.get("Procesos/Listo/Procesos[Listos].txt");
				estado = "Listo";
				break;
			case 2: //Estado  ejecucion
				path = Paths.get("Procesos/Ejecucion/Procesos[Ejecucion].txt");
				estado = "Ejecucion";
				break;
			case 3: //Estado  bloqueado
				path = Paths.get("Procesos/Bloqueado/Procesos[Bloqueados].txt");
				estado = "Bloqueado";
				break;
			case 4: ////Estado  terminado
				path = Paths.get("Procesos/Terminado/Procesos[Terminados].txt");
				estado = "Terminado";
				break;
			default:
				path = Paths.get("");
				break;
			}
			//Thread.sleep(100);
			ElimiarArchivoPlano(estado);
			
		if(Files.exists(path)){
			br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.TRUNCATE_EXISTING);
		}else
			br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE);
		
		for(int i = 0; i<lista.size() ; i++) {
			br.write(lista.get(i).toString()); //Aqui se debe llamar para crear una nueva linea de descripcion del proceso.
			br.newLine();
		}
		escribirEnArchivoPlanoProceso(lista, estado);
		br.close();
		//escribirEnArchivoPlanoProceso(lista, estado);
		
		
		}catch(IOException e){
			System.out.println(e);
		} 
	}
	
	protected void escribirEnArchivoPlanoProceso(ArrayList<AtributosProceso> lista, String estado) { //Metodo empleado para escribir en el archivo plano
		BufferedWriter br;
		try {
		for(int i = 0; i<lista.size() ; i++) {	//Creamos todo
		Path path = Paths.get("Procesos/"+estado+"/"+lista.get(i).getIdentificadorProceso()+".txt");
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
	
	public void ElimiarArchivoPlano(String estado) {
		
			//Path path = FileSystems.getDefault().getPath("Procesos/"+estadoProceso(proceso.getEstadoProceso()), proceso.getIdentificadorProceso()+".txt");
			//Path path = Paths.get("Procesos/"+estadoProceso(proceso.getEstadoProceso())+"/"+proceso.getIdentificadorProceso()+".txt");
		 
		
	     
			try {

			    File[] archivos = new File("Procesos/"+estado+"/").listFiles(new FileFilter() {
			
			        public boolean accept(File archivo) {
			
			            if (archivo.isFile())
			
			                return archivo.getName().endsWith(".txt");
			
			            return false;
			
			        }
			
			    });
			
			    for (File archivo : archivos)
			
			        archivo.delete();
			}catch(Exception e) {
				
			}
					
			
		
	}
	

public void listarFicherosPorCarpeta(final File carpeta) {
    for (final File ficheroEntrada : carpeta.listFiles()) {
        if (ficheroEntrada.isDirectory()) {
            listarFicherosPorCarpeta(ficheroEntrada);
        } else {
            System.out.println(ficheroEntrada.getName());
        }
    }
}
	

	
	public String estadoProceso(int estadoProceso) {
		
		switch(estadoProceso) {
		case 0:
			return "Nuevo";
		case 1:
			return "Listo";
		case 2:
			return "Ejecucion";
		case 3:
			return "Bloqueado";
		case 4:
			return "Terminado";
		default:
			return "";
		}
		
	}
	
	
}