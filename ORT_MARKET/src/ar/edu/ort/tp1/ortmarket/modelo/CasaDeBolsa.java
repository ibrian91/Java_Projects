package ar.edu.ort.tp1.ortmarket.modelo;

import java.util.ArrayList;

import ar.edu.ort.tp1.ortmarket.instrumentos.modelo.Bono;
import ar.edu.ort.tp1.ortmarket.instrumentos.modelo.FondoInversion;
import ar.edu.ort.tp1.ortmarket.instrumentos.modelo.InstrumentoFinanciero;
import ar.edu.ort.tp1.tdas.implementaciones.ColaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Cola;
import ar.edu.ort.tp1.tdas.interfaces.ListaOrdenada;

public class CasaDeBolsa {
	private static final int MAXIMO_CLIENTES = 10;
	private  Cliente[] clientes;
	private ListaInstrumentosPorCodigo instrumentos;
	private Cola<Orden>ordenesPendientes;

	public CasaDeBolsa() {
		this.clientes=new Cliente[MAXIMO_CLIENTES];
		instrumentos = new ListaInstrumentosPorCodigo();
		ordenesPendientes = new ColaNodos<>();
		inicializarInstrumentos();
	}

	/**
	 * Metodo para dar de alta a un Cliente en una posicion
	 * 
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param saldoInicial
	 */
	public void altaCliente(int dni, String nombre, String apellido, int saldoInicial) {
		
		try {
			int posicion = this.buscarPosCliente(dni);
			if (posicion != MAXIMO_CLIENTES) {
				System.out.println("Ya se encuentra registrado el cliente");
			}
			posicion = this.buscarPosicionLibre();
			if (posicion == MAXIMO_CLIENTES) {
				System.out.println("No se puede agregar más clientes");
			}
			clientes[posicion] = new Cliente(nombre,apellido,dni,saldoInicial);
			System.out.println("Cliente agregado: " + clientes[posicion].getNombreCompleto());
		} catch (RuntimeException exception) {
			System.out.println(exception.getMessage());
		}
	}

	private int buscarPosicionLibre() {
		int pos = 0;
		while (pos < MAXIMO_CLIENTES && clientes[pos] != null) {
			pos++;
		}
		return pos;
	}

	private int buscarPosCliente(int dni) {
		int pos = 0;
		while (pos < MAXIMO_CLIENTES && (clientes[pos] == null || clientes[pos].getDni() != dni)) {
			pos++;
		}
		return pos;
	}

	/**
	 * Permite cargar ordenes pendientes que luego van a ser procesadas, se debe
	 * validar que el instrumento financiero exista al igual que el cliente
	 * 
	 * @param cantidad
	 * @param codigoInstrumento
	 * @param dniCliente
	 * @param esVenta
	 * @throws Exception
	 */
	public void cargarOrden(int cantidad, String codigoInstrumento, int dniCliente, boolean esVenta) throws RuntimeException {
		int posCliente = this.buscarPosCliente(dniCliente);
		if (posCliente == clientes.length) {
			throw new RuntimeException("El cliente " + dniCliente + " no existe");
		}
		InstrumentoFinanciero instrumento = instrumentos.search(codigoInstrumento);
		if (instrumento == null) {
			throw new RuntimeException("El instrumento " + codigoInstrumento + " no existe");
		}
		ordenesPendientes.add(new Orden(cantidad, instrumento,clientes[posCliente],esVenta));
	}

	public void imprimirHistorialDelCliente() {
		System.out.println("-------------Imprimir Historial por Cliente-------------");

		for (Cliente cliente : clientes) {
			if (cliente != null) {
				cliente.imprimirHistorial();
			}
		}
	}

	public void imprimirListaPrecios() {
		instrumentos.listar();
	}

	private void inicializarInstrumentos() {
		// Metodo creado solo como caso de prueba.
		ArrayList<InstrumentoFinanciero> listaInstrumentos = new ArrayList<>();
		listaInstrumentos.add(new Bono("AL30", "BONO USD 2030 L.A.", "Gobierno Nacional Argentino", 0.75, 160000));
		listaInstrumentos.add(
				new Bono("GD30", "BONOS GLOBALES DE LA REP. ARG. 2030", "Gobierno Nacional Argentino", 0.063, 8965555));
		listaInstrumentos.add(new Bono("YPFD", "BONO USD 2030 L.A.", "Gobierno Nacional Argentino", 0.75, 160000));

		FondoInversion fi = new FondoInversion("SE", "Superfondo Equilibrado", "Banco Deplaza", 10000);

		for (InstrumentoFinanciero instrumentoFinanciero : listaInstrumentos) {
			instrumentos.add(instrumentoFinanciero);
			fi.agregarInstrumento(instrumentoFinanciero);
		}		
		instrumentos.add(fi);
	}

	/**
	 * Metodo que tiene que procesar todas las ordenes pendiente de la Casa de
	 * Bolsa
	 */
	public void procesarOrdenesDeClientes() {
		
		while (!ordenesPendientes.isEmpty()) {
			Orden orden = ordenesPendientes.remove();
			Cliente cliente =orden.getCliente();
			cliente.procesarOrden(orden);
		}
		System.out.println("Ordenes procesadas");
		
		
	}
	
	
	
	
}
