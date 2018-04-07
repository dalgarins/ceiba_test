package dominio.unitaria;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dominio.Libro;
import dominio.Prestamo;
import dominio.ReglaPrestamoPorQuinceDiasSumaIsbn;
import dominio.regla.ReglamentoPrestamo;
import testdatabuilder.LibroTestDataBuilder;

public class ReglaPrestamoPorQuinceDiasSumaIsbnTest {

	public static final String ISBN_SUMA_30 = "A9999";
	public static final String ANY_USER = "darwin";
	
	@Test
	public void validarReglaPrestamo15Dias() {
		
		//arrange
		ReglamentoPrestamo reglamentoPrestamo = new ReglaPrestamoPorQuinceDiasSumaIsbn();
		
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		Libro libro = libroTestDataBuilder
				.conIsbn(ISBN_SUMA_30)
				.build(); 
		
		//act
		Prestamo prestamo = reglamentoPrestamo.reglaDePrestamo(libro, ANY_USER);
		
		// assert
		assertNotNull(prestamo.getFechaEntregaMaxima());
	}
	
	
	
}
