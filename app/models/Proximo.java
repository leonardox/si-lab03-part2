package models;

import java.util.List;

/**
 * Created by X on 11/02/2015.
 */
public class Proximo extends TipoDoProximo {

    @Override
    public Episodio getProximoEpisodio(Serie serie) {
        Episodio proximo = null;
        for (int i = 1; i <= serie.getTotalDeTemporadas(); i ++){
            if (serie.isTemporadaAssistidaIncompleta(i)){
                List<Episodio> eps = serie.getEpisodios(i);
                int j = 0;
                int index = -1;
                while (j < eps.size()) {
                    if(eps.get(j).isAssistido()) {
                        index = j;
                    }
                    j++;
                }
                if(index == j-1) proximo = null;
                if(index == -1) proximo = eps.get(0);
                proximo = eps.get(index + 1);
            }
        }
        return proximo;
    }
}
