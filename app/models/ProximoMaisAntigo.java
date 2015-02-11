package models;

import java.util.List;

/**
 * Created by X on 11/02/2015.
 */
public class ProximoMaisAntigo extends TipoDoProximo{
    @Override
    public Episodio getProximoEpisodio(Serie serie) {
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
                    if(index == -1) return eps.get(0);
                    return eps.get(index + 1);
            }else{
                if (serie.isTemporadaNaoAssistida(i)){
                    List<Episodio> eps = serie.getEpisodios(i);
                    return eps.get(0);
                }
            }
        }
        return super.getProximoEpisodio(serie);
    }
}
