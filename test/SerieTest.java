import static org.junit.Assert.*;
import models.Serie;

import org.junit.Test;

public class SerieTest {

	@Test
	public void testdeveIniciarSerieSemEpisodios() {
		Serie serie = new Serie("serie A");
		assertFalse(serie.isAssistindo());
		assertEquals("serie A", serie.getNome());
		assertEquals(0, serie.getTotalDeEpisodios());
	}
	
	@Test
	public void testDeveAdicionarEpisodioEmSerie() {
		Serie serie = new Serie("serie A");
		serie.addEpisodio("Episodio 3", 2, 3);
		assertEquals(1, serie.getTotalDeEpisodios());
	}
	
	@Test
	public void testDeveRemoverEpisodioEmSerie() {
		Serie serie = new Serie("serie A");
		serie.addEpisodio("Episodio 3", 2, 3);
		assertEquals(1, serie.getTotalDeEpisodios());
		serie.removeEpisodio(serie.getEpisodios().get(0));
		assertEquals(0, serie.getTotalDeEpisodios());
	}
	
	@Test
	public void testDeveMudarStatusDaSerie() {
		Serie s1 = new Serie();
		s1.mudaStatus();
		assertTrue(s1.isAssistindo());
		s1.mudaStatus();
		assertFalse(s1.isAssistindo());
	}
	
	@Test
	public void testContadores() {
		Serie serie = new Serie("serie A");
		serie.addEpisodio("Episodio 1", 1, 1);
		serie.addEpisodio("Episodio 2", 2, 1);
		serie.addEpisodio("Episodio 3", 3, 1);
		serie.addEpisodio("Episodio 1", 1, 2);
		serie.addEpisodio("Episodio 2", 2, 2);
		serie.addEpisodio("Episodio 3", 3, 2);
		serie.addEpisodio("Episodio 4", 4, 2);
		serie.addEpisodio("Episodio 5", 5, 2);
		serie.addEpisodio("Episodio 1", 1, 3);
		serie.addEpisodio("Episodio 2", 2, 3);
		assertEquals(10, serie.getTotalDeEpisodios());
		assertEquals(3, serie.getTotalDeTemporadas());
		assertEquals(3, serie.getTotalDeEpisodiosDaTemporada(1));
		assertEquals(5, serie.getTotalDeEpisodiosDaTemporada(2));
		assertEquals(2, serie.getTotalDeEpisodiosDaTemporada(3));
		assertEquals(0, serie.getTotalDeEpisodiosAssistidos());
		assertEquals(10, serie.getTotalDeEpisodiosAssistir());
		serie.getEpisodios().get(0).mudaStatus();
		serie.getEpisodios().get(1).mudaStatus();
		assertEquals(2, serie.getTotalDeEpisodiosAssistidos());
		assertEquals(8, serie.getTotalDeEpisodiosAssistir());
	}

	@Test
	public void testDeveCompararSeries() {
		Serie serie1 = new Serie("A");
		Serie serie2 = new Serie("B");
		assertTrue(serie1.compareTo(serie2) < 0);
	}
	
	@Test
	public void testDeveRetornarListaDeEpisodiosDaTemporada() {
		Serie serie = new Serie("serie A");
		serie.addEpisodio("Episodio 1", 1, 1);
		serie.addEpisodio("Episodio 2", 2, 1);
		serie.addEpisodio("Episodio 3", 3, 1);
		serie.addEpisodio("Episodio 1", 1, 2);
		serie.addEpisodio("Episodio 2", 2, 2);
		serie.addEpisodio("Episodio 3", 3, 2);
		serie.addEpisodio("Episodio 4", 4, 2);
		serie.addEpisodio("Episodio 5", 5, 2);
		serie.addEpisodio("Episodio 1", 1, 3);
		serie.addEpisodio("Episodio 2", 2, 3);
		assertEquals("Episodio 1", serie.getEpisodios(1).get(0).getNome());
		assertEquals("Episodio 2", serie.getEpisodios(1).get(1).getNome());
		assertEquals("Episodio 3", serie.getEpisodios(1).get(2).getNome());
		assertEquals(1, serie.getEpisodios(1).get(0).getTemporada());
		assertEquals(1, serie.getEpisodios(1).get(1).getTemporada());
		assertEquals(1, serie.getEpisodios(1).get(2).getTemporada());
	}
	
	@Test
	public void testDeveRetornarListaDeTemporadas(){
		Serie serie = new Serie("serie A");
		serie.addEpisodio("Episodio 1", 1, 1);
		serie.addEpisodio("Episodio 2", 2, 1);
		serie.addEpisodio("Episodio 3", 3, 1);
		serie.addEpisodio("Episodio 1", 1, 2);
		serie.addEpisodio("Episodio 2", 2, 2);
		serie.addEpisodio("Episodio 3", 3, 2);
		serie.addEpisodio("Episodio 4", 4, 2);
		serie.addEpisodio("Episodio 5", 5, 2);
		serie.addEpisodio("Episodio 1", 1, 3);
		serie.addEpisodio("Episodio 2", 2, 3);
		assertEquals((Integer) 1, serie.getTemporadas().get(0));
		assertEquals((Integer) 2, serie.getTemporadas().get(1));
		assertEquals((Integer) 3, serie.getTemporadas().get(2));
	}
	
	@Test
	public void testDeveRetornarProximoEpisodio() {
		Serie serie = new Serie("serie A");
		serie.addEpisodio("Episodio 1", 1, 1);
		serie.addEpisodio("Episodio 2", 2, 1);
		serie.addEpisodio("Episodio 3", 3, 1);
		assertEquals("Episodio 1", serie.getProximoEpisodio(1).getNome());
		serie.getEpisodios().get(0).mudaStatus();
		assertTrue(serie.getEpisodios().get(0).isAssistido());
		assertEquals(1, serie.getEpisodios().get(0).getNumero());
		assertEquals(2, serie.getProximoEpisodio(1).getNumero());
		serie.getEpisodios().get(1).mudaStatus();
		assertEquals(3, serie.getProximoEpisodio(1).getNumero());
		serie.getEpisodios().get(2).mudaStatus();
		assertEquals(null, serie.getProximoEpisodio(1));
	}
	
	@Test
	public void testDeveVerificarStatusDasTemporadas() {
		Serie serie = new Serie("serie A");
		serie.addEpisodio("Episodio 1", 1, 1);
		serie.addEpisodio("Episodio 2", 2, 1);
		serie.addEpisodio("Episodio 3", 3, 1);
		serie.addEpisodio("Episodio 1", 1, 2);
		serie.addEpisodio("Episodio 2", 2, 2);
		serie.addEpisodio("Episodio 3", 3, 2);
		serie.addEpisodio("Episodio 4", 4, 2);
		serie.addEpisodio("Episodio 5", 5, 2);
		serie.addEpisodio("Episodio 1", 1, 3);
		serie.addEpisodio("Episodio 2", 2, 3);
		serie.getEpisodios().get(0).mudaStatus();
		serie.getEpisodios().get(8).mudaStatus();
		serie.getEpisodios().get(9).mudaStatus();
		assertTrue(serie.hasTemporadaAssistidaCompleta());
		assertTrue(serie.hasTemporadaAssistidaIncompleta());
		assertTrue(serie.hasTemporadaNaoAssistida());
		assertTrue(serie.isTemporadaAssistidaCompleta(3));
		assertTrue(serie.isTemporadaAssistidaIncompleta(1));
		assertTrue(serie.isTemporadaNaoAssistida(2));
	}
}
