package procesos;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class GuardarEstadoEjecucionProcesos {
	private ObjectOutputStream guardarEstadoEjecucion;
	private ObjectInputStream recuperarEstadoEjecucion;
	
	public void guardarEstadoEjecucion(ArrayList<AtributosProceso> procesos, String estado) {
		try {
			guardarEstadoEjecucion = new ObjectOutputStream(new FileOutputStream("estadoProcesos"+estado+".quebin"));
			guardarEstadoEjecucion.writeObject(procesos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<AtributosProceso> recuperarEstadoEjecucion(String estado) {
		ArrayList<AtributosProceso> procesos = null;
		try {
		recuperarEstadoEjecucion = new ObjectInputStream(new FileInputStream("estadoProcesos"+estado+".quebin"));
		procesos = (ArrayList<AtributosProceso>) recuperarEstadoEjecucion.readObject();
		
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return procesos;
	}
}
