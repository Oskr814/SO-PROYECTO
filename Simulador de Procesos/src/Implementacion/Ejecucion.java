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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	
	private JTextField txtCicloProcesador;
	
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
		
		JLabel lblInstruccin = new JLabel("Instrucci\u00F3n:");
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
		
		JButton btnEmpezar = new JButton("Empezar");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				empezar();
			}
		});
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
	
	public JLabel getLblObservacion() {
		return lblObservacion;
	}
	public void setLblObservacion(JLabel lblObservacion) {
		this.lblObservacion = lblObservacion;
	}
	public void empezar(){
		
	}
}
