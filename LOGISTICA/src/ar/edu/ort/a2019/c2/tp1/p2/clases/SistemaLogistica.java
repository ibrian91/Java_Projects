package ar.edu.ort.a2019.c2.tp1.p2.clases;

import ar.edu.ort.a2019.c2.tp1.p2.tad.Cola;
import ar.edu.ort.a2019.c2.tp1.p2.tad.Pila;
import ar.edu.ort.a2019.c2.tp1.p2.tad.implementaciones.nodo.ColaNodos;
import ar.edu.ort.a2019.c2.tp1.p2.tad.implementaciones.nodo.PilaNodos;

public class SistemaLogistica implements Mostrable {

	
	private static final String MSG_PRODUCTOS_ESTANDAR_LLENO="El deposito estandar esta lleno";
	private static final String MSG_PRODUCTOS_FRAGIL_LLENO="El deposito fragil esta lleno";
	private static final String MSG_PRODUCTO_NO_ENCONTRADO="Producto no encontrado";
	private static final String MSG_EL_CODIGO_NO_PUEDE_SER_NULO = "El codigo no puede ser nulo";
	private static final String MSG_TIPO_DE_PRODUCTO_INVALIDO = "Tipo de producto invalido";
	private static final String MSG_EL_PRODUCTO_NO_PUEDE_SER_NULO = "El producto no puede ser nulo";
	private static final String MSG_TAMANIO_DE_DEPOSITO_FRAGIL_INVALIDO = "Tamaño de deposito fragil invalido";
	private static final String MSG_TAMANIO_DE_DEPOSITO_ESTANDAR_INVALIDO = "Tamaño de deposito estandar invalido";
	private int stockMaximoEstandar;
	private int stockMaximoFragil;
	private int cantidadIngresadaFragil;
	private int cantidadIngresadaEstandar;
	private Pila<Producto> productosEstandar; // revisar bien esto el tipo de produto
	private Pila<Producto> productosFragiles;
	private int cantidadProductosRetirados;
	private Cola<Producto> productosRetirados;
	
	


	public SistemaLogistica(int stockMaximoEstandar, int stockMaximoFragil) throws IllegalArgumentException {
		this.setTamanioDepositoFragil(stockMaximoFragil);
		this.setTamanioDepositoEstandar(stockMaximoEstandar);
		this.productosEstandar= new PilaNodos<>();
		this.productosFragiles= new PilaNodos<>();
		this.productosRetirados = new ColaNodos<>();
	}

	private void setTamanioDepositoEstandar(int stockMaximoEstandar) {
		if (stockMaximoEstandar < 1) {
			throw new IllegalArgumentException(MSG_TAMANIO_DE_DEPOSITO_ESTANDAR_INVALIDO);
		}
		this.stockMaximoEstandar = stockMaximoEstandar;
	}

	private void setTamanioDepositoFragil(int stockMaximoFragil) {
		if (stockMaximoFragil < 1) {
			throw new IllegalArgumentException(MSG_TAMANIO_DE_DEPOSITO_FRAGIL_INVALIDO);
		}
		this.stockMaximoFragil = stockMaximoFragil;
	}


	public void ingresarProducto(Producto p ) throws RuntimeException {
		if (p == null) {
			throw new RuntimeException(MSG_EL_PRODUCTO_NO_PUEDE_SER_NULO);
		}
		
		this.validarCantidades(p);
		
		
		if (p instanceof ProductoEstandar) {
			this.productosEstandar.push(p);
			cantidadIngresadaEstandar++;
			p.etiquetar(TipoProducto.ESTANDAR);
		}
		else if ( p instanceof ProductoFragil) {
			this.productosFragiles.push(p);
			cantidadIngresadaFragil++;
			p.etiquetar(TipoProducto.FRAGIL);
		}
		
		
	}

	private void validarCantidades(Producto p) {
		if (p instanceof ProductoEstandar) {
			if (this.cantidadIngresadaEstandar == this.stockMaximoEstandar) {
				throw new IllegalArgumentException(MSG_PRODUCTOS_ESTANDAR_LLENO);
			}
		}
		if (p instanceof ProductoFragil) {
			if (this.cantidadIngresadaFragil == this.stockMaximoFragil) {
				throw new IllegalArgumentException(MSG_PRODUCTOS_FRAGIL_LLENO);
			}
		}
		

	}



	/**
	 * Retira un producto en base a su tipo y c�digo
	 * @param codigo Del producto a retirar
	 * @param tipo Tipo del producto a retirar
	 * @return el producto retirado
	 * @throws IllegalArgumentException en caso de que el c�digo o el tipo sean nulos
	 * @throws RuntimeException En caso de no encontrar el producto buscado.
	 */
	public Producto retirarProductoSeleccionado(String codigo, TipoProducto tipo) throws IllegalArgumentException, RuntimeException {
		this.realizarValidaciones(codigo,tipo);
		
	    Pila<Producto> pila;
	    if (tipo == TipoProducto.ESTANDAR) {
	        pila = productosEstandar;
	      
	    } else {
	        pila = productosFragiles;
	        
	    }

	    Pila<Producto> pilaAuxiliar = new PilaNodos<>();
	    Producto productoRetirado = null;

	    while (!pila.isEmpty()) {
	        Producto productoActual = pila.pop();
	        if (!productoActual.getCodigo().equals(codigo)) {
	            pilaAuxiliar.push(productoActual);
	        } else {
	            productoRetirado = productoActual;
	        }
	    }

	    // Restauramos el orden original
	    while (!pilaAuxiliar.isEmpty()) {
	        pila.push(pilaAuxiliar.pop());
	    }

	    cantidadProductosRetirados++;
	    productosRetirados.add(productoRetirado);
	    
	    return productoRetirado;
	}
	
	

	private void realizarValidaciones(String codigo, TipoProducto tipo) {
		if (codigo == null) {
			throw new IllegalArgumentException(MSG_EL_CODIGO_NO_PUEDE_SER_NULO);
		}
		if (tipo == null) {
			throw new IllegalArgumentException(MSG_TIPO_DE_PRODUCTO_INVALIDO);
		}
		// voy a usar el tipo de producto para buscar en la cola correcta y no duplicar codigo
		Producto producto = this.buscarProducto(codigo,tipo);
		if (producto == null) {
			throw new RuntimeException(MSG_PRODUCTO_NO_ENCONTRADO);
		}
		
		
	}

	private Producto buscarProducto(String codigo, TipoProducto tipo) {
		Pila<Producto> pila = null;

		if (tipo == TipoProducto.ESTANDAR) {
			pila = productosEstandar;
		} else if (tipo == TipoProducto.FRAGIL) {
			pila = productosFragiles;
		}

		// Recorremos la pila para buscar el producto
		Pila<Producto> pilaAuxiliar = new PilaNodos<>();
		Producto productoEncontrado = null;

		while (!pila.isEmpty()) {
			Producto productoActual = pila.pop();
			pilaAuxiliar.push(productoActual);

			if (productoActual.getCodigo().equals(codigo)) {
				productoEncontrado = productoActual;
			}
		}

		while (!pilaAuxiliar.isEmpty()) {
			pila.push(pilaAuxiliar.pop());
		}

		return productoEncontrado;
	}

	@Override
	public void mostrar() {
		System.out.println("Se han retirado: " + cantidadProductosRetirados);
		Producto productoCent = null;
		this.productosRetirados.add(productoCent);
		Producto producto = this.productosRetirados.remove();
		
		while(producto != productoCent) {
			
			System.out.print("* " );
			producto.mostrar(); 
			this.productosRetirados.add(producto);
			producto = this.productosRetirados.remove();
		}
		
		System.out.println("Se ingresaron " + this.getCantidadIngresadaEstandar() + " Productos Estandar y se ingresaron " + this.getCantidadIngresadaFragil() + " productos fragiles");
		
		
		
	}

	public int getCantidadIngresadaEstandar() {
		return cantidadIngresadaEstandar;
	}

	
	public int getCantidadIngresadaFragil() {
		return this.cantidadIngresadaFragil;
	}
	



}
