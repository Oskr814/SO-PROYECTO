package procesos;


public class GestorProcesos {
	public static void main(String args[]) throws InterruptedException {
		int n=0;
		//Las siguientes lineas de codigo nos crear�n las respectivas carpetas donde se almacenar�n los procesos
		/*File procesos = new File("procesos");
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
		
		System.out.println("se cre� una carpeta de procesos");*/
		
		Escritura_y_Lectura gestor = new Escritura_y_Lectura();

		while(true) {

			n++;

			//System.out.println("CicloDelProcesador #"+n);
			gestor.getEjecucion().mostrarInformacionEstados( String.valueOf(n), "CicloDelProcesador");
			
			gestor.cicloEjecucion();
			gestor.actualizarInformacion();
		
		}
		
	
	}
	
	
}
