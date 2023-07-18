package entidad;

public class TipoUsuario {
	
	private int idTipoUsuario;
	private String Descripcion;
	
	public TipoUsuario() {
		
	}

	public TipoUsuario(int idTipoUsuario, String descripcion) {
		this.idTipoUsuario = idTipoUsuario;
		Descripcion = descripcion;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoUsuario [idTipoUsuario=" + idTipoUsuario + ", Descripcion=" + Descripcion + "]";
	}
	
}
