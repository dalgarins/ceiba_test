package dominio.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import dominio.Bibliotecario;
import dominio.Libro;
import dominio.excepcion.LibroNoEncontradoException;
import dominio.excepcion.PrestamoException;
import dominio.excepcion.ReglaPrestamoException;
import dominio.regla.ReglamentoPrestamo;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import testdatabuilder.LibroTestDataBuilder;

public class BibliotecarioTest {
	
	public static final String ISBN_NO_EXISTE = "789456";
	public static final String ISBN_PALINDROMO = "A99A";
	public static final String ANY_USER = "darwin";

	@Test
	public void esPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		ReglamentoPrestamo reglamentoPrestamo = mock(ReglamentoPrestamo.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(libro);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo, reglamentoPrestamo);
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertTrue(esPrestado);
	}
	
	@Test
	public void libroNoPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		ReglamentoPrestamo reglamentoPrestamo = mock(ReglamentoPrestamo.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(null);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo, reglamentoPrestamo);
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertFalse(esPrestado);
	}
	
	@Test(expected = LibroNoEncontradoException.class)
	public void libroPrestadoNoExiste() {
		
		// arrange
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		ReglamentoPrestamo reglamentoPrestamo = mock(ReglamentoPrestamo.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(ISBN_NO_EXISTE)).thenReturn(null);
		when(repositorioLibro.obtenerPorIsbn(ISBN_NO_EXISTE)).thenReturn(null);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo, reglamentoPrestamo);
		
		String anyUser = "darwin";
		
		//act
		bibliotecario.prestar(ISBN_NO_EXISTE, anyUser);
	}
	
	@Test(expected = ReglaPrestamoException.class)
	public void libroPrestadoSoloSePuedeUsarEnLaBiblioteca() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
				
		Libro libro = libroTestDataBuilder.conIsbn(ISBN_PALINDROMO).build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		ReglamentoPrestamo reglamentoPrestamo = mock(ReglamentoPrestamo.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(ISBN_PALINDROMO)).thenReturn(null);
		when(repositorioLibro.obtenerPorIsbn(ISBN_PALINDROMO)).thenReturn(libro);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo, reglamentoPrestamo);
		
		
		
		//act
		bibliotecario.prestar(ISBN_PALINDROMO, ANY_USER);
	}
	
	@Test
	public void verificarLibroEstaDisponible() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		ReglamentoPrestamo reglamentoPrestamo = mock(ReglamentoPrestamo.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(ISBN_NO_EXISTE)).thenReturn(null);
		when(repositorioLibro.obtenerPorIsbn(libro.getIsbn())).thenReturn(libro);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo, reglamentoPrestamo);
		
		//act
		Libro libroSePuedePrestar = bibliotecario.verificarLibro(libro.getIsbn());
		
		// assert
		assertNotNull(libroSePuedePrestar);
	}
	
	@Test(expected = PrestamoException.class)
	public void verificarLibroNoEstaDisponible() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		ReglamentoPrestamo reglamentoPrestamo = mock(ReglamentoPrestamo.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(libro);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo, reglamentoPrestamo);
		
		//act
		bibliotecario.verificarLibro(libro.getIsbn());
	}
	
}
