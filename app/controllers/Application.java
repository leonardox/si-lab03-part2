package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	private static GenericDAO dao = new GenericDAO();

	@Transactional
    public static Result index() {
		List<Serie> series = dao.findAllByClassName(Serie.class.getName());
		List<Serie> seriesAssistir = new ArrayList<Serie>(); 
		List<Serie> seriesAssistindo = new ArrayList<Serie>();
		for (int i = 0; i < series.size(); i++) {
			if(series.get(i).isAssistindo()) seriesAssistindo.add(series.get(i));
			else seriesAssistir.add(series.get(i));
		}
		Collections.sort(seriesAssistir);
		Collections.sort(seriesAssistindo);
        return ok(index.render("Minhas Séries", seriesAssistir, seriesAssistindo));
    }
	
	@Transactional
    public static Result mudarStatusDaSerie() {
		
		DynamicForm requestData = Form.form().bindFromRequest();
		Long id = Long.parseLong(requestData.get("id"));
		String tipoProximo = requestData.get("proximo");
        Serie serie = dao.findByEntityId(Serie.class, id);

        if (tipoProximo != null){
            if (tipoProximo.equals("Seguinte")){
                serie.setTipoDoProximo(new Proximo());
            }else{
                if (tipoProximo.equals("Mais Antigo")){
                    serie.setTipoDoProximo(new ProximoMaisAntigo());
                }else{
                    serie.setTipoDoProximo(new ProximoAntigoComMudanca());
                }
            }
        }

		serie.mudaStatus();

        return redirect("/#serie-" + id);
    }
	
	@Transactional
    public static Result mudarStatusDoEpisodio() {
		
		DynamicForm requestData = Form.form().bindFromRequest();
		Long id = Long.parseLong(requestData.get("id"));
		
		Episodio episodio = dao.findByEntityId(Episodio.class, id);
		episodio.mudaStatus();
		Long idSerie = episodio.getSerie().getId();

        return redirect("/#serie-" + idSerie);
    }

}
