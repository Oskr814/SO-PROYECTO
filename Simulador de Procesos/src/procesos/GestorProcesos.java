package procesos;

public class GestorProcesos {
	public static void main(String args[]) throws InterruptedException {
		int n=0;
		//Las siguientes lineas de codigo nos crearán las respectivas carpetas donde se almacenarán los procesos
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
		
		System.out.println("se creó una carpeta de procesos");*/
		//GuardarEstadoEjecucionProcesos procesos = new GuardarEstadoEjecucionProcesos();
		Escritura_y_Lectura gestor = new Escritura_y_Lectura();
		gestor.getEjecucion().mostrarInformacionEstados("Preparando Gestor de Procesos", "Mensaje");
		Thread.sleep(5000);
		gestor.getEjecucion().mostrarInformacionEstados("", "Mensaje");
		while(true) {

			n++;

			//System.out.println("CicloDelProcesador #"+n);
			gestor.getEjecucion().mostrarInformacionEstados( String.valueOf(n), "CicloDelProcesador");
			
			gestor.cicloEjecucion();
			/*procesos.guardarEstadoEjecucion(gestor.getListaProcesosListo(), "Listo");
			//ArrayList<AtributosProceso> Listo = procesos.recuperarEstadoEjecucion("Listo");
			for(int i=0; i<gestor.getListaProcesosListo().size() ; i++) {
					System.out.println(Listo.get(i).toString());
			}*/
			gestor.actualizarInformacion();
		
		}
		
	
	}
	
	
}
