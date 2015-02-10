import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class IntegrationTest {
	
	private Result result;
	Map<String, String> parameters;
	FakeRequest fakeRequest;

    @Test
    public void testDeveIniciarComSeriesCadastradas() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("2 Broke Girls");
                assertThat(browser.pageSource()).contains("South Park");
                assertThat(browser.pageSource()).contains("The Affair");
                assertThat(browser.pageSource()).contains("Wives and Daughters");
                assertThat(browser.pageSource()).contains("Hungry Investors");
            }
        });
    }
    
    @Test
    public void testDeveIniciarSemSeriesAcompanhadas() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Nenhuma série acompanhada ainda");
            }
        });
    }

    @Test
    public void testDeveAdicionarSerieAcompanhada() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
            	
            	parameters = new HashMap<String, String>();
                parameters.put("id", "20");

                fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);

                result = Helpers.callAction(controllers.routes.ref.Application.mudarStatusDaSerie(), fakeRequest);
                assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        		assertThat(redirectLocation(result)).isEqualTo("/#serie-20");

                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).doesNotContain("Nenhuma série acompanhada ainda");
                assertThat(browser.pageSource()).contains("2 Broke Girls");
            }
        });
    }
    
    @Test
    public void testDeveDesacompanharSerieAcompanhada() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
            	
            	parameters = new HashMap<String, String>();
                parameters.put("id", "20");

                fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);

                result = Helpers.callAction(controllers.routes.ref.Application.mudarStatusDaSerie(), fakeRequest);
                assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        		assertThat(redirectLocation(result)).isEqualTo("/#serie-20");

                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).doesNotContain("Nenhuma série acompanhada ainda");
                assertThat(browser.pageSource()).contains("2 Broke Girls");
                
                parameters = new HashMap<String, String>();
                parameters.put("id", "20");

                fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);

                result = Helpers.callAction(controllers.routes.ref.Application.mudarStatusDaSerie(), fakeRequest);
                assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        		assertThat(redirectLocation(result)).isEqualTo("/#serie-20");

                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Nenhuma série acompanhada ainda");
            }
        });
    }
    
    @Test
    public void testDeveMarcarEpisodioComoAssistidoNaoAssistido() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
            	
            	parameters = new HashMap<String, String>();
                parameters.put("id", "20");

                fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);

                result = Helpers.callAction(controllers.routes.ref.Application.mudarStatusDaSerie(), fakeRequest);
                assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        		assertThat(redirectLocation(result)).isEqualTo("/#serie-20");

                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).doesNotContain("Nenhuma série acompanhada ainda");
                assertThat(browser.pageSource()).contains("2 Broke Girls");
                assertThat(browser.pageSource()).contains("Temporadas Não Assistidas");
                assertThat(browser.pageSource()).doesNotContain("Temporadas Assistidas Incompletas:");
                
                parameters = new HashMap<String, String>();
                parameters.put("id", "2610");

                fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);

                result = Helpers.callAction(controllers.routes.ref.Application.mudarStatusDoEpisodio(), fakeRequest);
                assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        		assertThat(redirectLocation(result)).isEqualTo("/#serie-20");

                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("And the Break-up Scene");
                assertThat(browser.pageSource()).contains("Temporadas Assistidas Incompletas:");
                assertThat(browser.pageSource()).contains("Assistido");
                assertThat(browser.pageSource()).contains("Não Assisti");
                
                parameters = new HashMap<String, String>();
                parameters.put("id", "2610");

                fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);

                result = Helpers.callAction(controllers.routes.ref.Application.mudarStatusDoEpisodio(), fakeRequest);
                assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
        		assertThat(redirectLocation(result)).isEqualTo("/#serie-20");

                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).doesNotContain("Temporadas Assistidas Completas:");
            }
        });
    }
}
