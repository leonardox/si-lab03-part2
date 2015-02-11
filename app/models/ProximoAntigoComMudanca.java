package models;

import java.util.List;

/**
 * Created by X on 11/02/2015.
 */
public class ProximoAntigoComMudanca extends ProximoMaisAntigo {

    @Override
    public Episodio getProximoEpisodio(Serie serie) {

        Episodio proximo = super.getProximoEpisodio(serie);

        if (getAssistidosAposProximo(serie, proximo) < 3){
            return proximo;
        }else{
            proximo = getNovoProximo(serie);
        }
        return proximo;
    }

    private Episodio getNovoProximo(Serie serie){
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

    private int getAssistidosAposProximo(Serie serie, Episodio proximo){

        int assistidosAposProximo = 0;

        for (int i = proximo.getTemporada(); i <= serie.getTotalDeTemporadas(); i++){
            List<Episodio> lista = serie.getEpisodios(i);
            int k = 0;

            while (k < lista.size()){
                Episodio ep = lista.get(k);
                if (ep.isAssistido()){
                    if (ep.getTemporada() > proximo.getTemporada()){
                        assistidosAposProximo++;
                    }else{
                        if (ep.getNumero() > proximo.getNumero()){
                            assistidosAposProximo++;
                        }
                    }
                }
                k++;
            }
        }
        return assistidosAposProximo;
    }
}
