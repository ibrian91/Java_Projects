package tp1.ort.edu.ar.test;

import java.util.ArrayList;

import tp1.ort.edu.ar.modelos.ControladorDeServicios;
import tp1.ort.edu.ar.modelos.Mensaje;
import tp1.ort.edu.ar.modelos.MensajeGrupal;
import tp1.ort.edu.ar.modelos.MensajePrivado;
import tp1.ort.edu.ar.modelos.MensajePublico;
import tp1.ort.edu.ar.modelos.Prioridad;

public class Test {

	public static void main(String[] args) {

		ControladorDeServicios cs = new ControladorDeServicios();

		ArrayList<Prioridad> grupos1 = new ArrayList<>();
		grupos1.add(Prioridad.CRITICOS);

		ArrayList<Prioridad> grupos2 = new ArrayList<>();
		grupos1.add(Prioridad.NO_CRITICOS);

		ArrayList<Prioridad> grupos3 = new ArrayList<>();
		grupos1.add(Prioridad.CRITICOS);
		grupos1.add(Prioridad.MUY_CRITICOS);

		System.out.println("------- Creacion de Servicios ------");
		cs.agregarServicio("Servicio 1", 90, 10, null);
		cs.agregarServicio("Servicio 2", 190, 5, null);
		cs.agregarServicio(null, 34, 1, null);
		cs.agregarServicio("SRV", 34, -1, null);
		cs.agregarServicio("Servicio 3", 290, 3, grupos1);
		cs.agregarServicio("", 34, 1, grupos1);
		cs.agregarServicio("Servicio 4", 290, 5, grupos2);
		cs.agregarServicio("Servicio 5", 290, 9, grupos2);
		cs.agregarServicio("", 34, -1, grupos1);
		cs.agregarServicio("Servicio 6", 1090, 1, grupos3);
		cs.agregarServicio("Servicio 7", 290, 2, grupos3);
		cs.agregarServicio("Servicio 8", 290, 2, grupos1);
		cs.agregarServicio("Servicio 9", 290, 5, grupos2);
		cs.agregarServicio("Servicio 10", 290, 5, grupos2);
		cs.agregarServicio("Servicio 11", 290, 5, grupos2);

		System.out.println("------- Carga de Mensajes ------");
		cs.agregarMensaje(crearMensajePublico());
		cs.agregarMensaje(crearMensajePrivado(Prioridad.CRITICOS, 1));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.NO_CRITICOS, 1));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.CRITICOS, 1));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.CRITICOS, 1));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.MUY_CRITICOS, 2));
		cs.agregarMensaje(crearMensajeGrupal(Prioridad.MUY_CRITICOS));
		cs.agregarMensaje(crearMensajePublico());
		cs.agregarMensaje(crearMensajePublico());
		cs.agregarMensaje(crearMensajePrivado(Prioridad.MUY_CRITICOS, 2));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.MUY_CRITICOS, 2));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.CRITICOS, 1));
		cs.agregarMensaje(crearMensajePublico());
		cs.agregarMensaje(crearMensajePrivado(Prioridad.MUY_CRITICOS, 2));
		cs.agregarMensaje(crearMensajeGrupal(Prioridad.NO_CRITICOS));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.MUY_CRITICOS, 2));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.MUY_CRITICOS, 2));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.CRITICOS, 1));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.CRITICOS, 1));
		cs.agregarMensaje(crearMensajePrivado(Prioridad.MUY_CRITICOS, 2));
		cs.agregarMensaje(crearMensajeGrupal(Prioridad.MUY_CRITICOS));
		cs.agregarMensaje(crearMensajePublico());
		cs.agregarMensaje(crearMensajePublico());
		cs.agregarMensaje(crearMensajePublico());

		System.out.println("------- Procesar Mensajes ------");
		cs.procesar();

		System.out.println("------- Servicios Por Tipo de Respuesta ------");
		cs.mostrarServiciosPorTipoDeRespuesta();

		System.out.println("------- Servicios Por Cantidad De Mensajes Disponibles ------");
		cs.mostrarServiciosPorCantidadDeMensajes();

	}

	public static Mensaje crearMensajePublico() {
		return new MensajePublico();
	}

	public static Mensaje crearMensajePrivado(Prioridad p, int servicioId) {
		Mensaje m=null;
		try {
			m = new MensajePrivado(p, servicioId);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return m;
	}

	public static Mensaje crearMensajeGrupal(Prioridad p) {
		Mensaje m = null;
		try {
			m = new MensajeGrupal(p);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return m;
	}

}
