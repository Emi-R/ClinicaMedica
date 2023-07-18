package entidad;

public class Horario {
	
	private int idHorario;
	private int DNIMedico;
	private String DiaAtencion;
	private int HoraInicio;
	private int HoraFin;
	private int Estado;
	
	public Horario () {
		
	}

	public Horario(int idHorario, int dNIMedico, String diaAtencion, int horaInicio, int horaFin, int estado) {
		super();
		this.idHorario = idHorario;
		DNIMedico = dNIMedico;
		DiaAtencion = diaAtencion;
		HoraInicio = horaInicio;
		HoraFin = horaFin;
		Estado = estado;
	}

	public int getIdHorario() {
		return idHorario;
	}


	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}


	public String getDiaAtencion() {
		return DiaAtencion;
	}

	public void setDiaAtencion(String diaAtencion) {
		DiaAtencion = diaAtencion;
	}

	public int getHoraInicio() {
		return HoraInicio;
	}

	public void setHoraInicio(int horaInicio) {
		HoraInicio = horaInicio;
	}

	public int getHoraFin() {
		return HoraFin;
	}

	public void setHoraFin(int horaFin) {
		HoraFin = horaFin;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	public int getDNIMedico() {
		return DNIMedico;
	}

	public void setDNIMedico(int dNIMedico) {
		DNIMedico = dNIMedico;
	}

	@Override
	public String toString() {
		return "Horario [idHorario=" + idHorario + ", DNIMedico=" + DNIMedico + ", DiaAtencion=" + DiaAtencion
				+ ", HoraInicio=" + HoraInicio + ", HoraFin=" + HoraFin + ", Estado=" + Estado + "]";
	}


}
