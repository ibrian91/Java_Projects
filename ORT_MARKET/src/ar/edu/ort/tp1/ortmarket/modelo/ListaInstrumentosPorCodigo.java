package ar.edu.ort.tp1.ortmarket.modelo;

import ar.edu.ort.tp1.ortmarket.instrumentos.modelo.InstrumentoFinanciero;
import ar.edu.ort.tp1.tdas.implementaciones.ListaOrdenadaNodos;

public class ListaInstrumentosPorCodigo extends ListaOrdenadaNodos<String, InstrumentoFinanciero> {

	@Override
	public int compare(InstrumentoFinanciero dato1, InstrumentoFinanciero dato2) {
		// TODO Auto-generated method stub
		return dato1.getCodigo().compareTo(dato2.getCodigo());
	}

	@Override
	public int compareByKey(String clave, InstrumentoFinanciero elemento) {
		// TODO Auto-generated method stub
		return clave.compareTo(elemento.getCodigo());
	}

	public void listar() {
		System.out.println();
		System.out.println("-------------Lista de Instrumentos-------------");
		for (InstrumentoFinanciero instrumento : this) {
			System.out.println(instrumento.obtenerDatos());
		}
		
	}
	
	
	

}