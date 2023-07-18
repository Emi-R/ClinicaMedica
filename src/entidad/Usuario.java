package entidad;

public class Usuario {
	
	private int DNI;
	private String Contrase�a;
	private TipoUsuario Tipo;
	private int estado;
	
	public Usuario() {
		
	}
	
	public Usuario(int dNI, String contrase�a, TipoUsuario tipo, int estado) {
		super();

		DNI = dNI;
		Contrase�a = contrase�a;
		Tipo = tipo;
		this.estado = estado;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getContrase�a() {
		return Contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		Contrase�a = contrase�a;
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
		return "Usuario [DNI=" + DNI + ", Contrase�a=" + Contrase�a + ", Tipo=" + Tipo + "]";
	}
	
}
