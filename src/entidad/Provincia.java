package entidad;

public class Provincia {
	
	private int IDProvincia;
	private String Descripcion;
	
	public Provincia() {
		
	}

	public Provincia(int iDProvincia, String descripcion) {
		super();
		IDProvincia = iDProvincia;
		Descripcion = descripcion;
	}
	
	public Provincia(int IDProvincia) {
		this.IDProvincia = IDProvincia;
	}


	public int getIDProvincia() {
		return IDProvincia;
	}

	public void setIDProvincia(int iDProvincia) {
		IDProvincia = iDProvincia;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Provincia [IDProvincia=" + IDProvincia + ", Descripcion=" + Descripcion + "]";
	}
	
	

}
