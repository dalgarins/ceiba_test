package dominio;

import java.time.LocalDate;
import java.util.Date;

import dominio.regla.ReglamentoPrestamo;

public class ReglaPrestamoPorQuinceDiasSumaIsbn implements ReglamentoPrestamo {

	public static int NUMERO_SUMA_LIMITE_ISBN_ESPECIALES = 30;
	public static int CANTIDAD_DIAS_MAXIMA_DE_ENTREGA = 15;
	
	@Override
	public Prestamo reglaDePrestamo(Libro libro, String nombreUsuario) {

		LocalDate fechaSolicitud = LocalDate.now();
		Date fechaEntregaMaxima = null;
		if (libro.obtenerSumaDigitosIsbn() > NUMERO_SUMA_LIMITE_ISBN_ESPECIALES) {
			fechaEntregaMaxima = UtilidadFechas.convertirToDate(obtenerFechaEntregaMaxima(fechaSolicitud));
		}
		return new Prestamo(UtilidadFechas.convertirToDate(fechaSolicitud), libro, fechaEntregaMaxima, nombreUsuario);		
	}
	
	private LocalDate obtenerFechaEntregaMaxima(LocalDate fechaSolicitud) {
		
		return UtilidadFechas.agregarDias(fechaSolicitud, CANTIDAD_DIAS_MAXIMA_DE_ENTREGA);
	}
}
