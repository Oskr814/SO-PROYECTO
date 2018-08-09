package procesos;

public class GestorProcesos {
	public static void main(String args[]) {
		int n=0;
		while(n<=5) {

			ArchivoTexto archivotextoplano[] = new ArchivoTexto[10];
			AtributosProceso proceso = new AtributosProceso("0003");
			/*
			archivotextoplano.crearArchivoPlano();
			proceso.nuevoProceso();
			archivotextoplano.escribirEnArchivoPlano(proceso.toString());
			*/
			System.out.println("proceso #"+n);
			
			n++;
		}
	}
}
