package procesos;

public class GestorProcesos {
	public static void main(String args[]) {
		int n=0;
		
		Escritura_y_Lectura gestor = new Escritura_y_Lectura();
		
		while(n<=5) {
			
			gestor.procesoNuevo();
			
			gestor.estadoNuevo_Listo();
			
			gestor.estadoListo_Ejecucion();
			
			System.out.println("Ciclo #"+n);
			
			n++;
		}
	}
}
