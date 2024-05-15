package tp1.ort.edu.ar.modelos;

import java.util.ArrayList;
import java.util.List;

public class Servicio implements Consumible, Identificable {

	private static final String CANTIDAD_DE_SERVICIO_INVALIDO = "Cantidad De Servicio Invalido";
	private static final String NOMBRE_DE_SERVICIO_INVALIDO = "Nombre De Servicio Invalido";
	private static final String CANTIDAD_MAXIMA_DE_MENSAJES_CONSUMIDOS_ALCANZADA = "Cantidad Maxima de Mensajes consumidos alcanzada!";
	private static final String MSG_PROCESAMIENTO_RESULTADO = "%s procesa msg %s resultado %s %n";
	
	private int id;
	private String nombre;
	private static int contador = 0;
	private int cantidadMaximaMensajes;
	private ArrayList<Prioridad> mensajesPermitidos;
	private int mensajesConsumidos;


	public Servicio(String nombre, int cantidad, List<Prioridad> lista) {
			this.setNombre(nombre);
			this.setCantidad(cantidad);
			this.setPrioridades(lista);
			this.id = contador++;

	}

	
	private void setPrioridades(List<Prioridad> lista) {
		mensajesPermitidos = new ArrayList<>();
		if (lista != null) {
			for (Prioridad grupo : lista) {
				mensajesPermitidos.add(grupo);
			}
		}
		
	}


	private void setCantidad(int cantidad) {
		if (cantidad < 0 || cantidad > 1000) {
			throw new IllegalArgumentException(CANTIDAD_DE_SERVICIO_INVALIDO);
		}
		this.cantidadMaximaMensajes = cantidad;
		
	}


	private void setNombre(String nombre) {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException(NOMBRE_DE_SERVICIO_INVALIDO);
		}
		this.nombre = nombre;
		
	}


	public Respuesta consumir(Mensaje mensaje) throws RuntimeException {
		Respuesta res=null;
		if (this.puedeConsumidorMensajes()) {
			res = mensaje.ejecutar(this);
			this.mensajesConsumidos++;
			System.out.printf(MSG_PROCESAMIENTO_RESULTADO,this.nombre, mensaje.getId(), res);
		} else {
			throw new RuntimeException(CANTIDAD_MAXIMA_DE_MENSAJES_CONSUMIDOS_ALCANZADA);
		}
		return res;
		
	}


	private boolean puedeConsumidorMensajes() {
		return this.mensajesConsumidos < cantidadMaximaMensajes;
		
	}


	@Override
	public int getId() {
		
		return this.id;
	}


	@Override
	public boolean sameId(int id) {
		
		 return this.id == id;
	}


	public int getCantidadMensajesDisponibles() {
		return cantidadMaximaMensajes - this.mensajesConsumidos;
	}


	public Integer getCantidadDeMensajesDisponible() {
		return cantidadMaximaMensajes - this.mensajesConsumidos;
	}

	public boolean prioridadAceptada(Prioridad prioridad) {
		return mensajesPermitidos.contains(prioridad);
	}
	
	@Override
	public String toString() {
		return "Servicio [nombre=" + nombre + ", mensajesConsumidos=" + mensajesConsumidos + ", cantidadMaximaMensajes="
				+ cantidadMaximaMensajes + ", id=" + id + "]";
	}
	
}
