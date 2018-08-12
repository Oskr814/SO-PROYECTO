package procesos;
import java.io.FileOutputStream;
/*import java.io.FileReader;
import java.io.FileWriter;*/
import java.util.ArrayList;
import java.util.Random;
/*import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;*/

import Implementacion.Ejecucion;

public class Escritura_y_Lectura {
	
	private ArrayList<String> listaID_Utilizables = new ArrayList<String>(10000);
	private ArrayList<AtributosProceso> listaProcesosListo  = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosEjecutando = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosBloqueado = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosNuevo = new ArrayList<AtributosProceso>(10);
	private ArrayList<AtributosProceso> listaProcesosTerminado = new ArrayList<AtributosProceso>();
	public ArchivoTexto archivo = new ArchivoTexto();
	private Random random = new Random();
	private Ejecucion ejecucion = new Ejecucion();
	
	FileOutputStream estadoProcesoNuevo = null;
	FileOutputStream estadoProcesoListo= null;
	FileOutputStream estadoProcesoEjecutado = null;
	FileOutputStream estadoProcesoBloqueado = null;
	FileOutputStream estadoProcesoTerminado = null;
	
	
	private int maximoProcesos = 5;
	public int ciclosDelProcesador = 10; //Esta variable la defino de manera constante para fines de codificacion, deberia ser especificada por el usuario.
	
	public Escritura_y_Lectura() {
		
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
	
	public Ejecucion getEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(Ejecucion ejecucion) {
		this.ejecucion = ejecucion;
	}
	
	public void procesoNuevo () throws InterruptedException {
		while(listaProcesosNuevo.size() < maximoProcesos) {
			int IndiceUtilizado = random.nextInt(listaID_Utilizables.size());//sacamos el numero al azar para la creacion de procesos, que será el indice de la lista disponible
			listaProcesosNuevo.add(new AtributosProceso(listaID_Utilizables.get(IndiceUtilizado)));//sacamos el String, con las regulaciones necesarias para mandarlo como parametro al constructor de "AtributosProceso"
			//actualizarTXT("procesos/Nuevo/estadoProcesoNuevo.txt", listaProcesosNuevo);
			//System.out.println("se ha creado un proceso: " + listaProcesosNuevo.get(buscarProceso(listaID_Utilizables.get(IndiceUtilizado), listaProcesosNuevo)).toString() + "---> NUEVO");
			ejecucion.mostrarInformacionEstados( listaProcesosNuevo.get(buscarProceso(listaID_Utilizables.get(IndiceUtilizado), listaProcesosNuevo)).toString(), "procesoNuevo" );
			listaID_Utilizables.remove(IndiceUtilizado);//Eliminamos el item para evitar que se repita nuestro ID que debe ser único
			Thread.sleep(1000);
		}
	}
	
	public void estadoNuevo_Listo() throws InterruptedException{//funcion que pasará un maximo de 10 procesos a la lista "LISTO"
		int i = -1;//indice inicializadop en -1 para que genero un error si no se ha hecho asignacion
		while(listaProcesosListo.size() < maximoProcesos) {//condicion para asignar 10 procesos como mucho a la lista "LISTO"
			if (hayPrioridad(1, listaProcesosNuevo)) {//se buscarán los procesos de mayor prioridad y si existen entrará al 'for'
				i = buscarPrioridad(1, listaProcesosNuevo);//indice donde está el proceso de 'x' prioridad que se moverá que se moverá
				listaProcesosListo.add(listaProcesosNuevo.get(i));//Añade a la lista"LISTO" el proceso desde "NUEVO"
				listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);
				//System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'NUEVO' a 'LISTO'");
				ejecucion.mostrarInformacionEstados( listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString(), "estadoNuevo_Listo" );
				listaProcesosNuevo.remove(i);//removemos el proceso que se trasladó
				i=-1;//reiniciamos el indice para no
				Thread.sleep(1000);	
			}else {
				if (hayPrioridad(2, listaProcesosNuevo)) {//se buscarán los procesos de media prioridad y si existen entrará al 'for' 
					i = buscarPrioridad(2, listaProcesosNuevo);
					listaProcesosListo.add(listaProcesosNuevo.get(i));
					listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);;
					//System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'NUEVO' a 'LISTO'");
					ejecucion.mostrarInformacionEstados( listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString(), "estadoNuevo_Listo" );
					listaProcesosNuevo.remove(i);
					i=-1;
					Thread.sleep(1000);	
				}else {
					if (hayPrioridad(3, listaProcesosNuevo)) {
						i = buscarPrioridad(3, listaProcesosNuevo);
						listaProcesosListo.add(listaProcesosNuevo.get(i));
						listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);;
						//System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'NUEVO' a 'LISTO'");
						ejecucion.mostrarInformacionEstados( listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString(), "estadoNuevo_Listo" );
						listaProcesosNuevo.remove(i);
						i=-1;
						Thread.sleep(1000);	
					}else {
						procesoNuevo();
					}
				}
			}
		}
		
		
	}
	
	public void estadoListo_Ejecucion() throws InterruptedException {//funcion que pasará procesos desde "LISTO" a "EJECUCION"
		int i=-1; //indice del proceso en la lista "LISTO"
		while (listaProcesosEjecutando.size() < maximoProcesos) {//cantidad de procesos que estarán el la lista de "EJECUCION"
			if (hayPrioridad(1, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
				i = buscarPrioridad(1, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
				listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
				listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).setEstadoProceso(2);;
				//System.out.println("se ha pasado el proceso: " +  listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString() + "----> 'LISTO' a 'EJECUCION'");
				ejecucion.mostrarInformacionEstados(listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString(), "estadoListo_Ejecucion");
				listaProcesosListo.remove(i);//removemos el proceso de LISTO
				i = -1;//reiniciamos el contador
				Thread.sleep(1000);
			}else {
				if (hayPrioridad(2, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
					i = buscarPrioridad(2, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
					listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
					listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).setEstadoProceso(2);;
					//System.out.println("se ha pasado el proceso: " +  listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString() + "----> 'LISTO' a 'EJECUCION'");
					ejecucion.mostrarInformacionEstados( listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString(), "estadoListo_Ejecucion");
					listaProcesosListo.remove(i);//removemos el proceso de LISTO
					i = -1;//reiniciamos el contador;
					Thread.sleep(1000);
				}else {
					if (hayPrioridad(3, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
						i = buscarPrioridad(3, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
						listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
						listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).setEstadoProceso(2);;
						//System.out.println("se ha pasado el proceso: " +  listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString() + "----> 'LISTO' a 'EJECUCION'");
						ejecucion.mostrarInformacionEstados( listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString(), "estadoListo_Ejecucion");
						listaProcesosListo.remove(i);//removemos el proceso de LISTO
						i = -1;//reiniciamos el contador
						Thread.sleep(1000);
					}else {
						estadoNuevo_Listo();
					}
				}
			}
		}
	}
		
	private boolean estadoEjecucion_Bloqueado (String instruccionBloqueo, String ID) throws InterruptedException {//funcion que revisará si el proceso llego a la instruccion de bloqueo y hace la transicion 
		if(listaProcesosEjecutando.get(buscarIndice(ID, listaProcesosEjecutando)).getInstruccionesLeidas() == Integer.parseInt(instruccionBloqueo)) {
			listaProcesosBloqueado.add(listaProcesosEjecutando.get(buscarIndice(ID, listaProcesosEjecutando)));
			listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).setEstadoProceso(3);
			//System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'EJECUCION' a 'BLOQUEADO'");
			ejecucion.mostrarInformacionEstados( listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString(), "estadoEjecucion_Bloqueado" );
			listaProcesosEjecutando.remove(buscarIndice(ID, listaProcesosEjecutando));
			Thread.sleep(1000);
			return true;
		}else {
			return false;
		}
	}
	
	public void estadoBloqueado_Listo(String ID) throws InterruptedException {//Función que determina si un proceso cumplió con los ciclos respectivos para estar en la lista de "BLOQUEO" y los pasa a "LISTO"
		if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getEventoBloqueo() == 3) {
			if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getCiclosEnBloqueo() == 13) {
				listaProcesosListo.add(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)));
				listaProcesosListo.get(buscarIndice(ID, listaProcesosListo)).setEstadoProceso(1);
				//System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'BLOQUEADO' A 'LISTO' 13-CICLOS");
				ejecucion.mostrarInformacionEstados( listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString(), "estadoBloqueado_Listo" );
				listaProcesosBloqueado.remove(buscarIndice(ID, listaProcesosBloqueado));
				Thread.sleep(1000);
			}
		}else {
			if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getCiclosEnBloqueo() == 27) {
				listaProcesosListo.add(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)));
				listaProcesosListo.get(buscarIndice(ID, listaProcesosListo)).setEstadoProceso(1);
				//System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'BLOQUEADO' A 'LISTO' 27-CICLOS");
				ejecucion.mostrarInformacionEstados( listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString(), "estadoBloqueado_Listo" );
				listaProcesosBloqueado.remove(buscarIndice(ID, listaProcesosBloqueado));
				Thread.sleep(1000);
			}
		}
	}
	
	private boolean estadoEjecucion_Listo(AtributosProceso proceso) throws InterruptedException { //Cambio de estado de Ejecucion a listo, asumiento que los procesos entran a lista ejecucion por prioridad
		if (proceso.getCiclosEjecucion() == ciclosDelProcesador) {
			proceso.SetCiclosEjecucion(0);
				listaProcesosListo.add(listaProcesosEjecutando.get(buscarProceso(proceso.getIdentificadorProceso(), listaProcesosEjecutando)));
				listaProcesosListo.get(buscarProceso(proceso.getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);
				listaProcesosEjecutando.remove(proceso);
				//System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarIndice(proceso.getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'EJECUCION' A 'LISTO'");
				ejecucion.mostrarInformacionEstados( listaProcesosListo.get(buscarIndice(proceso.getIdentificadorProceso(), listaProcesosListo)).toString(), "estadoEjecucion_Listo" );
				Thread.sleep(1000);
				return true;
			}
		return false;
	}
	
	private boolean EstadoEjecucion_Terminado(AtributosProceso proceso) throws InterruptedException {//funcion  que añade procesos a la lista de terminados y retorna un verdadero si el proceso se termina y falso si no.
		//System.out.println(proceso.getInstruccionesLeidas()+"//"+proceso.getCantidadInstrucciones());
		ejecucion.mostrarInformacionEstados( proceso.getInstruccionesLeidas()+"//"+proceso.getCantidadInstrucciones(), "ProcesoResume" );
		if(proceso.getInstruccionesLeidas() == Integer.parseInt(proceso.getCantidadInstrucciones())) {//compara las instrucciones leídas con las instrucciones totales para determinar si el proceso ha finalizado
			listaProcesosTerminado.add(proceso);
			listaProcesosTerminado.get(buscarProceso(proceso.getIdentificadorProceso(), listaProcesosTerminado)).setEstadoProceso(4);
			//System.out.println("el proceso " + proceso.toString() + " ha FINALIZADO");
			ejecucion.mostrarInformacionEstados( proceso.toString(), "EstadoEjecucion_Terminado" );
			listaProcesosEjecutando.remove(proceso);
			Thread.sleep(1000);
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
	public void cicloEjecucion () throws InterruptedException {
		int n = 0;
		int i = -1;
		while (n < ciclosDelProcesador) {//cantidad de ciclos dados por el usuario, instrucciones que se leerán a la vez
			
			n++;
			
			procesoNuevo();
			estadoNuevo_Listo();
			estadoListo_Ejecucion();
			
			
			//System.out.println("Instruccion #"+ n);
			Thread.sleep(1000);
			ejecucion.mostrarInformacionEstados( String.valueOf(n), "Instruccion" );
			
			if((i = buscarPrioridad(1, listaProcesosEjecutando)) != -1){//lo importante es buscar la prioridad mayor y ejecutar ese proceso
				if(EstadoEjecucion_Terminado(listaProcesosEjecutando.get(i)) != true) {//determinará si el proceso ha finalizado
					if(estadoEjecucion_Bloqueado(listaProcesosEjecutando.get(i).getInstrucionBloqueo(), listaProcesosEjecutando.get(i).getIdentificadorProceso()) != true ) {//determinará si la ultima instruccion es una instruccion de bloqueo
						if(estadoEjecucion_Listo(listaProcesosEjecutando.get(i)) != true ) {//determinará si se cumplieron los ciclos dados por el usuario en este proceso
							listaProcesosEjecutando.get(i).SetCiclosEjecucion();//aumentará la variable cada que se lean las instrucciones
							listaProcesosEjecutando.get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
							//System.out.println("se leyó la instruccion: " + listaProcesosEjecutando.get(i).getInstruccionesLeidas() + " del proceso: " + listaProcesosEjecutando.get(i).toString());
							ejecucion.mostrarInformacionEstados( String.valueOf(listaProcesosEjecutando.get(i).getInstruccionesLeidas()), "instruccionLeida" );
							ejecucion.mostrarInformacionEstados( listaProcesosEjecutando.get(i).toString(), "proceso");
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
								//System.out.println("se leyó la instruccion: " + listaProcesosEjecutando.get(i).getInstruccionesLeidas() + " del proceso: " + listaProcesosEjecutando.get(i).toString());
								ejecucion.mostrarInformacionEstados( String.valueOf(listaProcesosEjecutando.get(i).getInstruccionesLeidas()), "instruccionLeida" );
								ejecucion.mostrarInformacionEstados( listaProcesosEjecutando.get(i).toString(), "proceso");
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
									//System.out.println("se leyó la instruccion: " + listaProcesosEjecutando.get(i).getInstruccionesLeidas() + " del proceso: " + listaProcesosEjecutando.get(i).toString());
									ejecucion.mostrarInformacionEstados( String.valueOf(listaProcesosEjecutando.get(i).getInstruccionesLeidas()), "instruccionLeida" );
									ejecucion.mostrarInformacionEstados(  listaProcesosEjecutando.get(i).toString(), "proceso" );
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

	public void actualizarInformacion() {
		if(listaProcesosNuevo.size()>0)
		archivo.escrbirArchivoPlanoEstado(listaProcesosNuevo);
		if(listaProcesosListo.size()>0)
		archivo.escrbirArchivoPlanoEstado(listaProcesosListo);
		if(listaProcesosEjecutando.size()>0)
		archivo.escrbirArchivoPlanoEstado(listaProcesosEjecutando);
		if(listaProcesosBloqueado.size()>0)
		archivo.escrbirArchivoPlanoEstado(listaProcesosBloqueado);
		if(listaProcesosTerminado.size()>0)
		archivo.escrbirArchivoPlanoEstado(listaProcesosTerminado);
	}
	
	
//--------------------------------------------------------	

}
