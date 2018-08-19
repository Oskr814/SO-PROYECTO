package Implementacion;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import procesos.ArchivoTexto;
import procesos.AtributosProceso;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Ejecucion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtAInformacionEstadoNuevo;
	private JTextArea txtAInformacionEstadoListo;
	private JTextArea txtAInformacionEstadoEjecucion;
	private JTextArea txtAInformacionEstadoBloqueado;
	private JTextArea txtAInformacionEstadoTerminado;
	private JLabel lblRespuestaCicloDelProcesador;
	private JLabel lblRespuestaNumeroDeInstruccion;
	private JLabel lblRespuestaInstruccionLeida;
	private JLabel lblRespuestaProceso;
	private JLabel lblRespuestaEstadoActual;
	private JLabel lblObservacion;
	private ArrayList<String> listaID_Utilizables = new ArrayList<String>(10000);
	private ArrayList<AtributosProceso> listaProcesosListo  = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosEjecutando = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosBloqueado = new ArrayList<AtributosProceso>();
	private ArrayList<AtributosProceso> listaProcesosNuevo = new ArrayList<AtributosProceso>(10);
	private ArrayList<AtributosProceso> listaProcesosTerminado = new ArrayList<AtributosProceso>();
	public ArchivoTexto archivo = new ArchivoTexto();
	private Random random = new Random();
	JButton btnEmpezar = null;
	private JTextField txtCicloProcesador;
	private int temporizador = 5;
	private int maximoProcesos = 10;
	private int ciclosDelProcesador = 0; //Esta variable la defino de manera constante para fines de codificacion, deberia ser especificada por el usuario.
	private int cicloActual = 0;
	
	
	public int getCiclosDelProcesador() {
		return ciclosDelProcesador;
	}

	public Ejecucion() {
		inicializarVentana();
		
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
		btnEmpezar = new JButton("Empezar");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n=0;
				String ciclos = txtCicloProcesador.getText();
				ciclosDelProcesador =  Integer.parseInt(ciclos);
				
			}
		});

		inicializarElementos();
	}
	
	public void procesoNuevo () throws InterruptedException {
		while(listaProcesosNuevo.size() < maximoProcesos) {
			int IndiceUtilizado = random.nextInt(listaID_Utilizables.size());//sacamos el numero al azar para la creacion de procesos, que será el indice de la lista disponible
			listaProcesosNuevo.add(new AtributosProceso(listaID_Utilizables.get(IndiceUtilizado)));//sacamos el String, con las regulaciones necesarias para mandarlo como parametro al constructor de "AtributosProceso"
			System.out.println("se ha creado un proceso: " + listaProcesosNuevo.get(buscarProceso(listaID_Utilizables.get(IndiceUtilizado), listaProcesosNuevo)).toString() + "---> NUEVO");
			mostrarInformacionEstados();
			listaID_Utilizables.remove(IndiceUtilizado);//Eliminamos el item para evitar que se repita nuestro ID que debe ser único
			Thread.sleep(100);
		}
	}
	
	public void estadoNuevo_Listo() throws InterruptedException{//funcion que pasará un maximo de 10 procesos a la lista "LISTO"
		int i = -1;//indice inicializadop en -1 para que genero un error si no se ha hecho asignacion
		while(listaProcesosListo.size() < maximoProcesos) {//condicion para asignar 10 procesos como mucho a la lista "LISTO"
			if (hayPrioridad(1, listaProcesosNuevo)) {//se buscarán los procesos de mayor prioridad y si existen entrará al 'for'
				i = buscarPrioridad(1, listaProcesosNuevo);//indice donde está el proceso de 'x' prioridad que se moverá que se moverá
				listaProcesosListo.add(listaProcesosNuevo.get(i));//Añade a la lista"LISTO" el proceso desde "NUEVO"
				listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);
				System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'NUEVO' a 'LISTO'");
				listaProcesosNuevo.remove(i);//removemos el proceso que se trasladó
				mostrarInformacionEstados();
				procesoNuevo();
				i=-1;//reiniciamos el indice para no
				//Thread.sleep(1000);	
			}else {
				if (hayPrioridad(2, listaProcesosNuevo)) {//se buscarán los procesos de media prioridad y si existen entrará al 'for' 
					i = buscarPrioridad(2, listaProcesosNuevo);
					listaProcesosListo.add(listaProcesosNuevo.get(i));
					listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);;
					System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'NUEVO' a 'LISTO'");
					
					listaProcesosNuevo.remove(i);
					mostrarInformacionEstados();
					procesoNuevo();
					i=-1;
					//Thread.sleep(1000);	
				}else {
					if (hayPrioridad(3, listaProcesosNuevo)) {
						i = buscarPrioridad(3, listaProcesosNuevo);
						listaProcesosListo.add(listaProcesosNuevo.get(i));
						listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);;
						System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarProceso(listaProcesosNuevo.get(i).getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'NUEVO' a 'LISTO'");
						
						listaProcesosNuevo.remove(i);
						mostrarInformacionEstados();
						procesoNuevo();
						i=-1;
						//Thread.sleep(1000);	
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
				System.out.println("se ha pasado el proceso: " +  listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString() + "----> 'LISTO' a 'EJECUCION'");
				
				listaProcesosListo.remove(i);//removemos el proceso de LISTO
				mostrarInformacionEstados();
				estadoNuevo_Listo();
				i = -1;//reiniciamos el contador
				//Thread.sleep(1000);
			}else {
				if (hayPrioridad(2, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
					i = buscarPrioridad(2, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
					listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
					listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).setEstadoProceso(2);;
					System.out.println("se ha pasado el proceso: " +  listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString() + "----> 'LISTO' a 'EJECUCION'");
					
					listaProcesosListo.remove(i);//removemos el proceso de LISTO
					mostrarInformacionEstados();
					estadoNuevo_Listo();
					i = -1;//reiniciamos el contador;
					//Thread.sleep(1000);
				}else {
					if (hayPrioridad(3, listaProcesosListo)) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
						i = buscarPrioridad(3, listaProcesosListo);//almacena el indice del elemento de la lista con la prioridad buscada
						listaProcesosEjecutando.add(listaProcesosListo.get(i));//añadimos el proceso a la lista de "EJECUCION"
						listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).setEstadoProceso(2);;
						System.out.println("se ha pasado el proceso: " +  listaProcesosEjecutando.get(buscarProceso(listaProcesosListo.get(i).getIdentificadorProceso(), listaProcesosEjecutando)).toString() + "----> 'LISTO' a 'EJECUCION'");
						
						listaProcesosListo.remove(i);//removemos el proceso de LISTO
						mostrarInformacionEstados();
						estadoNuevo_Listo();
						i = -1;//reiniciamos el contador
						//Thread.sleep(1000);
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
				System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'EJECUCION' a 'BLOQUEADO'");
				listaProcesosEjecutando.remove(buscarIndice(ID, listaProcesosEjecutando));
				mostrarInformacionEstados();
				//Thread.sleep(500);
				return true;
		}else {
			return false;
		}
	}
	
	public void estadoBloqueado_Listo(String ID) throws InterruptedException {//Función que determina si un proceso cumplió con los ciclos respectivos para estar en la lista de "BLOQUEO" y los pasa a "LISTO"
		if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getEventoBloqueo() == 3) {
			if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getCiclosEnBloqueo() == 13) {
				if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getCiclosEjecucion() == temporizador) {
					listaProcesosListo.add(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)));
					listaProcesosListo.get(buscarIndice(ID, listaProcesosListo)).setEstadoProceso(1);
					System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'BLOQUEADO' A 'LISTO' 13-CICLOS");
					listaProcesosBloqueado.remove(buscarIndice(ID, listaProcesosBloqueado));
					mostrarInformacionEstados();
					//Thread.sleep(500);
				}
			}
		}else {
			if(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).getCiclosEnBloqueo() == 27) {
				listaProcesosListo.add(listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)));
				listaProcesosListo.get(buscarIndice(ID, listaProcesosListo)).setEstadoProceso(1);
				//System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'BLOQUEADO' A 'LISTO' 27-CICLOS");
				
				listaProcesosBloqueado.remove(buscarIndice(ID, listaProcesosBloqueado));
				mostrarInformacionEstados();
				//Thread.sleep(1000);
			}
		}
	}
	
	private boolean estadoEjecucion_Listo(AtributosProceso proceso) throws InterruptedException { //Cambio de estado de Ejecucion a listo, asumiento que los procesos entran a lista ejecucion por prioridad
		if (proceso.getCiclosEjecucion() == temporizador) {
			proceso.SetCiclosEjecucion(0);
				listaProcesosListo.add(listaProcesosEjecutando.get(buscarProceso(proceso.getIdentificadorProceso(), listaProcesosEjecutando)));
				listaProcesosListo.get(buscarProceso(proceso.getIdentificadorProceso(), listaProcesosListo)).setEstadoProceso(1);
				listaProcesosEjecutando.remove(proceso);
				System.out.println("se ha pasado el proceso: " +  listaProcesosListo.get(buscarIndice(proceso.getIdentificadorProceso(), listaProcesosListo)).toString() + "----> 'EJECUCION' A 'LISTO'");
				mostrarInformacionEstados();
				//Thread.sleep(500);
				return true;
			}
		return false;
	}
	
	private boolean EstadoEjecucion_Terminado(AtributosProceso proceso) throws InterruptedException {//funcion  que añade procesos a la lista de terminados y retorna un verdadero si el proceso se termina y falso si no.
		if(proceso.getInstruccionesLeidas() == Integer.parseInt(proceso.getCantidadInstrucciones())) {//compara las instrucciones leídas con las instrucciones totales para determinar si el proceso ha finalizado
			listaProcesosTerminado.add(proceso);
			listaProcesosTerminado.get(buscarProceso(proceso.getIdentificadorProceso(), listaProcesosTerminado)).setEstadoProceso(4);
			System.out.println("el proceso " + proceso.toString() + " ha FINALIZADO");
			
			listaProcesosEjecutando.remove(proceso);
			mostrarInformacionEstados();
			//Thread.sleep(100);
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
		cicloActual=0;
		while (n < ciclosDelProcesador) {//cantidad de ciclos dados por el usuario, instrucciones que se leerán a la vez
			
			if (ciclosDelProcesador != 0) {
				cicloActual++;
				n++;
				
				procesoNuevo();
				estadoNuevo_Listo();
				estadoListo_Ejecucion();
				
				
				System.out.println("Ciclo #"+ n);
				Thread.sleep(300);
				
				if((i = buscarPrioridad(1, listaProcesosEjecutando)) != -1){//lo importante es buscar la prioridad mayor y ejecutar ese proceso
					if(!EstadoEjecucion_Terminado(listaProcesosEjecutando.get(i))) {//determinará si el proceso ha finalizado
						if(!estadoEjecucion_Bloqueado(listaProcesosEjecutando.get(i).getInstrucionBloqueo(), listaProcesosEjecutando.get(i).getIdentificadorProceso())) {//determinará si la ultima instruccion es una instruccion de bloqueo
							if(!estadoEjecucion_Listo(listaProcesosEjecutando.get(i))) {//determinará si se cumplieron los ciclos dados por el usuario en este proceso
								listaProcesosEjecutando.get(i).SetCiclosEjecucion();//aumentará la variable cada que se lean las instrucciones
								listaProcesosEjecutando.get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
								mostrarInformacionEstados(listaProcesosEjecutando.get(i).getInstruccionesLeidas(), listaProcesosEjecutando.get(i).toString(), listaProcesosEjecutando.get(i).getInstrucionBloqueo());
								Thread.sleep(100);
								System.out.println("se leyó la instruccion: " + listaProcesosEjecutando.get(i).getInstruccionesLeidas() + " del proceso: " + listaProcesosEjecutando.get(i).toString());
								mostrarInformacionEstados();
							}
						}
					}
				}else {
					if((i = buscarPrioridad(2, listaProcesosEjecutando)) != -1){
						if(!EstadoEjecucion_Terminado(listaProcesosEjecutando.get(i))) {
							if(!estadoEjecucion_Bloqueado(listaProcesosEjecutando.get(i).getInstrucionBloqueo(), listaProcesosEjecutando.get(i).getIdentificadorProceso())) {
								if(!estadoEjecucion_Listo(listaProcesosEjecutando.get(i))) {
									listaProcesosEjecutando.get(i).SetCiclosEjecucion();
									listaProcesosEjecutando.get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
									mostrarInformacionEstados(listaProcesosEjecutando.get(i).getInstruccionesLeidas(), listaProcesosEjecutando.get(i).toString(), listaProcesosEjecutando.get(i).getInstrucionBloqueo());
									Thread.sleep(100);
									System.out.println("se leyó la instruccion: " + listaProcesosEjecutando.get(i).getInstruccionesLeidas() + " del proceso: " + listaProcesosEjecutando.get(i).toString());
									mostrarInformacionEstados();
								}
							}
						}
					}else {
						if((i = buscarPrioridad(3, listaProcesosEjecutando)) != -1){
							if(!EstadoEjecucion_Terminado(listaProcesosEjecutando.get(i))) {
								if(!estadoEjecucion_Bloqueado(listaProcesosEjecutando.get(i).getInstrucionBloqueo(), listaProcesosEjecutando.get(i).getIdentificadorProceso())) {
									if(!estadoEjecucion_Listo(listaProcesosEjecutando.get(i))) {
										listaProcesosEjecutando.get(i).SetCiclosEjecucion();
										listaProcesosEjecutando.get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
										mostrarInformacionEstados(listaProcesosEjecutando.get(i).getInstruccionesLeidas(), listaProcesosEjecutando.get(i).toString(), listaProcesosEjecutando.get(i).getInstrucionBloqueo());
										Thread.sleep(100);
										System.out.println("se leyó la instruccion: " + listaProcesosEjecutando.get(i).getInstruccionesLeidas() + " del proceso: " + listaProcesosEjecutando.get(i).toString());
										mostrarInformacionEstados();
									}
								}
							}
						}else {
							estadoListo_Ejecucion();
							mostrarInformacionEstados();
						}
					}
				}
				
				
				for(int j=0 ; j<listaProcesosBloqueado.size() ; j++) {
					listaProcesosBloqueado.get(j).setCiclosEnBloqueo(listaProcesosBloqueado.get(j).getCiclosEnBloqueo()+1);
					estadoBloqueado_Listo(listaProcesosBloqueado.get(j).getIdentificadorProceso());
					Thread.sleep(100);
					mostrarInformacionEstados();
				}
			
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

	public void inicializarVentana(){
		setTitle("Simulador de Gestor de Procesos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 700);
		contentPane = new JPanel();
		contentPane.setLocation(0, -340);
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(UIManager.getBorder("Button.border"));
		setContentPane(contentPane);
	}
	
	public void inicializarElementos(){		
		contentPane.setLayout(null);
		
		JLabel lblGestorDeProcesos = new JLabel("Gestor de Procesos");
		lblGestorDeProcesos.setBounds(10, 24, 600, 30);
		lblGestorDeProcesos.setFont(new Font("Sylfaen", Font.PLAIN, 24));
		lblGestorDeProcesos.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblGestorDeProcesos);
		
		JPanel panelEstadoTerminado = new JPanel();
		panelEstadoTerminado.setBackground(SystemColor.controlHighlight);
		panelEstadoTerminado.setBounds(418, 371, 194, 279);
		panelEstadoTerminado.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoTerminado);
		panelEstadoTerminado.setLayout(null);
		
		JScrollPane scrollInformacionEstadoTerminado = new JScrollPane();
		scrollInformacionEstadoTerminado.setBounds(10, 22, 174, 246);
		panelEstadoTerminado.add(scrollInformacionEstadoTerminado);
		
		txtAInformacionEstadoTerminado = new JTextArea();
		txtAInformacionEstadoTerminado.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoTerminado.setLineWrap(true);
		txtAInformacionEstadoTerminado.setEditable(false);
		scrollInformacionEstadoTerminado.setViewportView(txtAInformacionEstadoTerminado);
		
		JLabel lblEstadoTerminado = new JLabel("Estado Terminado");
		lblEstadoTerminado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoTerminado.setBounds(10, 5, 174, 14);
		panelEstadoTerminado.add(lblEstadoTerminado);
		
		JPanel panelEstadoEjecucion = new JPanel();
		panelEstadoEjecucion.setBackground(SystemColor.controlHighlight);
		panelEstadoEjecucion.setBounds(10, 371, 194, 279);
		panelEstadoEjecucion.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoEjecucion);
		panelEstadoEjecucion.setLayout(null);
		
		JScrollPane scrollInformacionEstadoEjecucion = new JScrollPane();
		scrollInformacionEstadoEjecucion.setBounds(10, 22, 174, 246);
		panelEstadoEjecucion.add(scrollInformacionEstadoEjecucion);
		
		txtAInformacionEstadoEjecucion = new JTextArea();
		txtAInformacionEstadoEjecucion.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoEjecucion.setLineWrap(true);
		txtAInformacionEstadoEjecucion.setEditable(false);
		scrollInformacionEstadoEjecucion.setViewportView(txtAInformacionEstadoEjecucion);
		
		JLabel lblEstadoEjecucin = new JLabel("Estado Ejecuci\u00F3n");
		lblEstadoEjecucin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoEjecucin.setBounds(10, 5, 174, 14);
		panelEstadoEjecucion.add(lblEstadoEjecucin);
		
		JPanel panelDetalle = new JPanel();
		panelDetalle.setBackground(SystemColor.controlHighlight);
		panelDetalle.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelDetalle.setBounds(10, 70, 194, 290);
		contentPane.add(panelDetalle);
		panelDetalle.setLayout(null);
		
		lblRespuestaProceso = new JLabel("");
		lblRespuestaProceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRespuestaProceso.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRespuestaProceso.setBounds(32, 181, 141, 14);
		panelDetalle.add(lblRespuestaProceso);
		
		lblRespuestaInstruccionLeida = new JLabel("");
		lblRespuestaInstruccionLeida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRespuestaInstruccionLeida.setBounds(144, 128, 40, 14);
		panelDetalle.add(lblRespuestaInstruccionLeida);
		
		JLabel lblCicloDelProcesador = new JLabel("Ciclo del Procesador:");
		lblCicloDelProcesador.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblCicloDelProcesador.setBounds(10, 83, 119, 14);
		panelDetalle.add(lblCicloDelProcesador);
		
		lblRespuestaCicloDelProcesador = new JLabel("");
		lblRespuestaCicloDelProcesador.setBounds(144, 83, 40, 14);
		panelDetalle.add(lblRespuestaCicloDelProcesador);
		
		lblRespuestaNumeroDeInstruccion = new JLabel("");
		lblRespuestaNumeroDeInstruccion.setBounds(144, 103, 40, 14);
		panelDetalle.add(lblRespuestaNumeroDeInstruccion);
		
		JLabel lblInstruccin = new JLabel("Instrucci\u00F3n de bloqueo:");
		lblInstruccin.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblInstruccin.setBounds(10, 103, 119, 14);
		panelDetalle.add(lblInstruccin);
		
		JLabel lblInstruccionLeida = new JLabel("Instruccion Leida:");
		lblInstruccionLeida.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblInstruccionLeida.setBounds(10, 128, 95, 14);
		panelDetalle.add(lblInstruccionLeida);
		
		JLabel lblProceso = new JLabel("Del Proceso:");
		lblProceso.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblProceso.setBounds(10, 153, 65, 14);
		panelDetalle.add(lblProceso);
		
		JLabel lblEstadoActual = new JLabel("Estado Actual:");
		lblEstadoActual.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblEstadoActual.setBounds(10, 207, 70, 14);
		panelDetalle.add(lblEstadoActual);
		
		lblRespuestaEstadoActual = new JLabel("");
		lblRespuestaEstadoActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestaEstadoActual.setBounds(79, 207, 105, 14);
		panelDetalle.add(lblRespuestaEstadoActual);
		
		lblObservacion = new JLabel("");
		lblObservacion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblObservacion.setVerticalAlignment(SwingConstants.TOP);
		lblObservacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblObservacion.setForeground(Color.RED);
		lblObservacion.setBounds(10, 232, 174, 47);
		panelDetalle.add(lblObservacion);
		
		JLabel lblDetalleProceso = new JLabel("Detalle Proceso");
		lblDetalleProceso.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleProceso.setBounds(10, 4, 174, 14);
		panelDetalle.add(lblDetalleProceso);
		
		JLabel lblIngreseCicloDel = new JLabel("Ingrese Ciclo del Procesador:");
		lblIngreseCicloDel.setBounds(10, 29, 152, 14);
		panelDetalle.add(lblIngreseCicloDel);
		
		txtCicloProcesador = new JTextField();
		txtCicloProcesador.setBounds(10, 50, 85, 23);
		panelDetalle.add(txtCicloProcesador);
		txtCicloProcesador.setColumns(10);
		
		
		
		
		
		btnEmpezar.setBounds(100, 50, 85, 23);
		panelDetalle.add(btnEmpezar);
		JPanel panelEstadoNuevo = new JPanel();
		panelEstadoNuevo.setBackground(SystemColor.controlHighlight);
		panelEstadoNuevo.setBounds(214, 70, 194, 290);
		panelEstadoNuevo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoNuevo);
		panelEstadoNuevo.setLayout(null);
		
		JScrollPane scrollInformacionEstadoNuevo = new JScrollPane();
		scrollInformacionEstadoNuevo.setBounds(10, 22, 174, 257);
		panelEstadoNuevo.add(scrollInformacionEstadoNuevo);
		setVisible(true);
		
		txtAInformacionEstadoNuevo = new JTextArea();
		txtAInformacionEstadoNuevo.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoNuevo.setLineWrap(true);
		txtAInformacionEstadoNuevo.setEditable(false);
		scrollInformacionEstadoNuevo.setViewportView(txtAInformacionEstadoNuevo);
		
		JLabel lblEstadoNuevo = new JLabel("Estado Nuevo");
		lblEstadoNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoNuevo.setBounds(10, 5, 174, 14);
		panelEstadoNuevo.add(lblEstadoNuevo);
		
		JPanel panelEstadoListo = new JPanel();
		panelEstadoListo.setBackground(SystemColor.controlHighlight);
		panelEstadoListo.setBounds(418, 70, 194, 290);
		panelEstadoListo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoListo);
		panelEstadoListo.setLayout(null);
		
		JScrollPane scrollInformacionEstadoListo = new JScrollPane();
		scrollInformacionEstadoListo.setBounds(10, 22, 174, 257);
		panelEstadoListo.add(scrollInformacionEstadoListo);
		setVisible(true);
		
		txtAInformacionEstadoListo = new JTextArea();
		txtAInformacionEstadoListo.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoListo.setLineWrap(true);
		txtAInformacionEstadoListo.setEditable(false);
		scrollInformacionEstadoListo.setViewportView(txtAInformacionEstadoListo);
		
		JLabel lblEstadoListo = new JLabel("Estado Listo");
		lblEstadoListo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoListo.setBounds(10, 5, 174, 14);
		panelEstadoListo.add(lblEstadoListo);
		setVisible(true);
		
		JPanel panelEstadoBloqueado = new JPanel();
		panelEstadoBloqueado.setBackground(SystemColor.controlHighlight);
		panelEstadoBloqueado.setBounds(214, 371, 194, 279);
		panelEstadoBloqueado.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoBloqueado);
		panelEstadoBloqueado.setLayout(null);
		
		JScrollPane scrollInformacionEstadoBloqueado = new JScrollPane();
		scrollInformacionEstadoBloqueado.setBounds(10, 22, 174, 246);
		panelEstadoBloqueado.add(scrollInformacionEstadoBloqueado);
		setVisible(true);
		
		txtAInformacionEstadoBloqueado = new JTextArea();
		txtAInformacionEstadoBloqueado.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoBloqueado.setLineWrap(true);
		txtAInformacionEstadoBloqueado.setEditable(false);
		scrollInformacionEstadoBloqueado.setViewportView(txtAInformacionEstadoBloqueado);
		
		JLabel lblEstadoBloqueado = new JLabel("Estado Bloqueado");
		lblEstadoBloqueado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoBloqueado.setBounds(10, 5, 174, 14);
		panelEstadoBloqueado.add(lblEstadoBloqueado);
		setVisible(true);
	}
	
	public void mostrarInformacionEstados(){//echarle un ojo despues
		txtAInformacionEstadoNuevo.setText(concatenarLista(listaProcesosNuevo));
		lblRespuestaEstadoActual.setText("Nuevo");
		txtAInformacionEstadoListo.setText(concatenarLista(listaProcesosListo));
		lblRespuestaEstadoActual.setText("Listo");
		txtAInformacionEstadoEjecucion.setText(concatenarLista(listaProcesosEjecutando));
		lblRespuestaEstadoActual.setText("Ejecución");
		txtAInformacionEstadoBloqueado.setText(concatenarLista(listaProcesosBloqueado));
		lblRespuestaEstadoActual.setText("Bloqueado");
		txtAInformacionEstadoTerminado.setText(concatenarLista(listaProcesosTerminado));
		lblRespuestaEstadoActual.setText("Terminado");
	}
	
	public void mostrarInformacionEstados(int numeroInstruccion, String NombreProceso, String instruccionBloqueo){//echarle un ojo despues
		lblRespuestaNumeroDeInstruccion.setText( "#" + instruccionBloqueo);
		lblRespuestaCicloDelProcesador.setText( "#" + cicloActual );
		lblRespuestaInstruccionLeida.setText("#" + numeroInstruccion);
		lblRespuestaProceso.setText(NombreProceso);
		lblObservacion.setText("");
}
	
	public JLabel getLblObservacion() {
		return lblObservacion;
	}
	
	public void setLblObservacion(JLabel lblObservacion) {
		this.lblObservacion = lblObservacion;
	}
	
	private String concatenarLista(ArrayList<AtributosProceso> lista) {
		String buffer = "";
		for (int i=0 ; i<lista.size() ; i++) {
			buffer = buffer + lista.get(i) + "\n";
		}
		return buffer;
	}
	
}
