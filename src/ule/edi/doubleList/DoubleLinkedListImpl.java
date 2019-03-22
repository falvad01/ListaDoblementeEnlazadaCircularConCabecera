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
		
		
		for(int i = 0; i < elements.length; i++) {
			DoubleNode<T> element = new DoubleNode<T>(elements[i]);
			element.previous = aux; //añadimos la referencia al anterior
			aux.next = element; // añadimos la referencia al siguiente
			aux = aux.next; //movemos el nodo
			
			if(i == elements.length- 1) {
				element.next = cab;
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
		cab.next = cab;
		cab.previous = cab;
		System.out.println("lista other: " + other.toString());
		System.out.println("Longitud: "+  other.toString().length() );
		System.out.println(other.iterator().next());
		
	
		
		// TODO
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
			System.out.println(i++);
			System.out.println("cab: " + at.content);
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				System.out.println("Content: " + at.content);
				T ret = at.next.content;
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

		private DoubleNode<T> at = cab.next;

		@Override
		public boolean hasNext() {
			if (at.previous == null) {
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
				at = at.previous;
				return at.previous.content;
			}
		}

		@Override
		public void remove() {

			throw new UnsupportedOperationException();
		}
	};

	private class OddAndEvenIterator implements Iterator<T> {

		// Definir los atributos necesarios para implementar el iterador

		public OddAndEvenIterator() {

		}

		@Override
		public boolean hasNext() {
			return false;
			// TODO Auto-generated method stub

		}

		@Override
		public T next() {
			return null;
			// TODO Auto-generated method stub

		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	};

	////// FIN DE ITERADORES ///////
	////////////////////////////////

	@Override
	public boolean isEmpty() {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		DoubleNode<T> aux = cab.next;
		int ret = 0;
		while(aux != null) {
			
			ret++;
		}
		return ret;
	}

	@Override
	public void addFirst(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addLast(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAtPos(T element, int p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNTimes(T element, int n) {
		// TODO Auto-generated method stub

	}

	@Override
	public T getElem(int p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElem(T elem, int p) {
		// TODO Auto-generated method stub

	}

	@Override
	public int indexOf(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int indexOf(T elem, int p) {
		// TODO Auto-generated method stub
		return 0;
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
				//System.out.println(rx.toString());
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
		return null;
		// TODO Auto-generated method stub

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
