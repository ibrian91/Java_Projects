package tp1.parcial1.clases;

public abstract class Servicio implements Detallable {
	
	
	
	private String descripcion;
	private double porcentajeGanancia;
	private String patente;
	private final static String MENSAJE = "servicio de: %s\nPatente %s Precio: %s\n";


	public Servicio(String descripcion, double porcentajeGanancia, String patente) {
		this.descripcion=descripcion;
		this.porcentajeGanancia=porcentajeGanancia;
		this.patente=patente;
	}

	public abstract double calcularPrecioCosto();
	
	public double calcularPrecioVenta() {
		double costo = this.calcularPrecioCosto();
		return costo+(this.porcentajeGanancia*costo/100);
	}

	public String getDescripcion() {
		
		return this.descripcion;
	}
	
	public void detallar() {
	System.out.printf(MENSAJE,this.getClass().getSimpleName(),this.getPatente(),this.calcularPrecioVenta());
}

	private double getPorcentajeGanancia() {
		return porcentajeGanancia;
	}

	public void setPorcentajeGanancia(double porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}

	public String getPatente() {
		return this.patente;
	}
	
	

	
}
