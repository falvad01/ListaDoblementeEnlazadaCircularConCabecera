package ule.edi.doubleList;

import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListImpl<T> implements DoubleLinkedList<T> {
	/**
	 * Nodo doblemente enlazado.
	 * 
	 * Como es estática, no tiene en ámbito el parámetro 'T' de la clase que la
	 * contiene. El parámetro 'D' será sustituído por un tipo particular cuando se
	 * use el nodo, por ejemplo:
	 * 
	 * DoubleNode<T> cab;
	 * 
	 * en la lista.
	 *
	 * @param <D>
	 */
	static class DoubleNode<D> {

		DoubleNode(D element) {
			this.content = element;
			this.previous = null;
			this.next = null;
		}

		// dato a almacenar en el nodo
		D content;

		DoubleNode<D> previous;

		DoubleNode<D> next;
	}

	/**
	 * Apunta al nodo cabecera; siempre habrá un nodo vacío (sin elemento) que actua
	 * de cabecera OJO!!! ESTE NODO CABECERA DEBERÁ CREARSE EN CADA CONSTRUCTOR DE
	 * LA LISTA
	 */
	private DoubleNode<T> cab;

	//////////////////////
	///// CONSTRUCTORES
	//////////////////////

	/**
	 * Construye una lista vacía.
	 */
	public DoubleLinkedListImpl() {

		cab = new DoubleNode<T>(null); // nodo cabecera vacio
		cab.next = cab;
		cab.previous = cab;

		// Deberá crear el nodo cabecera vacío

	}

	/**
	 * Construye una lista con los elementos dados.
	 * 
	 * Java creará un array 'elements' con los dados en la llamada al constructor;
	 * por ejemplo:
	 * 
	 * x = new DoubleLinkedList<String>("A", "B", "C");
	 * 
	 * ejecuta este método con un array [A, B, C] en 'elements'.
	 * 
	 * @param elements
	 */
	public DoubleLinkedListImpl(T... elements) {
		cab = new DoubleNode<T>(null); // nodo cabecera vacio
		cab.next = cab;
		cab.previous = cab;
		DoubleNode<T> aux = cab;

		for (int i = 0; i < elements.length; i++) {
			DoubleNode<T> element = new DoubleNode<T>(elements[i]);
			element.previous = aux; // añadimos la referencia al anterior
			aux.next = element; // añadimos la referencia al siguiente
			aux = aux.next; // movemos el nodo

			if (i == elements.length - 1) {
				element.next = cab;
				cab.previous = element;// referencia de la cabeza al anterior

			}

//			System.out.println("Anterior al elemento " + i + ": " +  element.previous.content);
//			System.out.println("Elemento " + i + ": " +  element.content);

		}

	}

	/**
	 * Construye una lista a partir de otra.
	 * 
	 * Las listas tienen nodos independientes, con los mismos contenidos.
	 */
	public DoubleLinkedListImpl(DoubleLinkedList<T> other) {
		cab = new DoubleNode<T>(null); // nodo cabecera vacio
		Iterator<T> it = other.iterator();
		cab.next = cab;
		cab.previous = cab;

		DoubleNode<T> aux = cab;

		for (int i = 0; i < other.size(); i++) {

			DoubleNode<T> element = new DoubleNode<T>(it.next());
			// System.out.println("Element " + i + ": " + element.content);

			element.previous = aux; // añadimos la referencia al anterior
			aux.next = element; // añadimos la referencia al siguiente
			aux = aux.next; // movemos el nodo

			if (i == other.size() - 1) {
				element.next = cab;
				cab.previous = element;// referencia de la cabeza al anterior

			}

		}

		// System.out.println(size());
	}

	//////////////////////
	///// ITERADORES
	//////////////////////

	private class ForwardIterator implements Iterator<T> {

		private DoubleNode<T> at = cab.next;

		@Override
		public boolean hasNext() {

			if (at.content == null) {
				return false;
			} else {
				return true;
			}

		}

		@Override
		public T next() {
			int i = 0;
			// System.out.println(i++);
			// System.out.println("cab: " + at.content);
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {

				T ret = at.content;
				at = at.next;
				return ret;
			}

		}

		@Override
		public void remove() {

			throw new UnsupportedOperationException();
		}
	};

	private class reverseIterator implements Iterator<T> {

		private DoubleNode<T> at = cab.previous;

		@Override
		public boolean hasNext() {
			if (at.content == null) {

				return false;
			} else {
				return true;
			}

		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				T ret = at.content;
				at = at.previous;
				return ret;
			}
		}

		@Override
		public void remove() {

			throw new UnsupportedOperationException();
		}
	};

	private class OddAndEvenIterator implements Iterator<T> {

		// Definir los atributos necesarios para implementar el iterador

		private DoubleNode<T> at;
		int count;

		public OddAndEvenIterator() {
			at = cab.next.next;
			count = 0;
		}

		@Override
		public boolean hasNext() {
			if (at.content == null) {
				return false;
			} else {
				return true;
			}

		}

		@Override
		public T next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				T ret = at.content;
				// System.out.println("Antes: " + at.content);
				at = at.next.next;
				// System.out.println("Despues: " + at.content);
				if (at.content == null) {

					at = at.next;

				}
				count++;
				System.out.println(count);
				if (count == size()) { // Cuando llegamos al fianl

					at = at.previous; // Con el algoritmo implementados nos pasamos 1, por lo igualo a la posicion
										// anterior

				}

				// System.out.println(ret);
				return ret;
			}

		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	};

	////// FIN DE ITERADORES ///////
	////////////////////////////////

	@Override
	public boolean isEmpty() {

		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		DoubleNode<T> aux = cab.next;
		// System.out.println("AUX: " + aux.content);
		int ret = 0;
		while (aux.content != null) {
			// System.out.println("while: " + aux.content);
			aux = aux.next;
			ret++;
		}
		return ret;
	}

	@Override
	public void addFirst(T element) {
		DoubleNode<T> nodeElement = new DoubleNode<T>(element);

		if (isEmpty()) {
			cab.next = nodeElement;
			cab.previous = nodeElement;
			nodeElement.previous = cab;
			nodeElement.next = cab;

		} else {

			DoubleNode<T> aux = cab.next; // Guadamos en un auxiliar el que era antes el primero
			cab.next = nodeElement;
			nodeElement.previous = cab;
			nodeElement.next = aux;
			aux.previous = nodeElement;
		}

	}

	@Override
	public void addLast(T element) {
		DoubleNode<T> nodeElement = new DoubleNode<T>(element);
		// System.out.println(element);
		if (isEmpty()) {
			// System.out.println("Entra una vez");
			cab.next = nodeElement;
			cab.previous = nodeElement;
			nodeElement.previous = cab;
			nodeElement.next = cab;

		} else {

			DoubleNode<T> aux = cab.previous; // Vamos al ultimo de la lista
			// System.out.println("El resto de veces");
			// System.out.println("Contest " + aux.content);
			aux.next = nodeElement;
			nodeElement.previous = aux;
			nodeElement.next = cab;
			cab.previous = nodeElement; // Cerramos el ciclo apuntando el previus de la cabeza al nuevo ultimo elemento

		}

	}

	@Override
	public void addAtPos(T element, int p) {

		DoubleNode<T> nodeElement = new DoubleNode<T>(element);

		if (p > this.size()) {// Cuando la posicion en la que lo insertamos es mayor que el tamaño de la lista

			this.addLast(element);
		} else {
			DoubleNode<T> aux = cab.next; // Guadamos en un auxiliar el que era antes el primero

			DoubleNode<T> previus = cab;
			DoubleNode<T> next = cab;

			int i = 0;
			while (aux.content != null) {
				if (i < p - 1) { // Guardamos el nodo anterior a donde guardamos el nuevo elemento
					previus = aux;
					next = aux.next;

				}
				aux = aux.next;

				i++;
			}

			previus.next = nodeElement;
			nodeElement.previous = previus;
			nodeElement.next = next;
			next.previous = nodeElement;

		}

	}

	@Override
	public void addNTimes(T element, int n) {
		int i = 0;
		while (i < n) {
			this.addLast(element);
			// System.out.println(i);
			i++;

		}

		// System.out.println(toString());

	}

	@Override
	public T getElem(int p) {

		if (isEmpty()) {
			throw new IndexOutOfBoundsException();

		} else if (p > size()) {

			throw new IndexOutOfBoundsException();

		} else {

			int i = 1;

			Iterator<T> it = this.iterator();
			T ret = null;
			while (i <= p) {

				ret = it.next();
				i++;
			}
			return ret;

		}

	}

	@Override
	public void setElem(T elem, int p) {

		DoubleNode<T> aux = cab.next;
		DoubleNode<T> toChange = cab.next;

		for (int i = 1; aux.content != null; i++) {

			if (i == p) {
				toChange = aux;
			}
			aux = aux.next;
		}

		toChange.content = elem;

		while (aux.content != null) {

		}

	}

	@Override
	public int indexOf(T elem) {

		Iterator<T> it = this.iterator();
		boolean flag = true;
		int i = 0;
		int ret = -1;
		T element;

		while (it.hasNext() != false) {
			element = it.next();

			if (flag && element.equals(elem)) {

				ret = i + 1;
				flag = false;
			}
			i++;
		}

		if (ret == -1) {
			throw new NoSuchElementException();
		} else {

			return ret;
		}

	}

	@Override
	public int indexOf(T elem, int p) {// TODO preguntar diferencia entre las excepciones

		Iterator<T> it = this.iterator();
		boolean flag = true;
		T element;
		int ret = -1;
		int j = 1;

		for (int i = 1; it.hasNext() != false; i++) {
			element = it.next();
			if (i > p - 1 && flag) {
				if (element.equals(elem)) {
					ret = j;
					flag = false;
				}
				j++;
			}
		}
		
		if (ret == -1) {
			throw new NoSuchElementException();
		} else {
			return ret;
		}
	}

	@Override
	public T removeFirst(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeAll(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reverse() {
		// TODO Auto-generated method stub

	}

	@Override
	public int isSubList(DoubleLinkedList<T> part) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void interlace(DoubleLinkedList<T> other) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {

		if (cab != cab.next) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			DoubleNode<T> i = cab.next;
			while (i != cab) {
				rx.append(i.content);
				rx.append(", ");
				i = i.next;
				// System.out.println(rx.toString());
			}
			rx.delete(rx.length() - 2, rx.length());
			rx.append("]");

			return rx.toString();
		} else {
			return "[]";
		}
	}

	///////////////////////////////////////////
	// métodos que devuelve iteradores
	///////////////////////////////////////
	@Override
	public Iterator<T> oddAndEvenIterator() {
		return new OddAndEvenIterator();

	}

	@Override
	public Iterator<T> iterator() {
		return new ForwardIterator();

	}

	@Override
	public Iterator<T> reverseIterator() {
		return new reverseIterator();
	}

}
