package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dominio.Libro;
import testdatabuilder.LibroTestDataBuilder;

public class LibroTest {

	private static final int ANIO = 2010;
	private static final String ISBN = "1234";
	private static final String NOMBRE_LIBRO = "Cien años de soledad";
	
	private static final String ISBN_PALINDROMO = "1221";
	
	private static final String ISBN_SUMA_CERO = "AAABCT";
	

	@Test
	public void crearLibroTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().
				conTitulo(NOMBRE_LIBRO).
				conIsbn(ISBN).
				conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();

		// assert
		assertEquals(NOMBRE_LIBRO, libro.getTitulo());
		assertEquals(ISBN, libro.getIsbn());
		assertEquals(ANIO, libro.getAnio());
	}
	
	@Test
	public void comprobarIsbnLibroEsPalindromo() {
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().
				conTitulo(NOMBRE_LIBRO).
				conIsbn(ISBN_PALINDROMO).
				conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();
		
		// assert
		assertTrue(libro.esPalindromo());
	}
	
	@Test
	public void comprobarIsbnLibroNoEsPalindromo() {
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().
				conTitulo(NOMBRE_LIBRO).
				conIsbn(ISBN).
				conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();
		
		// assert
		assertFalse(libro.esPalindromo());
	}
	
	@Test
	public void comprobarSumaDigitosIsbnEsCero() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().
				conTitulo(NOMBRE_LIBRO).
				conIsbn(ISBN_SUMA_CERO).
				conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();
		
		// assert
		assertEquals(0, libro.obtenerSumaDigitosIsbn());
	}

}
