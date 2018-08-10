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
	
	private int maximoProcesos = 5;
	public int ciclosDelProcesador = 10; //Esta variable la defino de manera constante para fines de codificacion, deberia ser especificada por el usuario.
	
	public Escritura_y_Lectura() {
		// TODO Auto-generated constructor stub
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
	}
	
	public void procesoNuevo () {
		while(listaProcesosNuevo.size() < maximoProcesos) {
			int IndiceUtilizado = random.nextInt(listaID_Utilizables.size());//sacamos el numero al azar para la creacion de procesos, que será el indice de la lista disponible
			listaProcesosNuevo.add(new AtributosProceso(listaID_Utilizables.get(IndiceUtilizado)));//sacamos el String, con las regulaciones necesarias para mandarlo como parametro al constructor de "AtributosProceso"
			
			System.out.println("se ha creado un proceso: " + listaProcesosNuevo.get(buscarProceso(listaID_Utilizables.get(IndiceUtilizado), listaProcesosNuevo)).toString() + "---> NUEVO");
			listaID_Utilizables.remove(IndiceUtilizado);//Eliminamos el item para evitar que se repita nuestro ID que debe ser único
			
		}
	}
	
	public void estadoNuevo_Listo(){//funcion que pasará un maximo de 10 procesos a la lista "LISTO"
		int i = -1;//indice inicializadop en -1 para que genero un error si no se ha hecho asignacion
		while(listaProcesosListo.size() < maximoProcesos) {//condicion para asignar 10 procesos como mucho a la lista "LISTO"
			if (hayPrioridad(1, listaProcesosNuevo)) {//se buscarán los procesos de mayor prioridad y si existen entrará al 'for'
				i = buscarPrioridad(1, listaProcesosNuevo);//indice donde está el proceso de 'x' prioridad que se moverá que se moverá
				listaProcesosListo.add(listaProcesosNuevo.get(i));//Añade a la lista"LISTO" el proceso desde "NUEVO"
				listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);;
				System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'NUEVO' a 'LISTO'");
				listaProcesosNuevo.remove(i);//removemos el proceso que se trasladó
				i=-1;//reiniciamos el indice para no
			}else {
				if (hayPrioridad(2, listaProcesosNuevo)) {//se buscarán los procesos de media prioridad y si existen entrará al 'for' 
					i = buscarPrioridad(2, listaProcesosNuevo);
					listaProcesosListo.add(listaProcesosNuevo.get(i));
					listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);;
					System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'NUEVO' a 'LISTO'");
					listaProcesosNuevo.remove(i);
					i=-1;
				}else {
					if (hayPrioridad(3, listaProcesosNuevo)) {
						i = buscarPrioridad(3, listaProcesosNuevo);
						listaProcesosListo.add(listaProcesosNuevo.get(i));
						listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);;
						System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'NUEVO' a 'LISTO'");
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
		while (listaProcesosEjecutando.size() < maximoProcesos) {//cantidad de procesos que estarán el la lista de "EJECUCION"
			if (hayPrioridad(1, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
				i = buscarPrioridad(1, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
				listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
				listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).setEstadoProceso(2);;
				System.out.println("se ha pasado el proceso: " +  listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString() + "----> 'LISTO' a 'EJECUCION'");
				listaProcesosListo.remove(i);//removemos el proceso de LISTO
				i = -1;//reiniciamos el contador
			}else {
				if (hayPrioridad(2, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
					i = buscarPrioridad(2, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
					listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
					listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).setEstadoProceso(2);;
					System.out.println("se ha pasado el proceso: " +  listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString() + "----> 'LISTO' a 'EJECUCION'");
					listaProcesosListo.remove(i);//removemos el proceso de LISTO
					i = -1;//reiniciamos el contador
				}else {
					if (hayPrioridad(3, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
						i = buscarPrioridad(3, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
						listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"

						listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).setEstadoProceso(2);;
						System.out.println("se ha pasado el proceso: " +  listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString() + "----> 'LISTO' a 'EJECUCION'");
						
						listaProcesosListo.remove(i);//removemos el proceso de LISTO
						i = -1;//reiniciamos el contador
					}else {
						estadoNuevo_Listo();
					}
				}
			}
		}
	}
		
	private boolean estadoEjecucion_Bloqueado (String instruccionBloqueo, String ID) {//funcion que revisará si el proceso llego a la instruccion de bloqueo y hace la transicion 
		if(listaProcesosEjecutando.get(buscarIndice(ID, listaProcesosEjecutando)).getInstruccionesLeidas() == Integer.parseInt(instruccionBloqueo)) {
			listaProcesosBloqueado.add(listaProcesosEjecutando.get(buscarIndice(ID, listaProcesosEjecutando)));
			listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).setEstadoProceso(3);
			System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'EJECUCION' a 'BLOQUEADO'");
			listaProcesosEjecutando.remove(buscarIndice(ID, listaProcesosEjecutando));
			return true;
		}else {
			return false;
		}
	}
	
	public void estadoBloqueado_Listo(String ID) {//Función que determina si un proceso cumplió con los ciclos respectivos para estar en la lista de "BLOQUEO" y los pasa a "LISTO"
		if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getEventoBloqueo() == 3) {
			if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getCiclosEnBloqueo() == 3) {
				listaProcesosListo.add(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)));
				System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'BLOQUEADO' A 'LISTO' 13-CICLOS");
				listaProcesosBloqueado.remove(buscarIndice(ID, listaProcesosBloqueado));
			}
		}else {
			if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getCiclosEnBloqueo() == 5) {
				listaProcesosListo.add(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)));
				System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'BLOQUEADO' A 'LISTO' 27-CICLOS");
				listaProcesosBloqueado.remove(buscarIndice(ID, listaProcesosBloqueado));
			}
		}
	}
	
	private boolean estadoEjecucion_Listo(AtributosProceso proceso) { //Cambio de estado de Ejecucion a listo, asumiento que los procesos entran a lista ejecucion por prioridad
		if (proceso.getCiclosEjecucion() == ciclosDelProcesador) {
			listaProcesosListo.add(listaProcesosEjecutando.get(buscarProceso(proceso.getIdentificadorProceso(), listaProcesosEjecutando)));
			listaProcesosEjecutando.remove(proceso);
			System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(proceso.getIdentificadorProceso(), listaProcesosBloqueado)).toString() + "----> 'EJECUCION' A 'LISTO'");
			return true;
		}
		return false;
	}
	
	private boolean EstadoEjecucion_Terminado(AtributosProceso proceso) {//funcion  que añade procesos a la lista de terminados y retorna un verdadero si el proceso se termina y falso si no.
		if(proceso.getInstruccionesLeidas() == Integer.parseInt(proceso.getCantidadInstrucciones())) {//compara las instrucciones leídas con las instrucciones totales para determinar si el proceso ha finalizado
			listaProcesosTerminado.add(proceso);
			System.out.println("el proceso " + proceso.toString() + " ha FINALIZADO");
			listaProcesosEjecutando.remove(proceso);
			return true;
		}
		return false;
	}
	
	private int buscarIndice (String ID, ArrayList<AtributosProceso> lista) {//funcion que nos retornará el indice de la lista donde está el proceso con el respectivo ID
		for(int i=0 ; i<lista.size() ; i++) {
			if(lista.get(i).getIdentificadorProceso() == ID) {
				return i;
			}
		}
		return -1;
	}
	
	private int buscarPrioridad(int Prioridad, ArrayList<AtributosProceso> Lista) {//funcion que retorna el respectivo indice del proceso con la prioridad pedida, de la lista enviada
		for(int i=0 ; i<Lista.size() ; i++) {
			if(Lista.get(i).getPrioridadProceso() == Prioridad) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean hayPrioridad (int Prioridad, ArrayList<AtributosProceso> Lista) {//funcion que corrobora si existe entre los elementos de la lista u proceso con ese grado de prioridad
		for(int i=0 ; i<Lista.size() ; i++) {
			if(Lista.get(i).getPrioridadProceso() == Prioridad) {
				return true;
			}
		}
		return false;
	}
	
	private int buscarProceso(String ID, ArrayList<AtributosProceso> Lista) {
		for(int i=0 ; i<Lista.size() ; i++ ) {
			if(Lista.get(i).getIdentificadorProceso() == ID) {
				return i;
			}
		}
		return -1;
	}
//----------------------------------------------------------
	public void cicloEjecucion () {
		int n = 0;
		int i = -1;
		while (n < ciclosDelProcesador) {//cantidad de ciclos dados por el usuario, instrucciones que se leerán a la vez
			
			n++;
			
			System.out.println("Instruccion #"+ n);
			
			if((i = buscarPrioridad(1, listaProcesosEjecutando)) != -1){//lo importante es buscar la prioridad mayor y ejecutar ese proceso
				if(EstadoEjecucion_Terminado(listaProcesosEjecutando.get(i)) != true) {//determinará si el proceso ha finalizado
					// determinará si el proceso se bloqueará
					if(estadoEjecucion_Bloqueado(listaProcesosEjecutando.get(i).getInstrucionBloqueo(), listaProcesosEjecutando.get(i).getIdentificadorProceso()) != true ) {
						if(estadoEjecucion_Listo(listaProcesosEjecutando.get(i)) != true ) {
							listaProcesosEjecutando.get(i).SetCiclosEjecucion();
							listaProcesosEjecutando.get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
							System.out.println("se leyó la instruccion: " + listaProcesosEjecutando.get(i).getInstruccionesLeidas() + " del proceso: " + listaProcesosEjecutando.get(i).toString());
						}
					}
				}
			}else {
				if((i = buscarPrioridad(2, listaProcesosEjecutando)) != -1){
					if(EstadoEjecucion_Terminado(listaProcesosEjecutando.get(i)) != true ) {
						if(estadoEjecucion_Bloqueado(listaProcesosEjecutando.get(i).getInstrucionBloqueo(), listaProcesosEjecutando.get(i).getIdentificadorProceso()) != true ) {
							if(estadoEjecucion_Listo(listaProcesosEjecutando.get(i)) != true ) {
								listaProcesosEjecutando.get(i).SetCiclosEjecucion();
								listaProcesosEjecutando.get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
								System.out.println("se leyó la instruccion: " + listaProcesosEjecutando.get(i).getInstruccionesLeidas() + " del proceso: " + listaProcesosEjecutando.get(i).toString());
							}
						}
					}
				}else {
					if((i = buscarPrioridad(3, listaProcesosEjecutando)) != -1){
						if(EstadoEjecucion_Terminado(listaProcesosEjecutando.get(i)) != true) {
							if(estadoEjecucion_Bloqueado(listaProcesosEjecutando.get(i).getInstrucionBloqueo(), listaProcesosEjecutando.get(i).getIdentificadorProceso()) != true ) {
								if(estadoEjecucion_Listo(listaProcesosEjecutando.get(i)) != true ) {
									listaProcesosEjecutando.get(i).SetCiclosEjecucion();
									listaProcesosEjecutando.get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
									System.out.println("se leyó la instruccion: " + listaProcesosEjecutando.get(i).getInstruccionesLeidas() + " del proceso: " + listaProcesosEjecutando.get(i).toString());
								}
							}
						}
					}else {
						estadoListo_Ejecucion();
					}
				}
			}
			
			
			for(int j=0 ; j<listaProcesosBloqueado.size() ; j++) {
				listaProcesosBloqueado.get(j).setCiclosEnBloqueo(listaProcesosBloqueado.get(j).getCiclosEnBloqueo()+1);
				estadoBloqueado_Listo(listaProcesosBloqueado.get(j).getIdentificadorProceso());
			}
			
			
		
		}
	}
//--------------------------------------------------------	
	
}
