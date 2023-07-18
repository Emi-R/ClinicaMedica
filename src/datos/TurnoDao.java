package datos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Turno;
import entidad.Medico;

public interface TurnoDao {
	
	public boolean ActualizarTurno(Turno turno);
	public ArrayList<Turno> ListarTodos();
	public ArrayList<Turno> ListarTurnosPorMedico(Medico medico);
	public ArrayList<Turno> ListarTurnosPorMedicoDiaActual(Medico medico);
	public ArrayList<Turno> ListarTurnosPorMedicoYFecha(Medico medico, LocalDate fechaDesde, LocalDate fechaHasta);
	public ArrayList<Turno> ListarTurnosLibresPorMedico(Medico medico);
	public boolean ChequearFecha(LocalDate fecha, int dniMedico);
	public boolean insertarTurno(int dniMedico, LocalDate fecha, int i);
	public boolean existeTurnoEnHorarioFecha(Turno turno);
	public boolean ActualizarEstadoTurnoAsistio(int idTurno, String observacion);
	public boolean ActualizarEstadoTurnoAusente(int idTurno);
	public boolean EliminarTurnosLibresPorMedico(int dniMedico);
	public boolean eliminarTurnosxFecha (Medico medico, LocalDate fecha);
	public boolean EliminarTurnosLibresPorPaciente(int dniPaciente);
	public int ContarTurnosLibres();
	public int ContarTurnosOcupados();
	public int ContarTurnosPresentes();
	public int ContarTurnosAusentes();
	public int ContarTurnosAsignadosAMedico(int dni);
	public ArrayList<Turno> ListarTurnosProximosPorMedico(Medico medico);


}
