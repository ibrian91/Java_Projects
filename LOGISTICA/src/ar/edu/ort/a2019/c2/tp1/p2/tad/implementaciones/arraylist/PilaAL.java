package ar.edu.ort.a2019.c2.tp1.p2.tad.implementaciones.arraylist;

import java.util.ArrayList;

import ar.edu.ort.a2019.c2.tp1.p2.tad.Pila;

public class PilaAL<TipoElemento> implements Pila<TipoElemento> {

	private ArrayList<TipoElemento> elementos;
	private int limite;

	public PilaAL() {
		this(SIN_LIMITE);
	}

	public PilaAL(int limite) {
		if (limite != SIN_LIMITE && limite < LIMITE_MINIMO_POSIBLE) {
			throw new IllegalArgumentException(ERR_TAM_PILA_ILEGAL);
		}
		this.limite = limite;
		this.elementos = new ArrayList<>();
	}

	@Override
	public void push(TipoElemento elemento) {
		if (isFull()) {
			throw new RuntimeException(ERR_PILA_LLENA);
		}
		elementos.add(elemento);
	}

	@Override
	public TipoElemento pop() {
		if (isEmpty()) {
			throw new RuntimeException(ERR_PILA_VACIA);
		}
		return elementos.remove(elementos.size() - 1);
	}

	@Override
	public TipoElemento peek() {
		if (isEmpty()) {
			throw new RuntimeException(ERR_PILA_VACIA);
		}
		return elementos.get(elementos.size() - 1);
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