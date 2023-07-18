package negocio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Medico;
import entidad.Turno;
public interface TurnoNegocio {
	
	public boolean ActualizarTurno(Turno turno);
	public ArrayList<Turno> ListarTodos();
	public ArrayList<Turno> ListaTurnosPorMedico(Medico medico);
	public ArrayList<Turno> ListarTurnosPorMedicoDiaActual(Medico medico);
	public ArrayList<Turno> ListarTurnosPorMedicoYFecha(Medico medico, LocalDate fechaDesde, LocalDate fechaHasta);
	public ArrayList<Turno> ListarTurnosLibresPorMedico(Medico medico);
	public boolean chequearFecha(LocalDate fecha, int dniMedico);
	public boolean insertarTurno(int dniMedico, LocalDate fecha, int i);
	public boolean existeTurnoEnHorarioFecha(Turno turno);
	public boolean ActualizarEstadoTurnoAsistio(int idTurno, String observacion);
	public boolean ActualizarEstadoTurnoAusente(int idTurno);
	public boolean EliminarTurnosLibresPorMedico(int dniMedico);
	public boolean eliminarTurnosxDia (Medico medico, String fecha);
	public boolean EliminarTurnosLibresPorPaciente(int dniPaciente);
	public int ContarTurnosLibres();
	public int ContarTurnosOcupados();
	public int ContarTurnosPresentes();
	public int ContarTurnosAusentes();
	public int ContarTurnosAsignadosAMedico(int dni);
	public ArrayList<Turno> ListarTurnosProximosPorMedico(Medico medico);
	
	
	


}
