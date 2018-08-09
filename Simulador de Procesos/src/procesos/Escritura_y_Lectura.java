package procesos;
import java.util.ArrayList;
import java.util.Random;

public class Escritura_y_Lectura {
	
	private ArrayList<String> listaID_Utilizables = new ArrayList<String>(10000);
	//private ArrayList<AtributosProceso> listaProcesos = new ArrayList<AtributosProceso>();
	//private ArrayList listaProcesosPrioridadAlta = new ArrayList<>();//almacena los indices de los procesos de alta prioridad que están en "listaProcesos".
	//private ArrayList listaProcesosPrioridadMedia;//almacena los indices de los procesos de media prioridad que están en "listaProcesos".
	//private ArrayList listaProcesosPrioridadBaja;//almacena los indices de los procesos de baja prioridad que están en "listaProcesos".
	private ArrayList<AtributosProceso> listaProcesosListo  = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosEjecutando = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosBloqueado = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosNuevo = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosSaliente = new ArrayList<AtributosProceso>();
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
	
	public void procesoNuevo() {
		int IndiceUtilizado = random.nextInt(listaID_Utilizables.size());//sacamos el numero al azar para la creacion de procesos, que será el indice de la lista disponible
		int ID_proceso =0;
		listaProcesosNuevo.add(new AtributosProceso(listaID_Utilizables.get(IndiceUtilizado)));//sacamos el String, con las regulaciones necesarias para mandarlo como parametro al constructor de "AtributosProceso"
		
		for(int i=0 ; i<listaProcesosNuevo.size() ; i++) {
			if(listaProcesosNuevo.get(i).getIdentificadorProceso() == listaID_Utilizables.get(IndiceUtilizado)) {
				ID_proceso = i;
			}
		}
		
		
		listaID_Utilizables.remove(IndiceUtilizado);//Eliminamos el item para evitar que se repita nuestro ID que debe ser único	
		if(listaProcesosListo.isEmpty()) {//para cuando sea el primer Proceso creado.
			listaProcesosListo.add(listaProcesosNuevo.get(ID_proceso));//mandamos el proceso a la lista de "Listos"
			listaProcesosNuevo.remove(IndiceUtilizado);//eliminamos el proceso que pasó a "listos".
		}
		
	}
	
	/*
	public void ordenarPrioridades() {//clasificamos los procesos.
		for(int i=0 ; i<listaProcesosNuevo.size() ; i++) {
			if(listaProcesosNuevo.get(i).getPrioridadProceso() == 1) { 
				listaProcesosPrioridadAlta.add(i);
			}else { 
				if(listaProcesosNuevo.get(i).getPrioridadProceso() == 2) 
					listaProcesosPrioridadMedia.add(i);
				else
					listaProcesosPrioridadBaja.add(i);	
			}
		}
	}
	*/
	
	public void procesoListo() {
		
	}
	
	

}
