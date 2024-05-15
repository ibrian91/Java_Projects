package ar.edu.ort.a2019.c2.tp1.p2.clases;

public class ProductoFragil extends Producto {
	
	private static final String ETIQUETA = "Producto Fragil tretese con cuidado";
	private TipoProducto etiqueta;
	
	public ProductoFragil(String codigo, String descripcion, String nombre) {
		super(codigo, descripcion, nombre);
		etiqueta=null;
	}



	public String valorEtiqueta() {
		return ETIQUETA;
	}



	@Override
	protected void etiquetar(TipoProducto tipoProducto) {
		this.etiqueta=tipoProducto;
		
	}



	@Override
	public void mostrar() {
		System.out.println(this.getClass().getSimpleName() + " [Codigo: " + super.getCodigo() + ",Nombre: "
				+ super.getNombre() + ", Descripcion:" + super.getDescripcion() + ",Etiqueta:" + this.valorEtiqueta());
		
	}



	public TipoProducto getEtiqueta() {
		return etiqueta;
	}

	


}
