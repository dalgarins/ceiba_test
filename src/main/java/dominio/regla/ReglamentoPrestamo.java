package dominio.regla;

import dominio.Libro;
import dominio.Prestamo;

public interface ReglamentoPrestamo {

	Prestamo reglaDePrestamo(Libro libro, String nombreUsuario);
}
