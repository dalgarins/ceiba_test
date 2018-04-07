package dominio.excepcion;

public class LibroNoEncontradoException extends PrestamoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8670748015255979292L;
	
	public LibroNoEncontradoException(String message) {
		super(message);
	}

}
