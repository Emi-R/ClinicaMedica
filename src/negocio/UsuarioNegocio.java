package negocio;

import Exceptions.DniInvalido;
import Exceptions.UsuarioRegistrado;
import entidad.Usuario;

public interface UsuarioNegocio {
	public Usuario obtenerUsuario(String pass, int dni);
	public boolean insertarUsuario(String pass, int dni, int tipoUsuario);
	public boolean editarUsuario(String pass, int dni);
	public boolean validarDNI(int dni) throws DniInvalido;
	public boolean eliminarUsuario(int dni);
}
