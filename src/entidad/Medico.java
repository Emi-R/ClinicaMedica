package entidad;

public class Medico extends Persona {
	
	private Especialidad Especialidad;
	private Horario Horario;
	
	public Medico() {
		
	}

	public Medico(entidad.Especialidad especialidad, Horario horario) {
		super();
		Especialidad = especialidad;
		Horario = horario;
	}

	public Especialidad getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		Especialidad = especialidad;
	}

	public Horario getHorario() {
		return Horario;
	}
	
	public void setHorario(Horario horario) {
		Horario = horario;
	}
	
	@Override
	public String toString() {
		return "Medico [Especialidad=" + Especialidad + "]";
	}
			
}
