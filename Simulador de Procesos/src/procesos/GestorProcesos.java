package procesos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Implementacion.Ejecucion;

public class GestorProcesos {
	public static void main(String args[]) throws InterruptedException {
		int n=0;
				
		GuardarEstadoEjecucionProcesos procesos = new GuardarEstadoEjecucionProcesos();
		
		Ejecucion gestor = null;
		
		try {
			gestor = new Ejecucion();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		File carpeta = new File("C:/Users/Public/RegistroProcesosGrupo6");
		carpeta.mkdir();
		
		
		
		
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
