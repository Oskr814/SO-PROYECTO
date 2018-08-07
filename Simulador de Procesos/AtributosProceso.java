package procesos;
import java.util.Random;

public class AtributosProceso {
	//Declaracion instancia de variables que definen los atributos de un proceso.
	private int identificadorProceso;
	private int estadoProceso; //Variable 
	private int prioridadProceso;
	private int cantidadInstrucciones;
	private int instrucionBloqueo;
	private int eventoBloqueo;
	//Declaracion instancia de variable para generar numeros aleatorios
	private Random aleatorio = new Random();
	
	
	public int getIdentificadorProceso() {
		return identificadorProceso;
	}
	/* Me parece que el Id debe tener 4 espacios de memoria comprendidos entre 0-9 cada espacio, Ejemplo:
		public void setIdentificadorProceso(0-9,0-9,0-9,0-9) {
			concatenaciòn = 0-9,0-9,0-9,0-9; --> 0987
			this.identificadorProceso = concatenacion;
		}
	?????
	
	Este recibiria valores comprendidos desde 0 hasta 9999
	si el aleatorio me da 0 solo seria un valor?
	Identificador=0; pero deberia ser 0000
	??
	*/
	public void setIdentificadorProceso(int identificadorProceso) {
		this.identificadorProceso = identificadorProceso;
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

	public int getCantidadInstrucciones() {
		return cantidadInstrucciones;
	}

	public void setCantidadInstrucciones(int cantidadInstrucciones) {
		this.cantidadInstrucciones = cantidadInstrucciones;
	}

	public int getInstrucionBloqueo() {
		return instrucionBloqueo;
	}

	public void setInstrucionBloqueo(int instrucionBloqueo) {
		this.instrucionBloqueo = instrucionBloqueo;
	}

	public int getEventoBloqueo() {
		return eventoBloqueo;
	}

	public void setEventoBloqueo(int eventoBloqueo) {
		this.eventoBloqueo = eventoBloqueo;
	}

	public void nuevoProceso() {
		setIdentificadorProceso(aleatorio.nextInt(10000));
		setEstadoProceso(aleatorio.nextInt(6));
		setPrioridadProceso(aleatorio.nextInt(4));
		setCantidadInstrucciones(aleatorio.nextInt(1000));
		setInstrucionBloqueo(aleatorio.nextInt(getCantidadInstrucciones());//getCantidadInstrucciones para que la instruccion de bloqueo no sea mayor a la cantidad de instrucciones totales, lo cual no seria correcto
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
