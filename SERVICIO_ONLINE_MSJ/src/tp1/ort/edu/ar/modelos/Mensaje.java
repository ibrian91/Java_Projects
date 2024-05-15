package tp1.ort.edu.ar.modelos;

public abstract class Mensaje implements Ejecutable {

	
	private static int contador = 0;
	private int id;
	private Prioridad prioridad;
	

	public Mensaje(Prioridad prioridad) {
		if (prioridad == null) {
			throw new IllegalArgumentException("ERROR: Prioridad Nula");
		}
		this.prioridad=prioridad;
		this.setId();
	}

	private void setId() {
		this.id=contador++;
		
	}

	public int getId() {
		return id;
	}

	public Prioridad getPrioridad() {
		return prioridad;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", prioridad=" + prioridad + "]";
	}

	
	
	

}
