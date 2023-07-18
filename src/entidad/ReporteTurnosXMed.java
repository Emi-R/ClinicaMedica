package entidad;

public class ReporteTurnosXMed {
	
	private String nombre;	
	private String apellido;
	private int cantidad;
	
	public ReporteTurnosXMed(String nombre, String apellido, int cantidad) {
		super();
	}

	public ReporteTurnosXMed() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
