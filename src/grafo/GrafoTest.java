package grafo;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GrafoTest {

	private String dataTest, dataTestB, dataTestC;
	private Grafo<String> grafoA;
	
	@Before
	public void setUp() throws Exception {
		this.dataTest = "Test";
		this.dataTestB = "TestB";
		this.dataTestC = "TestC";
		
		this.grafoA = new Grafo<String>();
		
		this.grafoA.agregarVertice(dataTest);
		this.grafoA.agregarVertice(dataTestB);
		this.grafoA.agregarVertice(dataTestC);
	}
	
	@Test
	public void testAgregarVerticeNodo() {
		String dataTestD = "TestD";
		this.grafoA.agregarVertice(dataTestD);
		assertTrue(grafoA.existeVertice(dataTestD));
	}
	
	@Test
	public void testAgregarArista() {
		this.grafoA.agregarVertice(dataTest);
		this.grafoA.agregarVertice(dataTestB);
		
		this.grafoA.agregarArista(dataTest, dataTestB, 1);
		asserTrue(grafoA.existeArista(dataTest));
	}
	
	@Test
	public void testVerificarQueLosNodosNoSeanElMismo() {
		
	}
	
	@Test
	public void testVerificarVerticesDeArista() {
		
	}
	
	@Test
	public void testVerificarExisteArista() {
		
	}
	
	@Test 
	public void testEliminarArista() {
		
	}
	
	@Test
	public void testExisteArista() {
		
	}
	
	@Test
	public void testTamano() {
		
	}
	
	@Test
	public void testVecinos() {
		
	}
	
	@Test 
	public void testVerificarVertice() {
		
	}
	
	@Test
	public void testObtenerTodosLosVertices() {
		
	}
	
	@Test
	public void testObtenerVertice() {
		
	}
	
	
}
