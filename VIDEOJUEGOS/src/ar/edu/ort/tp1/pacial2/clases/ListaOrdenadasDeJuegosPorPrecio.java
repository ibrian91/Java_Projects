package ar.edu.ort.tp1.pacial2.clases;

import ar.edu.ort.tp1.tdas.implementaciones.ListaOrdenadaNodos;

public class ListaOrdenadasDeJuegosPorPrecio extends ListaOrdenadaNodos<Float, Juego> {
    @Override
    public int compare(Juego dato1, Juego dato2) {
        return this.compareByKey(dato1.getPrecioDeVenta(), dato2);
    }

    @Override
    public int compareByKey(Float clave, Juego elemento) {
        return Float.compare(clave, elemento.getPrecioDeVenta());
    }
}
