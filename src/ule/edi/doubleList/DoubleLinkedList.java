package ule.edi.doubleList;

import java.util.Iterator;

public interface DoubleLinkedList<T> extends Iterable<T> {

	/**
	 * indica si la lista está vacía.
	 *  @return true si la lista es vacía, false en caso contrario
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve el número de elementos de la lista.
	 *  @return numero de elementos de la lista
	 */
	public int size();
	
	/**
	 * Inserta un elemento como primero.
	 * @param element a insertar
	 */
	public void addFirst(T element);
	
	/**
	 * Inserta un elemento como último.
	 * @param element a insertar
	 */
	public void addLast(T element);

	
	/**
	 * Inserta el elemento en la posición p, desplazando los elementos a partir de esa posición. 
	 * Si la lista tiene menos de n elementos lo insertará como último elemento.
	 * 
	 * Si la lista era [A, B, C] :
	 *       lista.addAtPos(“Z”, 1) dejará la lista como [Z, A, B, C].
	 *       lista.addAtPos(“Z”, 3) dejará la lista como [A, B, Z, C].
	 *       lista.addAtPos(“Z”, 5) dejará la lista como [A, B, C, Z].
	 *       
	 * @param element a insertar
	 * @param pos posicion en la que se insertará el elemento, desplazando los siguientes
	 */
	public void addAtPos(T element, int p);
	
	/**
	 * inserta n veces el elemento al final de la lista.
	 * Si lista=[A, B, C], lista.addNTimes(“Z”, 4) dejará la lista como: [A, B, C, Z, Z, Z, Z]
	 *       
	 * @param element a insertar
	 * @param p posicion en la que se insertará el elemento, desplazando los siguientes
	 */
	public void addNTimes(T element, int n);
	
	/**
	 * Devuelve el elemento que ocupa la posición p en la lista (las posiciones empiezan en 1).
	 *  Dispara IndexOutOfBoundsException si no existen p elementos (la lista tiene menos de p elementos). 
	 *       
	 * @param p posicion que ocupa el elemento a devolver 
	 * @return el elemento que ocupa la posición p en la lista
	 * @throws IndexOutOfBoundsException si no existen p elementos (la lista tiene menos de p elementos). 
	 */
	public T getElem(int p);
	
	/**
	 * Sustituye el elemento que ocupa la posición p en la lista (las posiciones empiezan en 1).
	 *  Dispara IndexOutOfBoundsException si no existen p elementos (la lista tiene menos de p elementos). 
	 *       
	 * @param p posicion que ocupa el elemento a devolver 
	 * @throws IndexOutOfBoundsException si no existen p elementos (la lista tiene menos de p elementos). 
	 */
	public void setElem(T elem, int p);
	
	/**
	 * Indica la posición donde se encuentra la primera aparición de elem desde el principio de la lista (las posiciones empiezan en 1). 
	 * Dispara la excepción NoSuchElementException si no se encuentra el elemento en la lista.
	 *       
	 * @param elem  el elemento a buscar 
	 * @return la posición que ocupa el elemento en la lista
	 * @throws NoSuchElementException si no se encuentra el elemento en la lista.
	 */
	public int indexOf(T elem);
	
	/**
	 * Indica la posición donde se encuentra la primera aparición de elem desde desde la posición p incluida (las posiciones empiezan en 1).  
	 * Dispara la excepción IndexOutOfBoundsException si no hay p elementos en la lista.
	 * Dispara la excepción NoSuchElementException si no se encuentra el elemento en la lista a partir de la posición indicada incluida habiendo al menos p elementos en la lista.
	 * 
	 * @param elem  el elemento a buscar 
	 * @param p posicion a partir de la que se busca el elemento 
	 * @return la posición que ocupa el elemento en la lista a partir de la posición p
	 * @throws IndexOutOfBoundsException si no existen p elementos (la lista tiene menos de p elementos). 
	 * @throws NoSuchElementException si no se encuentra el elemento en la lista a partir de la posición p (habiendo al menos p elementos en la lista)
	 */
	public int indexOf(T elem, int p);
	
	/**
	 * Elimina el último elemento de la lista.
	 * 
	 * @return el elemento que es eliminado
	 * @throws EmptyCollectionException si la lista está vacía
	 */
	public T removeLast() throws EmptyCollectionException;

	/**
	 * Elimina la primera aparición del elemento.
	 * Si la lista es vacía dispara la excepción EmptyCollectionException.
	 * 
	 * Si lista=[A, C, B, C, D, C] 
	 * 		lista.removeFirst(“C”) dejará a lista=[A, B, C, D, C]
	 * 
	 * @param elem  el elemento a eliminar 
	 * @return el elemento que es eliminado
	 * @throws EmptyCollectionException si la lista está vacía
	 * @throws NoSuchElementException si no se encuentra el elemento en la lista
	 */
	public T removeFirst(T elem) throws EmptyCollectionException;

	/**
	 * Elimina todas las apariciones del elemento.
	 * Si la lista es vacía dispara la excepción EmptyCollectionException.
	 * 
	 * Si lista=[A, C, B, C, D, C] 
	 * 		lista.removeAll(“C”) dejará a lista=[A, B, D]
	 * 
	 * @param elem  el elemento a eliminar 
	 * @return la última aparición del elemento que es eliminado
	 * @throws EmptyCollectionException si la lista está vacía
	 * @throws NoSuchElementException si no se encuentra el elemento en la lista
	 */
	public T removeAll(T elem) throws EmptyCollectionException;

	/**
	 * Invierte la lista actual. 
	 * Por ejemplo, si la lista era [A, B, C], después de la llamada al método será [C,B,A]
	 * 
	 */
	public void reverse();
	
	/**
	 * Indica a partir de qué posición se encuentra la sublista pasada como parámetro en la lista actual o -1 si no se encuentra.
	 * Si part es vacía devuelve 1
	 * 
	 * Ejemplos:
	 * 
	 *  [A, B, A, B, C], con part=[B, A, X], devolvería -1 
	 *  [A, B, A, B, C], con part=[B, A], devolvería 2 
	 *
	 *  [A, B, A, B], con part=[A, B], devolvería 1 
	 *  
	 *  [A, B, A, B, C, X, A], con part=[B, C, X], devolvería 4
	 *    
	 * @param part lista a comprobar si es sublista de la actual
	 * @return posición a partir de la que se encuentra la sublista en la lista actual
	 */
	public int isSubList(DoubleLinkedList<T> part);
		
	/**
	 * inserta los elementos de la lista pasada como parámetro en la lista actual, entrelazando sus elementos. 
	 * Es decir, el primer elemento de otherLista, lo dejará detrás del primer elemento de la lista actual, y así sucesivamente. 
	 * La lista other no se modifica, por lo que deben crearse nuevos nodos en la lista actual con el mismo contenido que los nodos de other.
	 *  Es decir, no se utilizarán los mismos nodos de other para insertarlos en la lista actual.
	 *  
	 *  Por ejemplo,
	 *  	Si lista=[A, B, C, D, E] y other=[X,Y,Z]:    lista.interlace(other) dejará a lista=[A, X, B, Y, C, Z, D, E]
	 *      Si lista=[A, B, C] y other=[V, W, X,Y,Z]:    lista.interlace(other) dejará a lista=[A, V, B, W, C, X, Y, Z]
	 *      Si lista=[A, B, C] y other=[]:               lista.interlace(other) dejará a lista=[A, B, C]
	 *      Si lista=[] y other=[A, B]:                  lista.interlace(other) dejará a lista=[A, B]
 
	 *    
	 * @param other lista a intercalar con la actual
	 */
	public void interlace(DoubleLinkedList<T> other);
	
	/// ITERADORES

	/**
	 * Devuelve un iterador que recorre la lista en orden inverso.
	 * 
	 * Por ejemplo, para una lista x con elementos [A, B, C, D, E]
	 * 
	 * el iterador creado con x.reverseIterator() devuelve en sucesivas llamadas a next(): E, D, C, B y A.
	 * 
	 * @return iterador para orden inverso.
	 */
	public Iterator<T> reverseIterator();
	
	/**
	 * Devuelve un iterador que recorre la lista recorriendo primero los elementos que ocupan posiciones pares y luego los que ocupen posiciones impares.
	 * 
	 * Por ejemplo, para una lista x con elementos [A, B, C, D, E]
	 * 
	 * el iterador creado con x.oddAndEvenIterator() devuelve en sucesivas llamadas a next(): B, D, A, C y E.
	 * 
	 * @return iterador para orden pares e impares.
	 */
	public Iterator<T> oddAndEvenIterator();
}
