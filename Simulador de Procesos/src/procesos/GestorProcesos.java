package procesos;

import java.util.ArrayList;
import java.util.Scanner;

import Implementacion.Ejecucion;

public class GestorProcesos {
	public static void main(String args[]) throws InterruptedException {
		int n=0;
				
		GuardarEstadoEjecucionProcesos procesos = new GuardarEstadoEjecucionProcesos();
		
		Ejecucion gestor = new Ejecucion();
		
		//gestor.mostrarInformacionEstados();
		//Thread.sleep(5000);
		
		while(true) {

	
				n++;

				//System.out.println("CicloDelProcesador #"+n);
				gestor.mostrarInformacionEstados();
				
				try {
					gestor.cicloEjecucion();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("salió del ciclo de ejecucion");
				gestor.actualizarInformacion();
		
			
			
		}
	
	
	}
	
}
