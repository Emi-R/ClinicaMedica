package negocioImpl;

import entidad.Medico;
import entidad.Persona;
import entidad.Turno;
import negocio.TurnoNegocio;
import datosImpl.TurnoDaoImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import datos.TurnoDao;

public class TurnoNegocioImpl implements TurnoNegocio{

	public TurnoDao tdao = new TurnoDaoImpl();

	public boolean ActualizarTurno(Turno turno) {
		return tdao.ActualizarTurno(turno);
	}
	
	public ArrayList<Turno> ListarTodos(){
		return tdao.ListarTodos();
	}
	
	public ArrayList<Turno> ListaTurnosPorMedico(Medico medico) {
		return tdao.ListarTurnosPorMedico(medico);
	}
	
	public ArrayList<Turno> ListarTurnosPorMedicoDiaActual(Medico medico)
	{
		return tdao.ListarTurnosPorMedicoDiaActual(medico);
	}
	
	public ArrayList<Turno> ListarTurnosPorMedicoYFecha(Medico medico, LocalDate fechaDesde, LocalDate fechaHasta)
	{
		return tdao.ListarTurnosPorMedicoYFecha(medico, fechaDesde, fechaHasta);
	}
	
	public boolean chequearFecha(LocalDate fecha, int dniMedico)
	{
		return tdao.ChequearFecha(fecha, dniMedico);
	}
	
	public boolean insertarTurno(int dniMedico, LocalDate fecha, int i)
	{
		return tdao.insertarTurno(dniMedico, fecha, i);
	}
	
	public boolean existeTurnoEnHorarioFecha(Turno turno) {
		return tdao.existeTurnoEnHorarioFecha(turno);
	}
	
	public boolean ActualizarEstadoTurnoAsistio(int idTurno, String observacion)
	{
		return tdao.ActualizarEstadoTurnoAsistio(idTurno, observacion);
	}
	
	public boolean ActualizarEstadoTurnoAusente(int idTurno)
	{
		return tdao.ActualizarEstadoTurnoAusente(idTurno);
	}
	
	public boolean EliminarTurnosLibresPorMedico(int dniMedico)
	{
		return tdao.EliminarTurnosLibresPorMedico(dniMedico);
	}

	public boolean EliminarTurnosLibresPorPaciente(int dniPaciente)
	{
		return tdao.EliminarTurnosLibresPorPaciente(dniPaciente);
	}
	
	@Override
	public boolean eliminarTurnosxDia(Medico medico, String dia) {
		
		ArrayList<Turno> listaT = tdao.ListarTurnosLibresPorMedico(medico);
		
		for (Turno turno : listaT) {
			
			LocalDate fecha = turno.getFecha();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("es"));
			String dayOfWeek = fecha.format(formatter);
			 
			 if (dayOfWeek.equalsIgnoreCase(dia)) 
			 {
				 tdao.eliminarTurnosxFecha(medico, fecha);
			 }
		}
		
		return true;
	}

	@Override
	public ArrayList<Turno> ListarTurnosLibresPorMedico(Medico medico) {
		return tdao.ListarTurnosLibresPorMedico(medico);
	}

	public int ContarTurnosLibres()
	{
		return tdao.ContarTurnosLibres();
	}
	public int ContarTurnosOcupados()
	{
		return tdao.ContarTurnosOcupados();
	}
	public int ContarTurnosPresentes()
	{
		return tdao.ContarTurnosPresentes();
	}
	public int ContarTurnosAusentes()
	{
		return tdao.ContarTurnosAusentes();
	}
	
	public int ContarTurnosAsignadosAMedico(int dni)
	{
		return tdao.ContarTurnosAsignadosAMedico(dni);
	}
	
	public ArrayList<Turno> ListarTurnosProximosPorMedico(Medico medico)
	{
		return tdao.ListarTurnosProximosPorMedico(medico);
	}
}
