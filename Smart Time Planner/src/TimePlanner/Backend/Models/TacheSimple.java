package TimePlanner.Backend.Models;


import java.io.Serializable;
import java.time.LocalDate;


public class TacheSimple extends Tache implements Serializable {
    public TacheSimple(String titre, String description, String priorite, LocalDate startDate, LocalDate endDate, String categorie, int dureeheur, int dureemin) {
        super(titre, description, priorite, startDate, endDate, categorie, dureeheur, dureemin);
    }
}
