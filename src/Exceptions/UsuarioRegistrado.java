package Exceptions;

public class UsuarioRegistrado extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "El usuario que est� intentando registrar ya figura en la Base de Datos";
	}
	

}
