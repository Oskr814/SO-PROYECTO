package procesos;

import java.util.ArrayList;

public class EjecucionListoYEjecucionTerminado {
	
	private ArrayList<AtributosProceso> listaProcesosListo  = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosEjecutando = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosBloqueado = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosNuevo = new ArrayList<AtributosProceso>(10);
	private ArrayList<AtributosProceso> listaProcesosSaliente = new ArrayList<AtributosProceso>();
	
	//variables condicionales para saber si debe ir a otro proceso
	public int ciclosDelProcesador = 10; //Esta variable la defino de manera constante para fines de codificacion, deberia ser especificada por el usuario.
	
	public void ejecucionAListo(AtributosProceso proceso) { //Cambio de estado de Ejecucion a listo, asumiento que los procesos entran a lista ejecucion por prioridad
		for(int i = 0 ; i<ciclosDelProcesador ; i++) {
			if(proceso.getInstruccionActual() == Integer.parseInt(proceso.getInstrucionBloqueo())) {
				//Aqui se debe mandar a bloqueo
			}else
				proceso.SetInstruccionActual();
				if(proceso.getInstruccionActual() == Integer.parseInt(proceso.getCantidadInstrucciones()))
				//Aqui se debe mandar a terminado
				ejecucionTerminado(proceso);
		}
		listaProcesosEjecutando.remove(proceso);
		listaProcesosListo.add(proceso);
	}
	
	public void ejecucionTerminado(AtributosProceso proceso) {
		listaProcesosEjecutando.remove(proceso);
		listaProcesosSaliente.add(proceso);
	}

}
