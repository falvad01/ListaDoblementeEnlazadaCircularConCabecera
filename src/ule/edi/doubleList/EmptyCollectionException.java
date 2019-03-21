package ule.edi.doubleList;


/**
 * Al heredar de {@link Exception} se trata de una excepción de tipo
 * "checked", y como tal debe declararse mediante <code>throws</code>
 * en los métodos que pueden lanzarla.
 * 
 * @author profesor
 *
 */
public class EmptyCollectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4468275233875850540L;

	/**
	 * Es interesante proporcionar un mensaje que explique la causa de la excepción
	 * 
	 * @param hint información sobre la causa de la excepción
	 */
	public EmptyCollectionException(String nameCollection) {
		super("La colección "+nameCollection+" está vacia.");
	}
}
