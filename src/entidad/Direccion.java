package entidad;

public class Direccion {
	
	private String Calle;
	private int Numero;
	private Localidad Localidad;
	private Provincia Provincia;
	
	public Direccion() {
		this.Localidad = new Localidad();
		this.Provincia = new Provincia ();
		Calle  = "";
		}
	
	public Direccion(String calle, int numero, Localidad localidad, Provincia provincia) {
		Calle = calle;
		Numero = numero;
		Localidad = localidad;
		Provincia = provincia;
	}

	public String getCalle() {
		return Calle;
	}

	public void setCalle(String calle) {
		Calle = calle;
	}

	public int getNumero() {
		return Numero;
	}

	public void setNumero(int numero) {
		Numero = numero;
	}

	public Localidad getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(Localidad localidad) {
		Localidad = localidad;
	}

	public Provincia getProvincia() {
		return Provincia;
	}

	public void setProvincia(Provincia provincia) {
		Provincia = provincia;
	}

	@Override
	public String toString() {
		return "Direccion [Calle=" + Calle + ", Numero=" + Numero + ", Localidad=" + Localidad + ", Provincia="
				+ Provincia + "]";
	}
	
	
}

