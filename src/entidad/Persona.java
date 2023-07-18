package entidad;

import java.time.LocalDate;

import Exceptions.DniInvalido;
import Exceptions.UsuarioRegistrado;

public class Persona {
	
	private int DNI;	
	private String Nombre;	
	private String Apellido;	
	private char Sexo;	
	private String Nacionalidad;	
	private LocalDate Fnac;	
	private Direccion Direccion;	
	private String Mail;	
	private String Telefono;
	private int Estado;
	
	public Persona () {
		this.Direccion = new Direccion();
		Nombre = "";
		Apellido = "";
		Nacionalidad = "";
		Mail = "";
		Telefono = "";
	}

	public Persona(int dNI, String nombre, String apellido, char sexo, String nacionalidad, LocalDate fnac,
			entidad.Direccion direccion, String mail, String telefono, int estado) {
		DNI = dNI;
		Nombre = nombre;
		Apellido = apellido;
		Sexo = sexo;
		Nacionalidad = nacionalidad;
		Fnac = fnac;
		Direccion = direccion;
		Mail = mail;
		Telefono = telefono;
		Estado = estado;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public char getSexo() {
		return Sexo;
	}

	public void setSexo(char sexo) {
		Sexo = sexo;
	}

	public String getNacionalidad() {
		return Nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}

	public LocalDate getFnac() {
		return Fnac;
	}

	public void setFnac(LocalDate fnac) {
		Fnac = fnac;
	}

	public Direccion getDireccion() {
		return Direccion;
	}

	public void setDireccion(Direccion direccion) {
		Direccion = direccion;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Persona [DNI=" + DNI + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Sexo=" + Sexo
				+ ", Nacionalidad=" + Nacionalidad + ", Fnac=" + Fnac + ", Direccion=" + Direccion + ", Mail=" + Mail
				+ ", Telefono=" + Telefono + ", Estado=" + Estado + "]";
	}
		
}

