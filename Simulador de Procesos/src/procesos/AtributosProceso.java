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
	//Declaracion instancia de variable para generar numeros aleatorios
	private Random aleatorio = new Random();
	
	
	public String getIdentificadorProceso() {
		return identificadorProceso;
	}

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
		setIdentificadorProceso(aleatorio.nextInt(10000));
		setEstadoProceso(aleatorio.nextInt(6));
		setPrioridadProceso(aleatorio.nextInt(4));
		setCantidadInstrucciones(aleatorio.nextInt(1000));
		setInstrucionBloqueo(aleatorio.nextInt(1000));
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
		return getIdentificadorProceso()+"/"+getEstadoProceso()+"/"+getPrioridadProceso()+"/"+getInstrucionBloqueo()+"/"+getEventoBloqueo();
	}
	
}
