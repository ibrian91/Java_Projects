package tp1.parcial1.clases;

public class Alineacion extends Servicio implements CotizablePorManoObra {

	static final double EXTRABALANCEO = 500;
	private int cantidadDeHoras;
	private boolean conBalanceo;
	
/*	todo completar */
	
	public Alineacion(String descripcion, double porcentajeGanancia,String patente, 
			int cantidadDeHoras, boolean conBalanceo) {
		
		super(descripcion, porcentajeGanancia, patente);
		this.cantidadDeHoras=cantidadDeHoras;
		this.conBalanceo=conBalanceo;
	}



	@Override
	public double calcularCostoHoras() {
		
		return this.cantidadDeHoras * CotizablePorManoObra.COSTOHORA;
	}

	@Override
	public double calcularPrecioCosto() {
		double costo = 0;
		if (this.conBalanceo = true) {
			costo = (this.calcularCostoHoras()) + EXTRABALANCEO;
		}
		return costo;
	}
	

}
