package ar.edu.ort.a2019.c2.tp1.p2.clases;

public abstract class Producto implements Mostrable {

	
	private static final String MSG_LA_DESCRIPCION_NO_PUEDE_SER_NULO = "La descripci�n no puede ser nulo";
	private static final String MSG_EL_CODIGO_NO_PUEDE_SER_NULO = "El codigo no puede ser nulo";
	private static final String MSG_EL_NOMBRE_NO_PUEDE_SER_NULO = "El nombre no puede ser nulo";
	private String codigo;
	private String descripcion;
	private String nombre;
	
	public Producto(String codigo,String descripcion, String nombre) {
		this.setCodigo(codigo);
		this.setDescripcion(descripcion);
		this.setNombre(nombre);
	}
	
	private void setNombre(String nombre) {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException(MSG_EL_NOMBRE_NO_PUEDE_SER_NULO);
		}
		this.nombre = nombre;
		
	}

	private void setDescripcion(String descripcion) {
		if (descripcion == null || descripcion.isEmpty()) {
			throw new IllegalArgumentException(MSG_LA_DESCRIPCION_NO_PUEDE_SER_NULO);
		}
		this.descripcion = descripcion;
		
	}

	private void setCodigo(String codigo) {
		if (codigo == null || codigo.isEmpty()) {
			throw new IllegalArgumentException(MSG_EL_CODIGO_NO_PUEDE_SER_NULO);
		}
		this.codigo = codigo;

	}

	


	protected abstract void etiquetar(TipoProducto tipoProducto);

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
