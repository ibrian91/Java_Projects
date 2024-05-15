package tp1.ort.edu.ar.modelos;

public class MensajePrivado extends Mensaje {
	
	private int idServDestino;

	public MensajePrivado(Prioridad prioridad,int id) {
		super(prioridad);
		this.idServDestino=id;
	}

	@Override
	public Respuesta ejecutar(Servicio srv) {
		Respuesta resp = Respuesta.ERROR;
		if (srv.getId() == idServDestino ) {
			resp= Respuesta.OK;
		}
		return resp;
	}

}
