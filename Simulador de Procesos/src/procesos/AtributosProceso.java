package procesos;
import java.util.Random;

public class AtributosProceso {
	//Declaracion instancia de variables que definen los atributos de un proceso.
	private String identificadorProceso;
	private int estadoProceso; //Variable 
	private int prioridadProceso;
	private String cantidadInstrucciones;
	private String instrucionBloqueo;
	private int eventoBloqueo;
	private int instruccionActual = 0;
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
	/* Me parece que el Id debe tener 4 espacios de memoria comprendidos entre 0-9 cada espacio, Ejemplo:
		public void setIdentificadorProceso(0-9,0-9,0-9,0-9) {
			concatenaciÃ²n = 0-9,0-9,0-9,0-9; --> 0987
			this.identificadorProceso = concatenacion;
		}
	?????
	
	Este recibiria valores comprendidos desde 0 hasta 9999
	si el aleatorio me da 0 solo seria un valor?
	Identificador=0; pero deberia ser 0000
	??
	*/
	
	/*
	 * Lo oculté porque quiero probar las listas, en caso de no funcionar mi idea, volvemos a esto ok?
	 * 
	public void setIdentificadorProceso(int identificadorProceso) {
		if(identificadorProceso<=9) {
			this.identificadorProceso = "000"+Integer.toString(identificadorProceso);
		}else if(identificadorProceso>9 && identificadorProceso<=99) {
			this.identificadorProceso = "00"+Integer.toString(identificadorProceso);
		}else if(identificadorProceso>99 && identificadorProceso<=999) {
			this.identificadorProceso = "0"+Integer.toString(identificadorProceso);
		}else
			this.identificadorProceso = Integer.toString(identificadorProceso);
	}
*/
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
		setEstadoProceso(aleatorio.nextInt(6));
		setPrioridadProceso(aleatorio.nextInt(4));
<<<<<<< HEAD
		setCantidadInstrucciones(CantidadInstrucciones);
		setInstrucionBloqueo(aleatorio.nextInt(CantidadInstrucciones));//getCantidadInstrucciones para que la instruccion de bloqueo no sea mayor a la cantidad de instrucciones totales, lo cual no seria correcto
=======
		setCantidadInstrucciones(aleatorio.nextInt(1000));
		setInstrucionBloqueo(aleatorio.nextInt(Integer.valueOf(getCantidadInstrucciones())));//getCantidadInstrucciones para que la instruccion de bloqueo no sea mayor a la cantidad de instrucciones totales, lo cual no seria correcto
>>>>>>> 00d9cff461fb7cca20080782a2d18cc95441f847
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
