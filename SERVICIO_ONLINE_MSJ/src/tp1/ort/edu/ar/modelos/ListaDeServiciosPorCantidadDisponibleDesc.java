package tp1.ort.edu.ar.modelos;

import ar.edu.ort.tp1.tdas.implementaciones.ListaOrdenadaNodos;

public class ListaDeServiciosPorCantidadDisponibleDesc extends ListaOrdenadaNodos<Integer, Servicio> {

	@Override
	public int compare(Servicio dato1, Servicio dato2) {
		
		return dato2.getCantidadMensajesDisponibles() - dato1.getCantidadMensajesDisponibles();
	}

	@Override
	public int compareByKey(Integer clave, Servicio elemento) {
		
		return  elemento.getCantidadDeMensajesDisponible() - clave;
	}

	
	
	
}
