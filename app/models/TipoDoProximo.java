package models;

import javax.persistence.Entity;
import java.io.Serializable;


/**
 * Created by X on 11/02/2015.
 */

public abstract class TipoDoProximo implements Serializable{

    public Episodio getProximoEpisodio(Serie serie) {
        return null;
    }
}
