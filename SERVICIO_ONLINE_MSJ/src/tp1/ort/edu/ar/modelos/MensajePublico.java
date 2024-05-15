package tp1.ort.edu.ar.modelos;

public class MensajePublico extends Mensaje {
	
	

	public MensajePublico() {
		super(Prioridad.NO_CRITICOS);
		
	}

	@Override
	public Respuesta ejecutar(Servicio srv) {
		
		return Respuesta.OK;
	}

}
