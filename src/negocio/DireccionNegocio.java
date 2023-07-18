package negocio;

import entidad.Direccion;

public interface DireccionNegocio {
	
	public Direccion ListarUno(int id);
	public boolean InsertarDP(int dni, Direccion direccion);
	public boolean InsertarDM(int dni, Direccion direccion);
	public boolean EditarDP(int dni, Direccion direccion);
	public boolean EditarDM(int dni, Direccion direccion);

}
