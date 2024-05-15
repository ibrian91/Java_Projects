package ar.edu.ort.a2019.c2.tp1.p2.tad.implementaciones.arraylist;

import java.util.ArrayList;

import ar.edu.ort.a2019.c2.tp1.p2.tad.Cola;

public class ColaAL<TipoElemento> implements Cola<TipoElemento> {

	private ArrayList<TipoElemento> elementos;
	private int limite;

	public ColaAL() {
		this(SIN_LIMITE);
	}

	public ColaAL(int limite) {
		if (limite != SIN_LIMITE && limite < LIMITE_MINIMO_POSIBLE) {
			throw new IllegalArgumentException(ERR_TAM_ILEGAL);
		}
		this.limite = limite;
		this.elementos = new ArrayList<>();
	}
	
	@Override
	public void add(TipoElemento elemento) {
		if (isFull()) {
			throw new RuntimeException(ERR_COLA_LLENA);
		}
		elementos.add(elemento);
	}

	@Override
	public TipoElemento remove() {
		if (isEmpty()) {
			throw new RuntimeException(ERR_COLA_VACIA);
		}
		return elementos.remove(0);
	}

	@Override
	public TipoElemento get() {
		if (isEmpty()) {
			throw new RuntimeException(ERR_COLA_VACIA);
		}
		return elementos.get(0);
	}

	@Override
	public boolean isEmpty() {
		return elementos.isEmpty();
	}

	@Override
	public boolean isFull() {
		return elementos.size() == limite;
	}
}