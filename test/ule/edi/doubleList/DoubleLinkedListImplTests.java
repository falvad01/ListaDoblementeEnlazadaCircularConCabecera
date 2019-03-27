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
		i.remove();;
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
		Assert.assertEquals("[A, B, C, D]", lS.toString()); //TODO ¿El toString siempre tiene que ir ordenador?
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
		i.remove();;
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
		i.remove();;
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
		
	}
	
}
