package entidad;

public class Especialidad {
	
	private int idEspecialidad;
	private String Descripcion;
	
	public Especialidad () {
		
	}

	public Especialidad(int idEspecialidad, String descripcion) {
		this.idEspecialidad = idEspecialidad;
		Descripcion = descripcion;
	}
	
	public Especialidad (int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Especialidad [idEspecialidad=" + idEspecialidad + ", Descripcion=" + Descripcion + "]";
	}
	

}
