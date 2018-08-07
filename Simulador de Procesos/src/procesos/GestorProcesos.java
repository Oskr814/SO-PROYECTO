package procesos;

public class GestorProcesos {
	public static void main(String args[]) {
		ArchivoTexto archivotextoplano = new ArchivoTexto();
		AtributosProceso proceso = new AtributosProceso();
		
		archivotextoplano.crearArchivoPlano();
		proceso.nuevoProceso();
		archivotextoplano.escribirEnArchivoPlano(proceso.toString());
		
		System.out.println("hola mundo");
	}
}
