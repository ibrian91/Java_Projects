package tp1.parcial1.clases;

public class Encendido extends Servicio implements CotizablePorMateriales {

/*	todo completar */	
	
	
	public Encendido(String descripcion, double porcentajeGanancia, String patente) {
		super(descripcion, porcentajeGanancia, patente);
	}


	@Override
	public double calcularCostoMateriales() {
		
		return CotizablePorMateriales.COSTOMATERIAL1 + CotizablePorMateriales.COSTOMATERIAL2;
	}

	@Override
	public double calcularPrecioCosto() {
		
		return this.calcularCostoMateriales();
	}
	
	

}
