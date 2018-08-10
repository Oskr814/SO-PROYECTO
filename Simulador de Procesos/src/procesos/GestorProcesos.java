package procesos;

public class GestorProcesos {
	public static void main(String args[]) {
		int n=0;
		
		Escritura_y_Lectura gestor = new Escritura_y_Lectura();
		
		while(n<=10) {

			n++;

			System.out.println("Ciclo #"+n);
			
			gestor.procesoNuevo();
			
			gestor.estadoNuevo_Listo();
			
			gestor.estadoListo_Ejecucion();
			
			gestor.cicloEjecucion();
			
			
		}
	}
}
