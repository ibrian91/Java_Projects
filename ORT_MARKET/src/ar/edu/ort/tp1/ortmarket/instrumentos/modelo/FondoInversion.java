package ar.edu.ort.tp1.ortmarket.instrumentos.modelo;

import ar.edu.ort.tp1.ortmarket.modelo.ListaInstrumentosPorCodigo;

public class FondoInversion extends InstrumentoFinanciero {

	private int nroCuotaPartes;
	private ListaInstrumentosPorCodigo instrumentos;

	public FondoInversion(String codigo, String nombre, String emisor, int nroCuotaPartes) {
		super(codigo, nombre, emisor);
		this.nroCuotaPartes = nroCuotaPartes;
		this.instrumentos = new ListaInstrumentosPorCodigo();
	}


	public void agregarInstrumento(InstrumentoFinanciero instrumentoFinanciero) {
		instrumentos.add(instrumentoFinanciero);
	}

	@Override
	public double obtenerPrecio() {
		double suma =this.sumarPrecioLista();
		return suma ;
	}


	private double sumarPrecioLista() {
		double total=0;
		for (InstrumentoFinanciero instrumentoFinanciero : instrumentos) {
			total = total + instrumentoFinanciero.obtenerPrecio();
		}
		return total / nroCuotaPartes;
	}

}
