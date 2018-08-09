package procesos;
import java.util.Random;

public class AtributosProceso {
	//Declaracion instancia de variables que definen los atributos de un proceso.
	private String identificadorProceso;
	private int estadoProceso; 
	private int prioridadProceso;
	private int instruccionesLeidas = 0;
	private String cantidadInstrucciones;
	private String instrucionBloqueo;
	private int eventoBloqueo;
	private int instruccionActual = 0;
	private int ciclosBloqueoRecorrido = 0;//lleva la cuenta de cuantos ciclos tiene de estar el "bloqueo"
	
	//Declaracion instancia de variable para generar numeros aleatorios
	private Random aleatorio = new Random();
	
	public AtributosProceso(String ID){
		this.identificadorProceso = ID;//Que ID sea enviado para llevar control de los ID utilizados y evitar repeticiones
		nuevoProceso();//Creacion del proceso al instaciar la clase
	}
	
	public String getIdentificadorProceso() {
		return identificadorProceso;
	}
	
	public void SetInstruccionActual() {
		instruccionActual++;
	}
	
	public int getInstruccionActual() {
		return instruccionActual;
	}
	
	public int getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(int estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public int getPrioridadProceso() {
		return prioridadProceso;
	}

	public void setPrioridadProceso(int prioridadProceso) {
		this.prioridadProceso = prioridadProceso;
	}

	public String getCantidadInstrucciones() {
		return cantidadInstrucciones;
	}

	public void setCantidadInstrucciones(int cantidadInstrucciones) {
		if(cantidadInstrucciones<=9) {
			this.cantidadInstrucciones = "00"+Integer.toString(cantidadInstrucciones);
		}else if(cantidadInstrucciones>9 && cantidadInstrucciones<=99) { 
			this.cantidadInstrucciones = "0"+Integer.toString(cantidadInstrucciones);
		}else
			this.cantidadInstrucciones = Integer.toString(cantidadInstrucciones);
		
	}

	public String getInstrucionBloqueo() {
		return instrucionBloqueo;
	}

	public void setInstrucionBloqueo(int instrucionBloqueo) {
		if(instrucionBloqueo<=9) {
			this.instrucionBloqueo = "00"+Integer.toString(instrucionBloqueo);
		}else if(instrucionBloqueo>9 && instrucionBloqueo<=99) {
			this.instrucionBloqueo = "0"+Integer.toString(instrucionBloqueo);
		}else
			this.instrucionBloqueo = Integer.toString(instrucionBloqueo);
	}

	public int getEventoBloqueo() {
		return eventoBloqueo;
	}

	public void setEventoBloqueo(int eventoBloqueo) {
		this.eventoBloqueo = eventoBloqueo;
	}

	public void nuevoProceso() {
		int CantidadInstrucciones = aleatorio.nextInt(10000);// atributo para que no entre en confilicto el "setCantidadInstrucciones" ya que le estamos enviando un String(que no se puede castear a INTEGER).
		setEstadoProceso(0);
		setPrioridadProceso(aleatorio.nextInt(3) + 1);

		setCantidadInstrucciones(CantidadInstrucciones);
		setInstrucionBloqueo(aleatorio.nextInt(CantidadInstrucciones));//getCantidadInstrucciones para que la instruccion de bloqueo no sea mayor a la cantidad de instrucciones totales, lo cual no seria correcto

		setCantidadInstrucciones(aleatorio.nextInt(1000));
		setInstrucionBloqueo(aleatorio.nextInt(Integer.valueOf(getCantidadInstrucciones())));//getCantidadInstrucciones para que la instruccion de bloqueo no sea mayor a la cantidad de instrucciones totales, lo cual no seria correcto
		switch(aleatorio.nextInt(2)){
			case 0:
				setEventoBloqueo(3);
				break;
			case 1:
				setEventoBloqueo(5);
				break;
		}
		
	}
	
	public String toString() {
		return getIdentificadorProceso()+"/"+getEstadoProceso()+"/"+getPrioridadProceso()+"/"+getCantidadInstrucciones()+"/"+getInstrucionBloqueo()+"/"+getEventoBloqueo();
	}
	
}
