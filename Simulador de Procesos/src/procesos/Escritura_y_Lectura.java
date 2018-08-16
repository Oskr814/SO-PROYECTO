package procesos;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Escritura_y_Lectura implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<String> listaID_Utilizables = new ArrayList<String>(10000);
	private ArrayList<AtributosProceso> listaProcesosListo  = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosEjecutando = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosBloqueado = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosNuevo = new ArrayList<AtributosProceso>(10);
	private ArrayList<AtributosProceso> listaProcesosTerminado = new ArrayList<AtributosProceso>();
	
	FileOutputStream estadoProcesoNuevo = null;
	FileOutputStream estadoProcesoListo= null;
	FileOutputStream estadoProcesoEjecutado = null;
	FileOutputStream estadoProcesoBloqueado = null;
	FileOutputStream estadoProcesoTerminado = null;
	
	private int maximoProcesos = 5;
	//int ciclosDelProcesador; //Esta variable la defino de manera constante para fines de codificacion, deberia ser especificada por el usuario.
	
	public Escritura_y_Lectura(){
		for(int i=0 ; i<10000 ; i++) {//estructura correcta que debe tener el ID asi que se harán como String
			if(i<=9) {
				listaID_Utilizables.add(i, "000"+Integer.toString(i));
			}else if(i>9 && i<=99) {
				listaID_Utilizables.add(i, "00"+Integer.toString(i));
			}else if(i>99 && i<=999) {
				listaID_Utilizables.add(i, "0"+Integer.toString(i));
			}else
				listaID_Utilizables.add(i, Integer.toString(i));
		}
		//empezar();
	}
	
	public ArrayList<String> getListaID_Utilizables() {
		return listaID_Utilizables;
	}
	public void setListaID_Utilizables(ArrayList<String> listaID_Utilizables) {
		this.listaID_Utilizables = listaID_Utilizables;
	}

	public FileOutputStream getEstadoProcesoNuevo() {
		return estadoProcesoNuevo;
	}




	public void setEstadoProcesoNuevo(FileOutputStream estadoProcesoNuevo) {
		this.estadoProcesoNuevo = estadoProcesoNuevo;
	}




	public FileOutputStream getEstadoProcesoListo() {
		return estadoProcesoListo;
	}




	public void setEstadoProcesoListo(FileOutputStream estadoProcesoListo) {
		this.estadoProcesoListo = estadoProcesoListo;
	}




	public FileOutputStream getEstadoProcesoEjecutado() {
		return estadoProcesoEjecutado;
	}




	public void setEstadoProcesoEjecutado(FileOutputStream estadoProcesoEjecutado) {
		this.estadoProcesoEjecutado = estadoProcesoEjecutado;
	}




	public FileOutputStream getEstadoProcesoBloqueado() {
		return estadoProcesoBloqueado;
	}




	public void setEstadoProcesoBloqueado(FileOutputStream estadoProcesoBloqueado) {
		this.estadoProcesoBloqueado = estadoProcesoBloqueado;
	}




	public FileOutputStream getEstadoProcesoTerminado() {
		return estadoProcesoTerminado;
	}




	public void setEstadoProcesoTerminado(FileOutputStream estadoProcesoTerminado) {
		this.estadoProcesoTerminado = estadoProcesoTerminado;
	}




	public int getMaximoProcesos() {
		return maximoProcesos;
	}




	public void setMaximoProcesos(int maximoProcesos) {
		this.maximoProcesos = maximoProcesos;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public ArrayList<AtributosProceso> getListaProcesosListo() {
		return listaProcesosListo;
	}

	public void setListaProcesosListo(ArrayList<AtributosProceso> listaProcesosListo) {
		this.listaProcesosListo = listaProcesosListo;
	}

	public ArrayList<AtributosProceso> getListaProcesosEjecutando() {
		return listaProcesosEjecutando;
	}

	public void setListaProcesosEjecutando(ArrayList<AtributosProceso> listaProcesosEjecutando) {
		this.listaProcesosEjecutando = listaProcesosEjecutando;
	}

	public ArrayList<AtributosProceso> getListaProcesosBloqueado() {
		return listaProcesosBloqueado;
	}

	public void setListaProcesosBloqueado(ArrayList<AtributosProceso> listaProcesosBloqueado) {
		this.listaProcesosBloqueado = listaProcesosBloqueado;
	}

	public ArrayList<AtributosProceso> getListaProcesosNuevo() {
		return listaProcesosNuevo;
	}

	public void setListaProcesosNuevo(ArrayList<AtributosProceso> listaProcesosNuevo) {
		this.listaProcesosNuevo = listaProcesosNuevo;
	}

	public ArrayList<AtributosProceso> getListaProcesosTerminado() {
		return listaProcesosTerminado;
	}

	public void setListaProcesosTerminado(ArrayList<AtributosProceso> listaProcesosTerminado) {
		this.listaProcesosTerminado = listaProcesosTerminado;
	}
	public int buscarIndice (String ID, ArrayList<AtributosProceso> lista) {//funcion que nos retornará el indice de la lista donde está el proceso con el respectivo ID
		for(int i=0 ; i<lista.size() ; i++) {
			if(lista.get(i).getIdentificadorProceso() == ID) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	public boolean hayPrioridad (int Prioridad, ArrayList<AtributosProceso> Lista) {//funcion que corrobora si existe entre los elementos de la lista u proceso con ese grado de prioridad
		for(int i=0 ; i<Lista.size() ; i++) {
			if(Lista.get(i).getPrioridadProceso() == Prioridad) {
				return true;
			}
		}
		return false;
	}
	
	public int buscarProceso(String ID, ArrayList<AtributosProceso> Lista) {
		for(int i=0 ; i<Lista.size() ; i++ ) {
			if(Lista.get(i).getIdentificadorProceso() == ID) {
				return i;
			}
		}
		return -1;
	}

	public int buscarPrioridad(int Prioridad, ArrayList<AtributosProceso> Lista) {//funcion que retorna el respectivo indice del proceso con la prioridad pedida, de la lista enviada
		for(int i=0 ; i<Lista.size() ; i++) {
			if(Lista.get(i).getPrioridadProceso() == Prioridad) {
				return i;
			}
		}
		return -1;
	}
//----------------------------------------------------------
		
//--------------------------------------------------------	
	
}
