package tp1.parcial1.clases;
import java.util.ArrayList;

public class TallerMecanico {

/*	todo completar */	
	private ArrayList<Servicio> serviciosEfectuados;
	private String nombre;
	
	private static final String FORMATO_CANTIDADES = "Se efectuaron: %d cambios de aceite, %d encendido y %d alineacion\n";

	
	public TallerMecanico(String nombre) {
		this.nombre=nombre;
		this.serviciosEfectuados = new ArrayList <>();

	}
	
	public boolean agregarServicio(Servicio nuevoServicio) {
		boolean pudo = false;
		if (nuevoServicio != null) {
			pudo = this.serviciosEfectuados.add(nuevoServicio); 
		}   

		return pudo;
	}
	
	public void listarServicios() {
		System.out.println(this.nombre);
		
		int cantidadCambioDeAceite = 0;
		int cantidadEncendido = 0;
		int cantidadAlineacion = 0;
		double importeTotal=0;
		
		for (Servicio servicio : serviciosEfectuados) {
			if (servicio instanceof CambioAceite ) {
				cantidadCambioDeAceite++;
			}	else if (servicio instanceof Encendido ){
				cantidadEncendido++;
			}   else cantidadAlineacion++;
			
			importeTotal += servicio.calcularPrecioVenta();
		}
		
		System.out.printf(FORMATO_CANTIDADES,cantidadCambioDeAceite,cantidadEncendido,cantidadAlineacion);
		System.out.println("El importe total de ventas es de: $" + importeTotal );
	}
}
