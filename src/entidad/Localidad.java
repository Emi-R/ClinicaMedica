package entidad;

public class Localidad {
	
	private int IDLocalidad;
	private String Descripcion;
	
	public Localidad () {
		
	}

	public Localidad(int iDLocalidad, String descripcion) {
		super();
		IDLocalidad = iDLocalidad;
		Descripcion = descripcion;
	}
	
	public Localidad(int IDLocalidad) {
		this.IDLocalidad = IDLocalidad;
	}

	public int getIDLocalidad() {
		return IDLocalidad;
	}

	public void setIDLocalidad(int iDLocalidad) {
		IDLocalidad = iDLocalidad;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Localidad [IDLocalidad=" + IDLocalidad + ", Descripcion=" + Descripcion + "]";
	}
	
	

}
