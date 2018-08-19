package Implementacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import procesos.ArchivoTexto;
import procesos.AtributosProceso;
import procesos.Escritura_y_Lectura;

public class Ejecucion extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel panelEstadoTerminado;
	private JPanel panelEstadoEjecucion;
	private JPanel panelDetalle;
	private JPanel panelEstadoNuevo;	
	private JPanel panelEstadoListo;	
	private JPanel panelEstadoBloqueado;
	
	private JScrollPane scrollInformacionEstadoTerminado;	
	private JScrollPane scrollInformacionEstadoEjecucion;
	private JScrollPane scrollInformacionEstadoNuevo;
	private JScrollPane scrollInformacionEstadoBloqueado;
	private JScrollPane scrollInformacionEstadoListo;
	
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
	private JLabel lblGestorDeProcesos;
	private JLabel lblEstadoTerminado;
	private JLabel lblCicloDelProcesador;
	private JLabel lblInstruccin;
	private JLabel lblInstruccionLeida;
	private JLabel lblProceso;
	private JLabel lblEstadoEjecucin;
	private JLabel lblEstadoActual;
	private JLabel lblDetalleProceso;
	private JLabel lblIngreseCicloDel;
	private JLabel lblEstadoNuevo;
	private JLabel lblEstadoListo;
	private JLabel lblEstadoBloqueado;
	
	private JButton btnEmpezar;		
	private JTextField txtCicloProcesador;
	
	private ArchivoTexto archivo = new ArchivoTexto();
	private Escritura_y_Lectura gestor = new Escritura_y_Lectura();
	private Random random = new Random();
	
	int ciclosDelProcesardor;
	private int pausa = 1000;

	public Ejecucion() {
		inicializarVentana();
		inicializarElementos();
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
		contentPane.setLayout(null);
	}
	public void inicializarElementos(){				
		
		panelDetalle = new JPanel();
		panelDetalle.setBackground(SystemColor.controlHighlight);
		panelDetalle.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelDetalle.setBounds(10, 70, 194, 290);
		contentPane.add(panelDetalle);
		panelDetalle.setLayout(null);
		
		lblRespuestaCicloDelProcesador = new JLabel("");
		lblRespuestaCicloDelProcesador.setBounds(144, 83, 40, 14);
		panelDetalle.add(lblRespuestaCicloDelProcesador);
		
		lblRespuestaProceso = new JLabel("");
		lblRespuestaProceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRespuestaProceso.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRespuestaProceso.setBounds(32, 181, 141, 14);
		panelDetalle.add(lblRespuestaProceso);
		
		lblRespuestaInstruccionLeida = new JLabel("");
		lblRespuestaInstruccionLeida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRespuestaInstruccionLeida.setBounds(144, 128, 40, 14);
		panelDetalle.add(lblRespuestaInstruccionLeida);
		
		lblCicloDelProcesador = new JLabel("Ciclo del Procesador:");
		lblCicloDelProcesador.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblCicloDelProcesador.setBounds(10, 83, 119, 14);
		panelDetalle.add(lblCicloDelProcesador);
		
		lblRespuestaNumeroDeInstruccion = new JLabel("");
		lblRespuestaNumeroDeInstruccion.setBounds(144, 103, 40, 14);
		panelDetalle.add(lblRespuestaNumeroDeInstruccion);
		
		lblInstruccin = new JLabel("Instrucci\u00F3n:");
		lblInstruccin.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblInstruccin.setBounds(10, 103, 119, 14);
		panelDetalle.add(lblInstruccin);
		
		lblInstruccionLeida = new JLabel("Instruccion Leida:");
		lblInstruccionLeida.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblInstruccionLeida.setBounds(10, 128, 95, 14);
		panelDetalle.add(lblInstruccionLeida);
		
		lblProceso = new JLabel("Del Proceso:");
		lblProceso.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblProceso.setBounds(10, 153, 65, 14);
		panelDetalle.add(lblProceso);
		
		lblEstadoActual = new JLabel("Estado Actual:");
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
		
		lblDetalleProceso = new JLabel("Detalle Proceso");
		lblDetalleProceso.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleProceso.setBounds(10, 4, 174, 14);
		panelDetalle.add(lblDetalleProceso);
		
		lblIngreseCicloDel = new JLabel("Ingrese Ciclo del Procesador:");
		lblIngreseCicloDel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblIngreseCicloDel.setBounds(10, 29, 152, 14);
		panelDetalle.add(lblIngreseCicloDel);
		
		txtCicloProcesador = new JTextField();
		txtCicloProcesador.addKeyListener( new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				char C = e.getKeyChar();
				if ( Character.isLetter(C) ){
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtCicloProcesador.setBounds(10, 50, 85, 23);
		panelDetalle.add(txtCicloProcesador);
		txtCicloProcesador.setColumns(10);
		
		btnEmpezar = new JButton("Empezar");		
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ciclos = txtCicloProcesador.getText();
				ciclosDelProcesardor =  Integer.parseInt(ciclos);
				System.out.println(ciclosDelProcesardor);
				empezar();
			}
		});
		btnEmpezar.setBounds(100, 50, 85, 23);
		btnEmpezar.addActionListener(this);
		
		panelDetalle.add(btnEmpezar);
		panelEstadoNuevo = new JPanel();
		panelEstadoNuevo.setBackground(SystemColor.controlHighlight);
		panelEstadoNuevo.setBounds(214, 70, 194, 290);
		panelEstadoNuevo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoNuevo);
		panelEstadoNuevo.setLayout(null);
		
		scrollInformacionEstadoNuevo = new JScrollPane();
		scrollInformacionEstadoNuevo.setBounds(10, 22, 174, 257);
		panelEstadoNuevo.add(scrollInformacionEstadoNuevo);
		
		txtAInformacionEstadoNuevo = new JTextArea();
		txtAInformacionEstadoNuevo.setColumns(5);
		txtAInformacionEstadoNuevo.setRows(5);
		txtAInformacionEstadoNuevo.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoNuevo.setLineWrap(true);
		txtAInformacionEstadoNuevo.setEditable(false);
		scrollInformacionEstadoNuevo.setViewportView(txtAInformacionEstadoNuevo);
		
		lblEstadoNuevo = new JLabel("Estado Nuevo");
		lblEstadoNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoNuevo.setBounds(10, 5, 174, 14);
		panelEstadoNuevo.add(lblEstadoNuevo);
		
		lblGestorDeProcesos = new JLabel("Gestor de Procesos");
		lblGestorDeProcesos.setBounds(10, 24, 600, 30);
		lblGestorDeProcesos.setFont(new Font("Sylfaen", Font.PLAIN, 24));
		lblGestorDeProcesos.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblGestorDeProcesos);
		
		panelEstadoTerminado = new JPanel();
		panelEstadoTerminado.setBackground(SystemColor.controlHighlight);
		panelEstadoTerminado.setBounds(418, 371, 194, 279);
		panelEstadoTerminado.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoTerminado);
		panelEstadoTerminado.setLayout(null);
		
		scrollInformacionEstadoTerminado = new JScrollPane();
		scrollInformacionEstadoTerminado.setBounds(10, 22, 174, 246);
		panelEstadoTerminado.add(scrollInformacionEstadoTerminado);
		
		txtAInformacionEstadoTerminado = new JTextArea();
		txtAInformacionEstadoTerminado.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoTerminado.setLineWrap(true);
		txtAInformacionEstadoTerminado.setEditable(false);
		scrollInformacionEstadoTerminado.setViewportView(txtAInformacionEstadoTerminado);
		
		lblEstadoTerminado = new JLabel("Estado Terminado");
		lblEstadoTerminado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoTerminado.setBounds(10, 5, 174, 14);
		panelEstadoTerminado.add(lblEstadoTerminado);
		
		panelEstadoEjecucion = new JPanel();
		panelEstadoEjecucion.setBackground(SystemColor.controlHighlight);
		panelEstadoEjecucion.setBounds(10, 371, 194, 279);
		panelEstadoEjecucion.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoEjecucion);
		panelEstadoEjecucion.setLayout(null);
		
		scrollInformacionEstadoEjecucion = new JScrollPane();
		scrollInformacionEstadoEjecucion.setBounds(10, 22, 174, 246);
		panelEstadoEjecucion.add(scrollInformacionEstadoEjecucion);
		
		txtAInformacionEstadoEjecucion = new JTextArea();
		txtAInformacionEstadoEjecucion.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoEjecucion.setLineWrap(true);
		txtAInformacionEstadoEjecucion.setEditable(false);
		scrollInformacionEstadoEjecucion.setViewportView(txtAInformacionEstadoEjecucion);
		
		lblEstadoEjecucin = new JLabel("Estado Ejecuci\u00F3n");
		lblEstadoEjecucin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoEjecucin.setBounds(10, 5, 174, 14);
		panelEstadoEjecucion.add(lblEstadoEjecucin);
		setVisible(true);
		
		panelEstadoListo = new JPanel();
		panelEstadoListo.setBackground(SystemColor.controlHighlight);
		panelEstadoListo.setBounds(418, 70, 194, 290);
		panelEstadoListo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoListo);
		panelEstadoListo.setLayout(null);
		
		scrollInformacionEstadoListo = new JScrollPane();
		scrollInformacionEstadoListo.setBounds(10, 22, 174, 257);
		panelEstadoListo.add(scrollInformacionEstadoListo);
		setVisible(true);
		
		txtAInformacionEstadoListo = new JTextArea();
		txtAInformacionEstadoListo.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoListo.setLineWrap(true);
		txtAInformacionEstadoListo.setEditable(false);
		scrollInformacionEstadoListo.setViewportView(txtAInformacionEstadoListo);
		
		lblEstadoListo = new JLabel("Estado Listo");
		lblEstadoListo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoListo.setBounds(10, 5, 174, 14);
		panelEstadoListo.add(lblEstadoListo);
		setVisible(true);
		
		panelEstadoBloqueado = new JPanel();
		panelEstadoBloqueado.setBackground(SystemColor.controlHighlight);
		panelEstadoBloqueado.setBounds(214, 371, 194, 279);
		panelEstadoBloqueado.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelEstadoBloqueado);
		panelEstadoBloqueado.setLayout(null);
		
		scrollInformacionEstadoBloqueado = new JScrollPane();
		scrollInformacionEstadoBloqueado.setBounds(10, 22, 174, 246);
		panelEstadoBloqueado.add(scrollInformacionEstadoBloqueado);
		setVisible(true);
		
		txtAInformacionEstadoBloqueado = new JTextArea();
		txtAInformacionEstadoBloqueado.setFont(new Font("Nunito", Font.PLAIN, 12));
		txtAInformacionEstadoBloqueado.setLineWrap(true);
		txtAInformacionEstadoBloqueado.setEditable(false);
		scrollInformacionEstadoBloqueado.setViewportView(txtAInformacionEstadoBloqueado);
		
		lblEstadoBloqueado = new JLabel("Estado Bloqueado");
		lblEstadoBloqueado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoBloqueado.setBounds(10, 5, 174, 14);
		panelEstadoBloqueado.add(lblEstadoBloqueado);
		setVisible(true);
	}
	
	public void mostrarInformacionEstados( String parametros, String estadoInstruccionProceso ){
		if( estadoInstruccionProceso == "procesoNuevo" ){
			txtAInformacionEstadoNuevo.append( "      " + parametros + "\n" );
			lblRespuestaEstadoActual.setText("Nuevo");
		}else if ( (estadoInstruccionProceso == "estadoNuevo_Listo") || (estadoInstruccionProceso == "estadoBloqueado_Listo") || (estadoInstruccionProceso == "estadoEjecucion_Listo")){
			txtAInformacionEstadoListo.append( "      " + parametros + "\n" );
			lblRespuestaEstadoActual.setText("Listo");
		}else if ( estadoInstruccionProceso == "estadoListo_Ejecucion" ){
			txtAInformacionEstadoEjecucion.append( "      " + parametros + "\n" );
			lblRespuestaEstadoActual.setText("Ejecución");
		}else if ( estadoInstruccionProceso == "estadoEjecucion_Bloqueado" ){
			txtAInformacionEstadoBloqueado.append( "      " + parametros + "\n" );
			lblRespuestaEstadoActual.setText("Bloqueado");
		}else if ( estadoInstruccionProceso == "EstadoEjecucion_Terminado" ){
			txtAInformacionEstadoTerminado.append( "      " + parametros + "\n" );
			lblRespuestaEstadoActual.setText("Terminado");
		}else if ( estadoInstruccionProceso == "Instruccion" ){
			lblRespuestaNumeroDeInstruccion.setText( "#" + parametros );
		}else if ( estadoInstruccionProceso == "CicloDelProcesador" ){
			lblRespuestaCicloDelProcesador.setText( "#" + parametros );
		}else if ( estadoInstruccionProceso == "instruccionLeida"){
			lblRespuestaInstruccionLeida.setText(parametros);
		}else if ( estadoInstruccionProceso == "proceso" ){
			lblRespuestaProceso.setText(parametros);
		}else if ( estadoInstruccionProceso == "Mensaje"){
			lblObservacion.setText(parametros);
		}
	}
	
	public void empezar(){
		int n=0;
		if ( ciclosDelProcesardor==0 ){
			mostrarInformacionEstados("Ingrese cuantos ciclos desea", "Mensaje");
		}else{		
			while(true) {	
				n++;
	
				//System.out.println("CicloDelProcesador #"+n);
				mostrarInformacionEstados( String.valueOf(n), "CicloDelProcesador");
				
				cicloEjecucion();
				/*procesos.guardarEstadoEjecucion(gestor.getListaProcesosListo(), "Listo");
				//ArrayList<AtributosProceso> Listo = procesos.recuperarEstadoEjecucion("Listo");
				for(int i=0; i<gestor.getListaProcesosListo().size() ; i++) {
						System.out.println(Listo.get(i).toString());
				}*/
				actualizarInformacion();			
			}
		}
	}
	
	public void cicloEjecucion () {
		int n = 0;
		int i = -1;
		while (n < ciclosDelProcesardor) {//cantidad de ciclos dados por el usuario, instrucciones que se leerán a la vez
			
			n++;
			
			procesoNuevo();
			estadoNuevo_Listo();
			estadoListo_Ejecucion();
			
			
			//System.out.println("Instruccion #"+ n);
			try {
				Thread.sleep(pausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mostrarInformacionEstados( String.valueOf(n), "Instruccion" );
			
			if((i = gestor.buscarPrioridad(1, gestor.getListaProcesosEjecutando())) != -1){//lo importante es buscar la prioridad mayor y ejecutar ese proceso
				if(EstadoEjecucion_Terminado(gestor.getListaProcesosEjecutando().get(i)) != true) {//determinará si el proceso ha finalizado
					if(estadoEjecucion_Bloqueado(gestor.getListaProcesosEjecutando().get(i).getInstrucionBloqueo(), gestor.getListaProcesosEjecutando().get(i).getIdentificadorProceso()) != true ) {//determinará si la ultima instruccion es una instruccion de bloqueo
						if(estadoEjecucion_Listo(gestor.getListaProcesosEjecutando().get(i)) != true ) {//determinará si se cumplieron los ciclos dados por el usuario en este proceso
							gestor.getListaProcesosEjecutando().get(i).SetCiclosEjecucion();//aumentará la variable cada que se lean las instrucciones
							gestor.getListaProcesosEjecutando().get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
							//System.out.println("se leyó la instruccion: " + gestor.getListaProcesosEjecutando().get(i).getInstruccionesLeidas() + " del proceso: " + gestor.getListaProcesosEjecutando().get(i).toString());
							mostrarInformacionEstados( String.valueOf(gestor.getListaProcesosEjecutando().get(i).getInstruccionesLeidas()), "instruccionLeida" );
							mostrarInformacionEstados( gestor.getListaProcesosEjecutando().get(i).toString(), "proceso");
						}
					}
				}
			}else {
				if((i = gestor.buscarPrioridad(2, gestor.getListaProcesosEjecutando())) != -1){
					if(EstadoEjecucion_Terminado(gestor.getListaProcesosEjecutando().get(i)) != true ) {
						if(estadoEjecucion_Bloqueado(gestor.getListaProcesosEjecutando().get(i).getInstrucionBloqueo(), gestor.getListaProcesosEjecutando().get(i).getIdentificadorProceso()) != true ) {
							if(estadoEjecucion_Listo(gestor.getListaProcesosEjecutando().get(i)) != true ) {
								gestor.getListaProcesosEjecutando().get(i).SetCiclosEjecucion();
								gestor.getListaProcesosEjecutando().get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
								//System.out.println("se leyó la instruccion: " + gestor.getListaProcesosEjecutando().get(i).getInstruccionesLeidas() + " del proceso: " + gestor.getListaProcesosEjecutando().get(i).toString());
								mostrarInformacionEstados( String.valueOf(gestor.getListaProcesosEjecutando().get(i).getInstruccionesLeidas()), "instruccionLeida" );
								mostrarInformacionEstados( gestor.getListaProcesosEjecutando().get(i).toString(), "proceso");
							}
						}
					}
				}else {
					if((i = gestor.buscarPrioridad(3, gestor.getListaProcesosEjecutando())) != -1){
						if(EstadoEjecucion_Terminado(gestor.getListaProcesosEjecutando().get(i)) != true) {
							if(estadoEjecucion_Bloqueado(gestor.getListaProcesosEjecutando().get(i).getInstrucionBloqueo(), gestor.getListaProcesosEjecutando().get(i).getIdentificadorProceso()) != true ) {
								if(estadoEjecucion_Listo(gestor.getListaProcesosEjecutando().get(i)) != true ) {
									gestor.getListaProcesosEjecutando().get(i).SetCiclosEjecucion();
									gestor.getListaProcesosEjecutando().get(i).setInstruccionesLeidas();//se leerá la instruccion siguiente
									//System.out.println("se leyó la instruccion: " + gestor.getListaProcesosEjecutando().get(i).getInstruccionesLeidas() + " del proceso: " + gestor.getListaProcesosEjecutando().get(i).toString());
									mostrarInformacionEstados( String.valueOf(gestor.getListaProcesosEjecutando().get(i).getInstruccionesLeidas()), "instruccionLeida" );
									mostrarInformacionEstados(  gestor.getListaProcesosEjecutando().get(i).toString(), "proceso" );
								}
							}
						}
					}else {
						estadoListo_Ejecucion();
					}
				}
			}
			
			
			for(int j=0 ; j<gestor.getListaProcesosBloqueado().size() ; j++) {
				gestor.getListaProcesosBloqueado().get(j).setCiclosEnBloqueo(gestor.getListaProcesosBloqueado().get(j).getCiclosEnBloqueo()+1);
				estadoBloqueado_Listo(gestor.getListaProcesosBloqueado().get(j).getIdentificadorProceso());
			}		
		}
	}	
	public void actualizarInformacion() {
		if(gestor.getListaProcesosNuevo().size()>0)
		archivo.escrbirArchivoPlanoEstado(gestor.getListaProcesosNuevo());
		if(gestor.getListaProcesosListo().size()>0)
		archivo.escrbirArchivoPlanoEstado(gestor.getListaProcesosListo());
		if(gestor.getListaProcesosEjecutando().size()>0)
		archivo.escrbirArchivoPlanoEstado(gestor.getListaProcesosEjecutando());
		if(gestor.getListaProcesosBloqueado().size()>0)
		archivo.escrbirArchivoPlanoEstado(gestor.getListaProcesosBloqueado());
		if(gestor.getListaProcesosTerminado().size()>0)
		archivo.escrbirArchivoPlanoEstado(gestor.getListaProcesosTerminado());
	}
	
	public void procesoNuevo () {
		while(gestor.getListaProcesosNuevo().size() < gestor.getMaximoProcesos()) {
			int IndiceUtilizado = random.nextInt(gestor.getListaID_Utilizables().size());//sacamos el numero al azar para la creacion de procesos, que será el indice de la lista disponible
			gestor.getListaProcesosNuevo().add(new AtributosProceso(gestor.getListaID_Utilizables().get(IndiceUtilizado)));//sacamos el String, con las regulaciones necesarias para mandarlo como parametro al constructor de "AtributosProceso"
			//actualizarTXT("procesos/Nuevo/estadoProcesoNuevo.txt", gestor.getListaProcesosNuevo());
			System.out.println("se ha creado un proceso: " + gestor.getListaProcesosNuevo().get(gestor.buscarProceso(gestor.getListaID_Utilizables().get(IndiceUtilizado), gestor.getListaProcesosNuevo())).toString() + "---> NUEVO");
			mostrarInformacionEstados( gestor.getListaProcesosNuevo().get(gestor.buscarProceso(gestor.getListaID_Utilizables().get(IndiceUtilizado), gestor.getListaProcesosNuevo())).toString(), "procesoNuevo" );
			gestor.getListaID_Utilizables().remove(IndiceUtilizado);//Eliminamos el item para evitar que se repita nuestro ID que debe ser único
			try {
				Thread.sleep(pausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private boolean estadoEjecucion_Listo(AtributosProceso proceso) { //Cambio de estado de Ejecucion a listo, asumiento que los procesos entran a lista ejecucion por prioridad
		if (proceso.getCiclosEjecucion() == ciclosDelProcesardor) {
			proceso.SetCiclosEjecucion(0);
				gestor.getListaProcesosListo().add(gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(proceso.getIdentificadorProceso(), gestor.getListaProcesosEjecutando())));
				gestor.getListaProcesosListo().get(gestor.buscarProceso(proceso.getIdentificadorProceso(), gestor.getListaProcesosListo())).setEstadoProceso(1);
				gestor.getListaProcesosEjecutando().remove(proceso);
				//System.out.println("se ha pasado el proceso: " +  gestor.getListaProcesosListo().get(gestor.buscarIndice(proceso.getIdentificadorProceso(), gestor.getListaProcesosListo())).toString() + "----> 'EJECUCION' A 'LISTO'");
				mostrarInformacionEstados( gestor.getListaProcesosListo().get(gestor.buscarIndice(proceso.getIdentificadorProceso(), gestor.getListaProcesosListo())).toString(), "estadoEjecucion_Listo" );
				try {
					Thread.sleep(pausa);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;
			}
		return false;
	}
	public void estadoNuevo_Listo() {//funcion que pasará un maximo de 10 procesos a la lista "LISTO"
		int i = -1;//indice inicializadop en -1 para que genero un error si no se ha hecho asignacion
		while(gestor.getListaProcesosListo().size() < gestor.getMaximoProcesos()) {//condicion para asignar 10 procesos como mucho a la lista "LISTO"
			if (gestor.hayPrioridad(1, gestor.getListaProcesosNuevo())) {//se buscarán los procesos de mayor prioridad y si existen entrará al 'for'
				i = gestor.buscarPrioridad(1, gestor.getListaProcesosNuevo());//indice donde está el proceso de 'x' prioridad que se moverá que se moverá
				gestor.getListaProcesosListo().add(gestor.getListaProcesosNuevo().get(i));//Añade a la lista"LISTO" el proceso desde "NUEVO"
				gestor.getListaProcesosListo().get(gestor.buscarProceso(gestor.getListaProcesosNuevo().get(i).getIdentificadorProceso(), gestor.getListaProcesosListo())).setEstadoProceso(1);
				//System.out.println("se ha pasado el proceso: " +  gestor.getListaProcesosListo().get(gestor.buscarProceso(gestor.getListaProcesosNuevo().get(i).getIdentificadorProceso(), gestor.getListaProcesosListo())).toString() + "----> 'NUEVO' a 'LISTO'");
				mostrarInformacionEstados( gestor.getListaProcesosListo().get(gestor.buscarProceso(gestor.getListaProcesosNuevo().get(i).getIdentificadorProceso(), gestor.getListaProcesosListo())).toString(), "estadoNuevo_Listo" );
				gestor.getListaProcesosNuevo().remove(i);//removemos el proceso que se trasladó
				i=-1;//reiniciamos el indice para no
				try {
					Thread.sleep(pausa);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}else {
				if (gestor.hayPrioridad(2, gestor.getListaProcesosNuevo())) {//se buscarán los procesos de media prioridad y si existen entrará al 'for' 
					i = gestor.buscarPrioridad(2, gestor.getListaProcesosNuevo());
					gestor.getListaProcesosListo().add(gestor.getListaProcesosNuevo().get(i));
					gestor.getListaProcesosListo().get(gestor.buscarProceso(gestor.getListaProcesosNuevo().get(i).getIdentificadorProceso(), gestor.getListaProcesosListo())).setEstadoProceso(1);;
					//System.out.println("se ha pasado el proceso: " +  gestor.getListaProcesosListo().get(gestor.buscarProceso(gestor.getListaProcesosNuevo().get(i).getIdentificadorProceso(), gestor.getListaProcesosListo())).toString() + "----> 'NUEVO' a 'LISTO'");
					mostrarInformacionEstados( gestor.getListaProcesosListo().get(gestor.buscarProceso(gestor.getListaProcesosNuevo().get(i).getIdentificadorProceso(), gestor.getListaProcesosListo())).toString(), "estadoNuevo_Listo" );
					gestor.getListaProcesosNuevo().remove(i);
					i=-1;
					try {
						Thread.sleep(pausa);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}else {
					if (gestor.hayPrioridad(3, gestor.getListaProcesosNuevo())) {
						i = gestor.buscarPrioridad(3, gestor.getListaProcesosNuevo());
						gestor.getListaProcesosListo().add(gestor.getListaProcesosNuevo().get(i));
						gestor.getListaProcesosListo().get(gestor.buscarProceso(gestor.getListaProcesosNuevo().get(i).getIdentificadorProceso(), gestor.getListaProcesosListo())).setEstadoProceso(1);;
						//System.out.println("se ha pasado el proceso: " +  gestor.getListaProcesosListo().get(gestor.buscarProceso(gestor.getListaProcesosNuevo().get(i).getIdentificadorProceso(), gestor.getListaProcesosListo())).toString() + "----> 'NUEVO' a 'LISTO'");
						mostrarInformacionEstados( gestor.getListaProcesosListo().get(gestor.buscarProceso(gestor.getListaProcesosNuevo().get(i).getIdentificadorProceso(), gestor.getListaProcesosListo())).toString(), "estadoNuevo_Listo" );
						gestor.getListaProcesosNuevo().remove(i);
						i=-1;
						try {
							Thread.sleep(pausa);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}else {
						procesoNuevo();
					}
				}
			}
		}		
	}
	public void estadoListo_Ejecucion() {//funcion que pasará procesos desde "LISTO" a "EJECUCION"
		int i=-1; //indice del proceso en la lista "LISTO"
		while (gestor.getListaProcesosEjecutando().size() < gestor.getMaximoProcesos()) {//cantidad de procesos que estarán el la lista de "EJECUCION"
			if (gestor.hayPrioridad(1, gestor.getListaProcesosListo())) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
				i = gestor.buscarPrioridad(1, gestor.getListaProcesosListo());//almacena el indice del elemento de la lista con la prioridad buscada
				gestor.getListaProcesosEjecutando().add(gestor.getListaProcesosListo().get(i));//añadimos el proceso a la lista de "EJECUCION"
				gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(gestor.getListaProcesosListo().get(i).getIdentificadorProceso(), gestor.getListaProcesosEjecutando())).setEstadoProceso(2);;
				//System.out.println("se ha pasado el proceso: " +  gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(gestor.getListaProcesosListo().get(i).getIdentificadorProceso(), gestor.getListaProcesosEjecutando())).toString() + "----> 'LISTO' a 'EJECUCION'");
				mostrarInformacionEstados(gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(gestor.getListaProcesosListo().get(i).getIdentificadorProceso(), gestor.getListaProcesosEjecutando())).toString(), "estadoListo_Ejecucion");
				gestor.getListaProcesosListo().remove(i);//removemos el proceso de LISTO
				i = -1;//reiniciamos el contador
				try {
					Thread.sleep(pausa);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				if (gestor.hayPrioridad(2, gestor.getListaProcesosListo())) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
					i = gestor.buscarPrioridad(2, gestor.getListaProcesosListo());//almacena el indice del elemento de la lista con la prioridad buscada
					gestor.getListaProcesosEjecutando().add(gestor.getListaProcesosListo().get(i));//añadimos el proceso a la lista de "EJECUCION"
					gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(gestor.getListaProcesosListo().get(i).getIdentificadorProceso(), gestor.getListaProcesosEjecutando())).setEstadoProceso(2);;
					//System.out.println("se ha pasado el proceso: " +  gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(gestor.getListaProcesosListo().get(i).getIdentificadorProceso(), gestor.getListaProcesosEjecutando())).toString() + "----> 'LISTO' a 'EJECUCION'");
					mostrarInformacionEstados( gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(gestor.getListaProcesosListo().get(i).getIdentificadorProceso(), gestor.getListaProcesosEjecutando())).toString(), "estadoListo_Ejecucion");
					gestor.getListaProcesosListo().remove(i);//removemos el proceso de LISTO
					i = -1;//reiniciamos el contador;
					try {
						Thread.sleep(pausa);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					if (gestor.hayPrioridad(3, gestor.getListaProcesosListo())) {//Corrobora si existen procesos con esa maxima prioridad en la lista para pasarlos a "EJECUCION"
						i = gestor.buscarPrioridad(3, gestor.getListaProcesosListo());//almacena el indice del elemento de la lista con la prioridad buscada
						gestor.getListaProcesosEjecutando().add(gestor.getListaProcesosListo().get(i));//añadimos el proceso a la lista de "EJECUCION"
						gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(gestor.getListaProcesosListo().get(i).getIdentificadorProceso(), gestor.getListaProcesosEjecutando())).setEstadoProceso(2);;
						//System.out.println("se ha pasado el proceso: " +  gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(gestor.getListaProcesosListo().get(i).getIdentificadorProceso(), gestor.getListaProcesosEjecutando())).toString() + "----> 'LISTO' a 'EJECUCION'");
						mostrarInformacionEstados( gestor.getListaProcesosEjecutando().get(gestor.buscarProceso(gestor.getListaProcesosListo().get(i).getIdentificadorProceso(), gestor.getListaProcesosEjecutando())).toString(), "estadoListo_Ejecucion");
						gestor.getListaProcesosListo().remove(i);//removemos el proceso de LISTO
						i = -1;//reiniciamos el contador
						try {
							Thread.sleep(pausa);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else {
						estadoNuevo_Listo();
					}
				}
			}
		}
	}
	private boolean EstadoEjecucion_Terminado(AtributosProceso proceso) {//funcion  que añade procesos a la lista de terminados y retorna un verdadero si el proceso se termina y falso si no.
		if(proceso.getInstruccionesLeidas() == Integer.parseInt(proceso.getCantidadInstrucciones())) {//compara las instrucciones leídas con las instrucciones totales para determinar si el proceso ha finalizado
			gestor.getListaProcesosTerminado().add(proceso);
			gestor.getListaProcesosTerminado().get(gestor.buscarProceso(proceso.getIdentificadorProceso(), gestor.getListaProcesosTerminado())).setEstadoProceso(4);
			//System.out.println("el proceso " + proceso.toString() + " ha FINALIZADO");
			mostrarInformacionEstados( proceso.toString(), "EstadoEjecucion_Terminado" );
			gestor.getListaProcesosEjecutando().remove(proceso);
			try {
				Thread.sleep(pausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	private boolean estadoEjecucion_Bloqueado (String instruccionBloqueo, String ID) {//funcion que revisará si el proceso llego a la instruccion de bloqueo y hace la transicion 
		if(gestor.getListaProcesosEjecutando().get(gestor.buscarIndice(ID, gestor.getListaProcesosEjecutando())).getInstruccionesLeidas() == Integer.parseInt(instruccionBloqueo)) {
			gestor.getListaProcesosBloqueado().add(gestor.getListaProcesosEjecutando().get(gestor.buscarIndice(ID, gestor.getListaProcesosEjecutando())));
			gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())).setEstadoProceso(3);
			//System.out.println("se ha pasado el proceso: " +  listaProcesosBloqueado.get(gestor.buscarIndice(ID, listaProcesosBloqueado)).toString() + "----> 'EJECUCION' a 'BLOQUEADO'");
			mostrarInformacionEstados( gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())).toString(), "estadoEjecucion_Bloqueado" );
			gestor.getListaProcesosEjecutando().remove(gestor.buscarIndice(ID, gestor.getListaProcesosEjecutando()));
			try {
				Thread.sleep(pausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
	public void estadoBloqueado_Listo(String ID) {//Función que determina si un proceso cumplió con los ciclos respectivos para estar en la lista de "BLOQUEO" y los pasa a "LISTO"
		if(gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())).getEventoBloqueo() == 3) {
			if(gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())).getCiclosEnBloqueo() == 13) {
				gestor.getListaProcesosListo().add(gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())));
				gestor.getListaProcesosListo().get(gestor.buscarIndice(ID, gestor.getListaProcesosListo())).setEstadoProceso(1);
				//System.out.println("se ha pasado el proceso: " +  gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())).toString() + "----> 'BLOQUEADO' A 'LISTO' 13-CICLOS");
				mostrarInformacionEstados( gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())).toString(), "estadoBloqueado_Listo" );
				gestor.getListaProcesosBloqueado().remove(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado()));
				try {
					Thread.sleep(pausa);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else {
			if(gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())).getCiclosEnBloqueo() == 27) {
				gestor.getListaProcesosListo().add(gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())));
				gestor.getListaProcesosListo().get(gestor.buscarIndice(ID, gestor.getListaProcesosListo())).setEstadoProceso(1);
				//System.out.println("se ha pasado el proceso: " +  gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())).toString() + "----> 'BLOQUEADO' A 'LISTO' 27-CICLOS");
				mostrarInformacionEstados( gestor.getListaProcesosBloqueado().get(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado())).toString(), "estadoBloqueado_Listo" );
				gestor.getListaProcesosBloqueado().remove(gestor.buscarIndice(ID, gestor.getListaProcesosBloqueado()));
				try {
					Thread.sleep(pausa);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void actionPerformed( ActionEvent evento ) {
		if ( evento.getSource() == btnEmpezar ){
			empezar();
		}
	}
}
