package ar.edu.ort.a2019.c2.tp1.p2.tad;

public interface Cola<TipoElemento> {

	String ERR_TAM_ILEGAL = "Tamaño definido ilegal";
	String ERR_COLA_LLENA = "Cola llena";
	String ERR_COLA_VACIA = "Cola vacia";
	
	int SIN_LIMITE = -1;
	int LIMITE_MINIMO_POSIBLE = 2;

	void add(TipoElemento element);

	TipoElemento remove();

	TipoElemento get();

	boolean isEmpty();

	boolean isFull();

}