package procesos;
import java.io.File;

public class GestorProcesos {
	public static void main(String args[]) {
		int n=0;
		//Las siguientes lineas de codigo nos crearán las respectivas carpetas donde se almacenarán los procesos
		File procesos = new File("procesos");
		procesos.mkdir();
		File nuevo = new File("procesos/Nuevo");
		nuevo.mkdirs();
		File Listo = new File("procesos/Listo");
		Listo.mkdirs();
		File Ejecucion = new File("procesos/Ejecucion");
		Ejecucion.mkdirs();
		File Bloqueado = new File("procesos/Bloqueado");
		Bloqueado.mkdirs();
		File Terminado = new File("procesos/Terminado");
		Terminado.mkdirs();
		
		System.out.println("se creó una carpeta de procesos");
		
		Escritura_y_Lectura gestor = new Escritura_y_Lectura();

		while(n<=10) {

			n++;

			System.out.println("CicloDelProcesador #"+n);
			
			
			gestor.cicloEjecucion();
			
		}
	}
}
