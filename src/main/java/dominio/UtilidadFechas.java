package dominio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UtilidadFechas {
	
	private UtilidadFechas() {}
	
	public static Date convertirToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static LocalDate agregarDias(LocalDate fechaSolicitud, int cantidadMaxima) {
	    if (cantidadMaxima < 1) {
	        return fechaSolicitud;
	    }

	    LocalDate fechaIterador = fechaSolicitud;
	    int diasAgregados = 1;
	    while (diasAgregados < cantidadMaxima) {
	    	fechaIterador = fechaIterador.plusDays(1);
	        if (!(fechaIterador.getDayOfWeek() == DayOfWeek.SUNDAY)) {
	            ++diasAgregados;
	        }
	    }

	    return fechaIterador;
	}

}
