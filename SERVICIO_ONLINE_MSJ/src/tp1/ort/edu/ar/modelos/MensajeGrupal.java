package tp1.ort.edu.ar.modelos;

public class MensajeGrupal extends Mensaje {

	public MensajeGrupal(Prioridad prioridad) {
		super(prioridad);
		
	}

	@Override
	public Respuesta ejecutar(Servicio srv) {
		Respuesta res = Respuesta.ERROR;
		if(srv.prioridadAceptada(getPrioridad())) {
			res = Respuesta.OK;
		}
		return res;
	}

}
