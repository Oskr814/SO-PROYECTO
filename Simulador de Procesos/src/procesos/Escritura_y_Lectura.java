package procesos;
import java.util.ArrayList;
import java.util.Random;

public class Escritura_y_Lectura {
	
	private ArrayList<String> listaID_Utilizables = new ArrayList<String>(10000);
	private ArrayList<AtributosProceso> listaProcesosListo  = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosEjecutando = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosBloqueado = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosNuevo = new ArrayList<AtributosProceso>(10);
	private ArrayList<AtributosProceso> listaProcesosTerminado = new ArrayList<AtributosProceso>();
	private Random random = new Random();
	
	
	public Escritura_y_Lectura() {
		// TODO Auto-generated constructor stub
		for(int i=0 ; i<10000 ; i++) {//estructura correcta que debe tener el ID asi que se harán como String
			if(i<=9) {
				listaID_Utilizables.set(i, "000"+Integer.toString(i));
			}else if(i>9 && i<=99) {
				listaID_Utilizables.set(i, "00"+Integer.toString(i));
			}else if(i>99 && i<=999) {
				listaID_Utilizables.set(i, "0"+Integer.toString(i));
			}else
				listaID_Utilizables.set(i, Integer.toString(i));
		}
	}
	
	public void procesoNuevo () {
		int IndiceUtilizado = random.nextInt(listaID_Utilizables.size());//sacamos el numero al azar para la creacion de procesos, que será el indice de la lista disponible
		int ID_proceso = 0;
		listaProcesosNuevo.add(new AtributosProceso(listaID_Utilizables.get(IndiceUtilizado)));//sacamos el String, con las regulaciones necesarias para mandarlo como parametro al constructor de "AtributosProceso"
		listaID_Utilizables.remove(IndiceUtilizado);//Eliminamos el item para evitar que se repita nuestro ID que debe ser único	
	}
	
	public void estadoEjecucion_Bloqueado (String instruccionBloqueo, String ID) {//funcion que revisará si el proceso llego a la instruccion de bloqueo y hace la transicion 
		if(listaProcesosEjecutando.get(buscarIndice(ID, listaProcesosEjecutando)).getInstrucionBloqueo() == instruccionBloqueo) {
			listaProcesosBloqueado.add(listaProcesosEjecutando.get(buscarIndice(ID, listaProcesosEjecutando)));
			listaProcesosEjecutando.remove(buscarIndice(ID, listaProcesosEjecutando));
		}
	}
	
	public void estadoNuevo_Listo(){//funcion que pasará un maximo de 10 procesos a la lista "LISTO"
		int i = -1;//indice inicializadop en -1 para que genero un error si no se ha hecho asignacion
		while(listaProcesosListo.size() <= 10) {//condicion para asignar 10 procesos como mucho a la lista "LISTO"
			if (hayPrioridad(1, listaProcesosNuevo)) {
				i = buscarPrioridad(1, listaProcesosNuevo);//indice donde está el proceso de 'x' prioridad que se moverá que se moverá
				listaProcesosListo.add(listaProcesosNuevo.get(i));//Añade a la lista"LISTO" el proceso desde "NUEVO"
				listaProcesosNuevo.remove(i);//removemos el proceso que se trasladó
				i=-1;//reiniciamos el indice para no
			}else {
				if (hayPrioridad(2, listaProcesosNuevo)) {
					i = buscarPrioridad(2, listaProcesosNuevo);
					listaProcesosListo.add(listaProcesosNuevo.get(i));
					listaProcesosNuevo.remove(i);
					i=-1;
				}else {
					if (hayPrioridad(3, listaProcesosNuevo)) {
						i = buscarPrioridad(3, listaProcesosNuevo);
						listaProcesosListo.add(listaProcesosNuevo.get(i));
						listaProcesosNuevo.remove(i);
						i=-1;
					}else {
						procesoNuevo();
					}
				}
			}
		}
	}
	
	public void estadoListo_Ejecucion() {//funcion que pasará procesos desde "LISTO" a "EJECUCION"
		int i=-1; //indice del proceso en la lista "LISTO"
		while (listaProcesosEjecutando.size() <= 10) {//cantidad de procesos que estarán el la lista de "EJECUCION"
			if (hayPrioridad(1, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
				i = buscarPrioridad(1, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
				listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
				listaProcesosListo.remove(i);//removemos el proceso de LISTO
				i = -1;//reiniciamos el contador
			}else {
				if (hayPrioridad(2, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
					i = buscarPrioridad(2, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
					listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
					listaProcesosListo.remove(i);//removemos el proceso de LISTO
					i = -1;//reiniciamos el contador
				}else {
					if (hayPrioridad(3, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
						i = buscarPrioridad(3, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
						listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
						listaProcesosListo.remove(i);//removemos el proceso de LISTO
						i = -1;//reiniciamos el contador
					}else {
						estadoNuevo_Listo();
					}
				}
			}
		}
	}
	
	public void estadoBloqueado_Listo(int cantidadCiclos, String ID) {//Función que determina si un proceso cumplió con los ciclos respectivos para estar en la lista de "BLOQUEO" y los pasa a "LISTO"
		if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getEventoBloqueo() == 3) {
			if(cantidadCiclos == 13) {
				listaProcesosListo.add(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)));
				listaProcesosBloqueado.remove(buscarIndice(ID, listaProcesosBloqueado));
			}
		}else {
			if(cantidadCiclos == 27) {
				listaProcesosListo.add(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)));
				listaProcesosBloqueado.remove(buscarIndice(ID, listaProcesosBloqueado));
			}
		}
	}
	
	public int buscarIndice (String ID, ArrayList<AtributosProceso> lista) {//funcion que nos retornará el indice de la lista donde está el proceso con el respectivo ID
		for(int i=0 ; i<lista.size() ; i++) {
			if(lista.get(i).getIdentificadorProceso() == ID) {
				return i;
			}
		}
		return -1;
	}
	
	public void ejecucion() {//aún no termino esto
		int NumeroDeProcesos = 0;
		int ciclosDelProcesador = 0;
		while (true) {
		
			while (NumeroDeProcesos <= 10) {
				if(listaProcesosNuevo.size() <=  10){
					procesoNuevo();
					System.out.println("se ha creado un nuevo proceso y colocado en la lista de 'NUEVOS'");
					NumeroDeProcesos++;
				}else{
					NumeroDeProcesos = 0;
					break;
				}
			}
			
			
			for(int i=0 ; i<listaProcesosNuevo.size() ; i++){
				if(listaProcesosNuevo.get(i).getPrioridadProceso() == 1){
					listaProcesosListo.add(listaProcesosNuevo.get(i));
					System.out.println("se ha mandado un proceso de 'NUEVO' a 'LISTO'");
					listaProcesosNuevo.remove(i);
				}
			}
			for(int i=0 ; i<listaProcesosNuevo.size() ; i++){
				if(listaProcesosNuevo.get(i).getPrioridadProceso() == 2){
					listaProcesosListo.add(listaProcesosNuevo.get(i));
					System.out.println("se ha mandado un proceso de 'NUEVO' a 'LISTO'");
					listaProcesosNuevo.remove(i);
				}
			}
			for(int i=0 ; i<listaProcesosNuevo.size() ; i++){
				if(listaProcesosNuevo.get(i).getPrioridadProceso() == 3){
					listaProcesosListo.add(listaProcesosNuevo.get(i));
					System.out.println("se ha mandado un proceso de 'NUEVO' a 'LISTO'");
					listaProcesosNuevo.remove(i);
				}
			}
			
			while (NumeroDeProcesos <= 10) {
				if(listaProcesosEjecutando.size() <=  10){
					for(int i=0 ; i<listaProcesosListo.size() ; i++){
						if(listaProcesosListo.get(i).getPrioridadProceso() == 1){
							listaProcesosEjecutando.add(listaProcesosListo.get(i));
							System.out.println("se ha mandado un proceso de 'LISTO' a 'EJECUCION'");
							listaProcesosListo.remove(i);
							NumeroDeProcesos++;
							if(NumeroDeProcesos ==  10) {
								break;
							}
						}
					}
					for(int i=0 ; i<listaProcesosListo.size() ; i++){
						if(NumeroDeProcesos ==  10) {
							break;
						}
						if(listaProcesosListo.get(i).getPrioridadProceso() == 2){
							listaProcesosEjecutando.add(listaProcesosListo.get(i));
							System.out.println("se ha mandado un proceso de 'LISTO' a 'EJECUCION'");
							listaProcesosListo.remove(i);
							NumeroDeProcesos++;
							if(NumeroDeProcesos ==  10) {
								break;
							}
						}
					}
					for(int i=0 ; i<listaProcesosListo.size() ; i++){
						if(NumeroDeProcesos ==  10) {
							break;
						}
						if(listaProcesosListo.get(i).getPrioridadProceso() == 3){
							listaProcesosEjecutando.add(listaProcesosListo.get(i));
							System.out.println("se ha mandado un proceso de 'LISTO' a 'EJECUCION'");
							listaProcesosListo.remove(i);
							NumeroDeProcesos++;
							if(NumeroDeProcesos ==  10) {
								break;
							}
						}
					}
					
				}else{
					NumeroDeProcesos = 0;
					break;
				}
			}
			
		}
	}

	public int buscarPrioridad(int Prioridad, ArrayList<AtributosProceso> Lista) {//funcion que retorna el respectivo indice del proceso con la prioridad pedida, de la lista enviada
		for(int i=0 ; i<Lista.size() ; i++) {
			if(Lista.get(i).getPrioridadProceso() == Prioridad) {
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
	
	
}
