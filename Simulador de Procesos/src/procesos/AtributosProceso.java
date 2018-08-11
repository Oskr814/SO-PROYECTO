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
	private int ciclosEjecucion = 0;
	private int ciclosEnBloqueo = 0;//lleva la cuenta de cuantos ciclos tiene de estar el "bloqueo"
	
	//Declaracion instancia de variable para generar numeros aleatorios
	private Random aleatorio = new Random();
	
	public AtributosProceso(String ID){
		this.identificadorProceso = ID;//Que ID sea enviado para llevar control de los ID utilizados y evitar repeticiones
		nuevoProceso();//Creacion del proceso al instaciar la clase
	}
	
	public String getIdentificadorProceso() {
		return identificadorProceso;
	}
	
	public void SetCiclosEjecucion() {
		ciclosEjecucion++;
	}
	
	public void SetCiclosEjecucion(int ciclosEjecucion) {
		this.ciclosEjecucion= ciclosEjecucion;
	}
	
	public int getCiclosEjecucion() {
		return ciclosEjecucion;
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

	public int getInstruccionesLeidas() {
		return instruccionesLeidas;
	}

	public void setInstruccionesLeidas() {
		this.instruccionesLeidas++;
	}

	public int getCiclosEnBloqueo() {
		return ciclosEnBloqueo;
	}

	public void setCiclosEnBloqueo(int CiclosEnBloqueo) {
		this.ciclosEnBloqueo = CiclosEnBloqueo;
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
		int CantidadInstrucciones = aleatorio.nextInt(19)+1;// atributo para que no entre en confilicto el "setCantidadInstrucciones" ya que le estamos enviando un String(que no se puede castear a INTEGER).
		setEstadoProceso(0);
		setPrioridadProceso(aleatorio.nextInt(3) + 1);

		setCantidadInstrucciones(CantidadInstrucciones);
		setInstrucionBloqueo(aleatorio.nextInt(Integer.parseInt(getCantidadInstrucciones())));//getCantidadInstrucciones para que la instruccion de bloqueo no sea mayor a la cantidad de instrucciones totales, lo cual no seria correcto

		//setCantidadInstrucciones(aleatorio.nextInt(1000));
		//setInstrucionBloqueo(aleatorio.nextInt(Integer.valueOf(getCantidadInstrucciones())));//getCantidadInstrucciones para que la instruccion de bloqueo no sea mayor a la cantidad de instrucciones totales, lo cual no seria correcto
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
