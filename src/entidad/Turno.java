package entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Turno {
	
	private int idTurno;
	private Medico Medico;
	private Persona Paciente;
	private Especialidad Especialidad;
	private LocalDate Fecha;
	private int Hora;
	private int Estado;
	private String Observaciones;
	
	public Turno () {
		
	}

	public Turno(int idTurno, entidad.Medico medico, Persona paciente, entidad.Especialidad especialidad,
			LocalDate fecha, int hora, int estado, String observaciones) {
		this.idTurno = idTurno;
		Medico = medico;
		Paciente = paciente;
		Especialidad = especialidad;
		Fecha = fecha;
		Hora = hora;
		Estado = estado;
		Observaciones = observaciones;
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public Medico getMedico() {
		return Medico;
	}

	public void setMedico(Medico medico) {
		Medico = medico;
	}

	public Persona getPaciente() {
		return Paciente;
	}

	public void setPaciente(Persona paciente) {
		Paciente = paciente;
	}

	public Especialidad getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		Especialidad = especialidad;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}
	
	public int getHora() {
		return Hora;
	}

	public void setHora(int hora) {
		Hora = hora;
	}

	public int isEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "Turno [idTurno=" + idTurno + ", Medico=" + Medico + ", Paciente=" + Paciente + ", Especialidad="
				+ Especialidad + ", Fecha=" + Fecha + ", Hora=" + Hora + ", Estado=" + Estado + ", Observaciones="
				+ Observaciones + "]";
	}

	
	
}
