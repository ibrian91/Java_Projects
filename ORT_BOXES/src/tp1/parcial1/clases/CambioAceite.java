package tp1.parcial1.clases;

public class CambioAceite extends Servicio implements CotizablePorManoObra, CotizablePorMateriales {
	

	//Pongo atributos
	
	private int cantidadDeHoras;
	
	
	public CambioAceite(String descripcion, double porcentajeGanancia, String patente,int cantidadDeHoras) {
		super(descripcion, porcentajeGanancia, patente);
		this.cantidadDeHoras=cantidadDeHoras;
	}
	
	




	@Override
	public double calcularCostoMateriales() {
		
		return CotizablePorMateriales.COSTOMATERIAL1 + CotizablePorMateriales.COSTOMATERIAL2;
	}



	@Override
	public double calcularCostoHoras() {
		
		return this.cantidadDeHoras * CotizablePorManoObra.COSTOHORA;
	}



	@Override
	public double calcularPrecioCosto() {
		double costo = 0;
		
		costo = this.calcularCostoHoras() + this.calcularCostoMateriales();
		
		return costo;
	}

	

}
