package Exceptions;

public class UsuarioRegistrado extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "El usuario que está intentando registrar ya figura en la Base de Datos";
	}
	

}
