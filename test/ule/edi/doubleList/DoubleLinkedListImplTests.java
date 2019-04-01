package ule.edi.doubleList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

public class DoubleLinkedListImplTests {

	private DoubleLinkedListImpl<String> lS;
	private DoubleLinkedListImpl<String> lSABC;
	private DoubleLinkedListImpl<String> lSABCDE;

	@Before
	public void setup() {
		this.lS = new DoubleLinkedListImpl<String>();
		this.lSABC = new DoubleLinkedListImpl<String>("A", "B", "C");
		this.lSABCDE = new DoubleLinkedListImpl<String>("A", "B", "C", "D", "E");
	}

	@Test
	public void testToStringVacio() {
		Assert.assertEquals(lS.toString(), "[]");
	}

	@Test
	public void testToStringNoVacio() {

		Assert.assertEquals(lSABC.toString(), "[A, B, C]");
	}

	@Test
	public void testConstructorConLista() {
		DoubleLinkedListImpl<String> nueva = new DoubleLinkedListImpl<String>(lSABCDE);
		Assert.assertEquals("[A, B, C, D, E]", nueva.toString());
	}

	@Test
	public void testForwardIt() {
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.iterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertEquals("[A, B, C, D]", lS.toString());
	}

	@Test(expected = NoSuchElementException.class)
	public void testForwardItException() {
		Iterator<String> i = lS.iterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveForwardItException() {
		Iterator<String> i = lS.iterator();
		i.remove();
		
	}

	@Test
	public void testReverset() {
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.reverseIterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertEquals("[A, B, C, D]", lS.toString()); // TODO ¿El toString siempre tiene que ir ordenador?
	}

	@Test(expected = NoSuchElementException.class)
	public void testReverseItException() {
		Iterator<String> i = lS.reverseIterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemovereverseItException() {
		Iterator<String> i = lS.reverseIterator();
		i.remove();
		
	}

	@Test
	public void testOddAndEvenIt() {
		lS = new DoubleLinkedListImpl<>("A", "B", "C", "D", "E");
		System.out.println(lS.toString());
		Iterator<String> i = lS.oddAndEvenIterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("E", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertEquals("[A, B, C, D, E]", lS.toString());
	}

	@Test(expected = NoSuchElementException.class)
	public void testOddAndEvenException() {
		Iterator<String> i = lS.oddAndEvenIterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveOddAndEvenItException() {
		Iterator<String> i = lS.oddAndEvenIterator();
		i.remove();
		
	}

	@Test
	public void testAddFirst() {

		Assert.assertTrue(lS.isEmpty());
		lS.addFirst("A");
		Assert.assertEquals("[A]", lS.toString());
		Assert.assertFalse(lS.isEmpty());
		lSABC.addFirst("F");
		Assert.assertEquals(lSABC.toString(), "[F, A, B, C]");

	}

	@Test
	public void testAddLast() {

		Assert.assertTrue(lS.isEmpty());
		lS.addLast("A");
		Assert.assertEquals("[A]", lS.toString());
		Assert.assertFalse(lS.isEmpty());

		lSABC.addLast("F");
		Assert.assertEquals(lSABC.toString(), "[A, B, C, F]");

		lSABC.addLast("H");
		Assert.assertEquals(lSABC.toString(), "[A, B, C, F, H]");

	}

	@Test
	public void testaddAtPos() {

		Assert.assertTrue(lS.isEmpty());
		lS.addAtPos("A", 4);
		Assert.assertEquals("[A]", lS.toString());
		Assert.assertFalse(lS.isEmpty());
		lSABC.addAtPos("F", 2);
		lSABC.addAtPos("H", 4); // Añadimos el penultimo
		Assert.assertEquals(lSABC.toString(), "[A, F, B, H, C]");
		lSABCDE.addAtPos("F", 4);
		Assert.assertEquals(lSABCDE.toString(), "[A, B, C, F, D, E]");
		lSABCDE.addAtPos("H", 7);
		Assert.assertEquals(lSABCDE.toString(), "[A, B, C, F, D, E, H]");
		lSABCDE.addAtPos("J", 7);
		Assert.assertEquals(lSABCDE.toString(), "[A, B, C, F, D, E, J, H]");

	}

	@Test
	public void testAddNTimes() {

		lS.addNTimes("Z", 3);
		Assert.assertEquals(lS.toString(), "[Z, Z, Z]");
		lSABC.addNTimes("J", 5);
		Assert.assertEquals(lSABC.toString(), "[A, B, C, J, J, J, J, J]");
	}

	@Test
	public void testGetElem() {

		Assert.assertEquals("C", lSABC.getElem(3));
		Assert.assertEquals("E", lSABCDE.getElem(5));

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetElemListaVacia() {

		lS.getElem(1);

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetElemFueraLista() {

		lSABC.getElem(4);

	}

	@Test
	public void testSetElem() {
		lSABC.setElem("J", 2);
		Assert.assertEquals(lSABC.toString(), "[A, J, C]");
		lSABCDE.setElem("H", 5);
		Assert.assertEquals(lSABCDE.toString(), "[A, B, C, D, H]");
	}

	@Test
	public void testIndexOfElem() {

		Assert.assertEquals(3, lSABC.indexOf("C"));
		Assert.assertEquals(5, lSABCDE.indexOf("E"));
		lSABC.addNTimes("J", 5);
		Assert.assertEquals(4, lSABC.indexOf("J"));

	}

	@Test(expected = NoSuchElementException.class)
	public void testIndexOfElemNoEncontrado() {
		lSABC.indexOf("D");

	}

	@Test
	public void testIndexOfElemP() {

		Assert.assertEquals(1, lSABC.indexOf("C", 3));
		Assert.assertEquals(4, lSABCDE.indexOf("E", 2));
		lSABC.addNTimes("J", 5);
		Assert.assertEquals(1, lSABC.indexOf("J"), 4);
		lSABCDE.addLast("A");
		Assert.assertEquals(5, lSABCDE.indexOf("A", 2));

	}

	@Test(expected = NoSuchElementException.class)
	public void testIndexOfElemPNoEncontrado() {
		lSABC.indexOf("A", 2);
	}

	@Test
	public void testRemoveFirst() throws EmptyCollectionException {

		Assert.assertEquals("[A, B, C, D, E]", lSABCDE.toString());
		Assert.assertEquals("A", lSABCDE.removeFirst("A"));
		Assert.assertEquals("[B, C, D, E]", lSABCDE.toString());
		lSABCDE.addNTimes("F", 3);
		Assert.assertEquals("[B, C, D, E, F, F, F]", lSABCDE.toString());
		Assert.assertEquals("F", lSABCDE.removeFirst("F"));
		Assert.assertEquals("[B, C, D, E, F, F]", lSABCDE.toString());
		lSABC.addAtPos("G", 2);
		lSABC.addAtPos("G", 5);
		Assert.assertEquals("[A, G, B, C, G]", lSABC.toString());
		Assert.assertEquals("G", lSABC.removeFirst("G"));
		Assert.assertEquals("[A, B, C, G]", lSABC.toString());

	}

	@Test(expected = EmptyCollectionException.class)
	public void testRemoveFirstColeccionVacia() throws EmptyCollectionException {

		lS.removeFirst("A");
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirstElementoNoEncontrado() throws EmptyCollectionException {

		lSABC.removeFirst("K");
	}

	@Test
	public void testRemoveAll() throws EmptyCollectionException {

		Assert.assertEquals("[A, B, C, D, E]", lSABCDE.toString());
		Assert.assertEquals("A", lSABCDE.removeAll("A"));
		Assert.assertEquals("[B, C, D, E]", lSABCDE.toString());
		lSABCDE.addNTimes("F", 3);
		Assert.assertEquals("[B, C, D, E, F, F, F]", lSABCDE.toString());
		Assert.assertEquals("F", lSABCDE.removeAll("F"));
		Assert.assertEquals("[B, C, D, E]", lSABCDE.toString());
		lSABC.addAtPos("G", 2);
		lSABC.addAtPos("G", 5);
		Assert.assertEquals("[A, G, B, C, G]", lSABC.toString());
		Assert.assertEquals("G", lSABC.removeAll("G"));
		Assert.assertEquals("[A, B, C]", lSABC.toString());

	}

	@Test(expected = EmptyCollectionException.class)
	public void testRemoveAllColeccionVacia() throws EmptyCollectionException {

		lS.removeAll("A");
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveAllElementoNoENcontrado() throws EmptyCollectionException {

		lSABC.removeAll("H");
	}

	@Test
	public void testRemoveLast() throws EmptyCollectionException {

		Assert.assertEquals("[A, B, C, D, E]", lSABCDE.toString());
		assertEquals("E", lSABCDE.removeLast());
		Assert.assertEquals("[A, B, C, D]", lSABCDE.toString());
		assertEquals("D", lSABCDE.removeLast());
		Assert.assertEquals("[A, B, C]", lSABCDE.toString());
		assertEquals("C", lSABCDE.removeLast());
		Assert.assertEquals("[A, B]", lSABCDE.toString());

	}

	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastColecionVacia() throws EmptyCollectionException {

		lS.removeLast();

	}
	
	@Test
	public void testReverse() {
		
		lSABC.reverse();
		Assert.assertEquals("[C, B, A]", lSABC.toString());
		lSABCDE.reverse();
		Assert.assertEquals("[E, D, C, B, A]", lSABCDE.toString());
		lS.reverse();
		Assert.assertEquals("[]", lS.toString());
		
	}

}
