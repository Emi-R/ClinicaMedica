package auxiliares;

import entidad.Usuario;

public class ValidarUsuario {
	
	public static boolean validarUsuarioAdmin(Usuario user) {
		
        if (user != null) 
        {
            if (user.getTipo().getIdTipoUsuario() == 1) 
            {
                // Administrador
                return true;
            } else 
            {
                // Medico
                return false;
            }
        } else {
            // No está logueado
            return false;
        }
    }
	
	public static boolean validarUsuarioMedico(Usuario user) {
        if (user != null) {
            if (user.getTipo().getIdTipoUsuario() == 0) {
                // Medico
                return true;
            } else {
                // Administrador
                return false;
            }
        } else {
            // No está logueado
            return false;
        }
    }
	
}
