package Exceptions;

public class DniInvalido extends RuntimeException{
	public DniInvalido() {
		
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "DNI inválido";
	}

}
