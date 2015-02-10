import static org.junit.Assert.*;

import models.Episodio;
import models.Serie;
import org.junit.Test;

public class EpisodioTest {

	@Test
	public void testDeveCriarEpisodio() {
		Serie serie = new Serie("serie A");
		Episodio episosio1 = new Episodio();
		Episodio episosio2 = new Episodio("Episodio 2", 3, 7);
		Episodio episosio3 = new Episodio("Episodio 3", 2, 3, serie);
		episosio1.setNome("Episodio 1");
		episosio1.setNumero(2);
		episosio1.setTemporada(1);
		episosio1.setSerie(serie);
		episosio2.setSerie(serie);
		assertEquals("Episodio 1", episosio1.getNome());
		assertEquals(2, episosio1.getNumero());
		assertEquals(1, episosio1.getTemporada());
		assertEquals("serie A", episosio1.getSerie().getNome());
		assertEquals("Episodio 2", episosio2.getNome());
		assertEquals(3, episosio2.getNumero());
		assertEquals(7, episosio2.getTemporada());
		assertEquals("serie A", episosio2.getSerie().getNome());
		assertEquals("Episodio 3", episosio3.getNome());
		assertEquals(2, episosio3.getNumero());
		assertEquals(3, episosio3.getTemporada());
		assertEquals("serie A", episosio3.getSerie().getNome());
	}

	@Test
	public void testDeveMudarStatusDoEpisodio() {
		Episodio episosio1 = new Episodio();
		episosio1.mudaStatus();
		assertTrue(episosio1.isAssistido());
		episosio1.mudaStatus();
		assertFalse(episosio1.isAssistido());
	}

}
