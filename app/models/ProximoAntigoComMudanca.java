package models;

import java.util.List;

/**
 * Created by X on 11/02/2015.
 */
public class ProximoAntigoComMudanca extends ProximoMaisAntigo {

    private final int NUMBER_VIEW = 3;

    @Override
    public Episodio getProximoEpisodio(Serie serie) {

        Episodio proximo = super.getProximoEpisodio(serie);

        if (getAssistidosAposProximo(serie, proximo) < NUMBER_VIEW){
            return proximo;
        }else{
            proximo = getNovoProximo(serie);
        }
        return proximo;
    }

    private Episodio getNovoProximo(Serie serie){
        Episodio novoProximo = null;
        for (int k = 1; k <= serie.getTotalDeTemporadas(); k ++){
            if (serie.isTemporadaAssistidaIncompleta(k)){
                List<Episodio> eps = serie.getEpisodios(k);
                int z = 0;
                int index = -1;
                while (z < eps.size()) {
                    if(eps.get(z).isAssistido()) {
                        index = z;
                    }
                    z++;
                }
                if(index == z-1){
                    novoProximo = null;
                }
                if(index == -1){
                    novoProximo = eps.get(0);
                }
                novoProximo = eps.get(index + 1);
            }
        }
        return novoProximo;
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
