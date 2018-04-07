package dominio;

public class Libro {

	private StringBuffer isbn;
	private String titulo;
	private int anio;

	public Libro(String isbn, String titulo, int anio) {

		this.isbn = new StringBuffer(isbn);
		this.titulo = titulo;
		this.anio = anio;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAnio() {
		return anio;
	}

	public String getIsbn() {
		return isbn.toString();
	}
	
	public boolean isPalindromo() {
		
		return this.isbn.toString().equals(this.isbn.reverse().toString());
	}
	
	

}
