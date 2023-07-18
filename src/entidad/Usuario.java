package entidad;

public class Usuario {
	
	private int DNI;
	private String Contraseña;
	private TipoUsuario Tipo;
	private int estado;
	
	public Usuario() {
		
	}
	
	public Usuario(int dNI, String contraseña, TipoUsuario tipo, int estado) {
		super();

		DNI = dNI;
		Contraseña = contraseña;
		Tipo = tipo;
		this.estado = estado;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

	public TipoUsuario getTipo() {
		return Tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		Tipo = tipo;
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Usuario [DNI=" + DNI + ", Contraseña=" + Contraseña + ", Tipo=" + Tipo + "]";
	}
	
}
