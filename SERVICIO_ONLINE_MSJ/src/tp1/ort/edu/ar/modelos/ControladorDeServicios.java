package tp1.ort.edu.ar.modelos;

import java.util.List;

import ar.edu.ort.tp1.tdas.implementaciones.ColaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Cola;

public class ControladorDeServicios implements Procesable {

	private static final String ERROR_MAXIMA_CANTIDAD_DE_SERVICIOS_ALCANZADA = "ERROR: Maxima cantidad de Servicios alcanzada";
	private static final String ERROR_CARGA_MSJ = "ERROR: El mensaje no puede ser nulo";
	private Cola<Servicio>servicios;
	private Cola<Mensaje>mensajes;
	private ListaDeServiciosPorCantidadDisponibleDesc listaOrdenadaDeServicios;
	private int[][] mensajesPorSevicioYRespuesta;
	
	private static int contadorDeServicios;
    private static final int CANTIDAD_MAXIMA_SERVICIOS = 10;

	public ControladorDeServicios() {
		this.listaOrdenadaDeServicios = new ListaDeServiciosPorCantidadDisponibleDesc();
		this.mensajes = new ColaNodos<>();
		this.servicios = new ColaNodos<>();
		mensajesPorSevicioYRespuesta = new int[CANTIDAD_MAXIMA_SERVICIOS][Respuesta.values().length];
	}

	public void agregarServicio(String nombre, int prioridad, int cantidad, List<Prioridad> grupos) {
	try {
		if (contadorDeServicios>=CANTIDAD_MAXIMA_SERVICIOS ) {
			throw new RuntimeException(ERROR_MAXIMA_CANTIDAD_DE_SERVICIOS_ALCANZADA);
		}
		Servicio srv = new Servicio(nombre,cantidad,grupos);
		this.servicios.add(srv);
		System.out.println(srv + " agregado correctamente");
		contadorDeServicios++;
	} catch(RuntimeException e ) {
		System.out.printf("Error al agregar: ");
		System.out.println(e.getMessage());
	}
	}

	public void agregarMensaje(Mensaje mensaje) {
		try {
			if (mensaje==null) {
				throw new RuntimeException(ERROR_CARGA_MSJ);
			}
			System.out.println(mensaje + " agregado");
			mensajes.add(mensaje);
			
		} catch (RuntimeException e) {
			System.out.printf("Error al agregar: ");
			System.out.println(e.getMessage());
		}

		
	}

	@Override
	public void procesar() {

		while (!mensajes.isEmpty()) {
			Servicio srv = this.servicios.remove();
			Mensaje msg = mensajes.remove();
			Respuesta res = ejecutarMensaje(srv, msg);
			if (res != null) {
				
				mensajesPorSevicioYRespuesta[srv.getId()][res.ordinal()]++;
			}
			this.servicios.add(srv);
		}

	}

	private Respuesta ejecutarMensaje(Servicio servicio, Mensaje msg) {
		Respuesta res = null;
		try {
			res = servicio.consumir(msg);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}

	public void mostrarServiciosPorTipoDeRespuesta() {

		System.out.printf(" %22s %8s %n", Respuesta.OK, Respuesta.ERROR);
		for (int i = 0; i < mensajesPorSevicioYRespuesta.length; i++) {
			System.out.printf("Servicio ID: " + i);
			for (int j = 0; j < mensajesPorSevicioYRespuesta[i].length; j++) {
				System.out.printf(" %7s", mensajesPorSevicioYRespuesta[i][j]);
			}
			System.out.println();
		}
	}

	public void mostrarServiciosPorCantidadDeMensajes() {
		while (!this.servicios.isEmpty()) {
			Servicio srv = this.servicios.remove();
			if (srv != null)
				listaOrdenadaDeServicios.add(srv);
		}

		for (Servicio s : listaOrdenadaDeServicios) {
			System.out.println(s);
		}

	}

}
