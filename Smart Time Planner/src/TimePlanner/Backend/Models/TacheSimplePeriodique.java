package TimePlanner.Backend.Models;
import java.io.Serializable;
import java.time.LocalDate;

public class TacheSimplePeriodique extends TacheSimple implements Serializable {
    private int periodicite;

   public TacheSimplePeriodique(String titre, String description, String priorite, LocalDate startDate, LocalDate endDate, String categorie, int dureeheur, int dureemin, int periodicite) {
        super(titre, description, priorite, startDate, endDate, categorie, dureeheur, dureemin);
        this.periodicite = periodicite;
    }

    public int getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }

    // Additional methods specific to TacheSimplePeriodique if needed
}
