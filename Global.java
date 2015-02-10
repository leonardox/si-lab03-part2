import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import models.Episodio;
import models.GenericDAO;
import models.Serie;
import play.*;
import play.db.jpa.JPA;


public class Global extends GlobalSettings {

	private static GenericDAO dao = new GenericDAO();

	@Override
	public void onStart(Application app) {
		Logger.info("inicializada...");

		JPA.withTransaction(new play.libs.F.Callback0() {
			
			public void invoke() throws Throwable {
				
				List<Serie> seriesDB = dao.findAllByClassName(Serie.class.getName());
				if(seriesDB.size() == 0) {
					
					CSVReader reader = new CSVReader(new FileReader("seriesFinalFile.csv"));
			        String [] nextLine;
			        List<Serie> series = new ArrayList<Serie>();
			        while ((nextLine = reader.readNext()) != null) {
			        	
			            // nextLine[] is an array of values from the line
			        	Serie serie  = new Serie(nextLine[0]);
			        	int i = series.indexOf(serie);
			        	if(i >= 0) {
			        		dao.persist(series.get(i).addEpisodio(nextLine[3], Integer.parseInt(nextLine[2]), Integer.parseInt(nextLine[1])));
			        	} else {
			        		dao.persist(serie.addEpisodio(nextLine[3], Integer.parseInt(nextLine[2]), Integer.parseInt(nextLine[1])));
			        		series.add(serie);
			        	}

			        }
			        
			        for (int i = 0; i < series.size(); i++) {
						dao.persist(series.get(i));
					}
			         
			        //close reader
			        reader.close();
				}
				
			}
		});
	}
	
	public void onStop(Application app) {
		Logger.info("desligada...");
	}

}
