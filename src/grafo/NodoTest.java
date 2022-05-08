package grafo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NodoTest {

	private String dataA, dataB, dataC;
	private Nodo<String> nodoA, nodoB, nodoC;
	
	@Before
	public void setUp() throws Exception {
		this.dataA = "0";
		this.dataB = "a";
		this.dataC = "hola";
		
		this.nodoA = new Nodo<String>(dataA);
		this.nodoB = new Nodo<String>(dataB);
		this.nodoC = new Nodo<String>(dataC);


	}

	@Test
	public void testGetInformacion() {
		assertTrue("0" == this.nodoA.getInformacion());
	}
	

	@Test
	public void testToString() {
		assertTrue("0" == this.nodoA.toString());
	}
	
	
	@Test
	public void testSetInfo() {
		this.nodoA.setInformacion("adios");
		assertTrue("adios" == this.nodoA.toString());
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testNoAgregarVecinoEsSiMismo() {
		this.nodoA.agregarVecino(nodoA, 1.0f);
	}
	
	
	@Test 
	public void testNoSonVecinosAun() {
		assertFalse(this.nodoA.esVecino(nodoB));
	}

}
