package procesos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Implementacion.Ejecucion;

public class GestorProcesos {
	public static void main(String args[]){
		int n=0;
		/*
		try {
			
			ArrayList numeros = new ArrayList<>();
			ArrayList LecturaNumero = new ArrayList<>();
			ArrayList<AtributosProceso> listaProcesosListo  = new ArrayList<AtributosProceso>();
			 ArrayList<AtributosProceso> listaProcesosEjecutando = new ArrayList<AtributosProceso>();
			 ArrayList<AtributosProceso> listaProcesosBloqueado = new ArrayList<AtributosProceso>();
			 ArrayList<AtributosProceso> listaProcesosNuevo = new ArrayList<AtributosProceso>();
			 ArrayList<AtributosProceso> listaProcesosTerminado = new ArrayList<AtributosProceso>();
			
			 for (int i=0 ; i<10 ; i++) {
				 numeros.add(i);
			 }
			 
			 ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("RegistroProcesosGrupo6/Numeros.txt"));
			 escribir.writeObject(numeros);
			 escribir.close();
			
			 
			 ObjectInputStream recuperaNuevo = new ObjectInputStream(new FileInputStream("RegistroProcesosGrupo6/Numeros.txt"));
			 LecturaNumero = (ArrayList)recuperaNuevo.readObject();
			 recuperaNuevo.close();
		
			 /*
			ObjectInputStream recuperaNuevo = new ObjectInputStream(new FileInputStream("RegistroProcesosGrupo6/EstadoNuevo.tiger"));
			ObjectInputStream recuperaListos = new ObjectInputStream(new FileInputStream("RegistroProcesosGrupo6/EstadoListo.tiger"));
			ObjectInputStream recuperaEjecucion = new ObjectInputStream(new FileInputStream("RegistroProcesosGrupo6/EstadoEjecucion.tiger"));
			ObjectInputStream recuperaBloqueado = new ObjectInputStream(new FileInputStream("RegistroProcesosGrupo6/EstadoBloqueado.tiger"));
			ObjectInputStream recuperaTerminado = new ObjectInputStream(new FileInputStream("RegistroProcesosGrupo6/EstadoTerminado.tiger"));
			
			listaProcesosNuevo = (ArrayList<AtributosProceso>) recuperaNuevo.readObject();
			listaProcesosListo = (ArrayList<AtributosProceso>) recuperaListos.readObject();
			listaProcesosEjecutando = (ArrayList<AtributosProceso>) recuperaEjecucion.readObject();
			listaProcesosBloqueado = (ArrayList<AtributosProceso>) recuperaBloqueado.readObject();
			listaProcesosTerminado = (ArrayList<AtributosProceso>) recuperaTerminado.readObject();
			
			recuperaNuevo.close();
			recuperaListos.close();
			recuperaEjecucion.close();
			recuperaBloqueado.close();
			recuperaTerminado.close();
			
			for (Object e: LecturaNumero) {
				System.out.println(e.toString());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Problema en la Lectura");
			e.printStackTrace();
		}
		
		
		System.out.println("NO entró");
		*/
		
		
		
		GuardarEstadoEjecucionProcesos procesos = new GuardarEstadoEjecucionProcesos();
		Ejecucion gestor = null;
		gestor = new Ejecucion();
		File carpeta = new File("RegistroProcesosGrupo6");
		carpeta.mkdir();
		while(true) {
				n++;
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
				gestor.actualizarInformacion();
		}
	}
	
}
