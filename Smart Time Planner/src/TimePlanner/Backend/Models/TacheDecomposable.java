package TimePlanner.Backend.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TacheDecomposable extends Tache {

    private List<Tache> sousTaches;

    public TacheDecomposable(String titre, String description, String priorite, LocalDate startDate, LocalDate endDate, String categorie, int dureeheur, int dureemin) {
        super(titre, description, priorite, startDate, endDate, categorie, dureeheur, dureemin);
        this.sousTaches = new ArrayList<>();
    }

    public List<Tache> getSousTaches() {
        return this.sousTaches;
    }

    public void setSousTaches(List<Tache> sousTaches) {
        this.sousTaches = sousTaches;
    }

    public void ajouterSousTache(Tache sousTache) {
        this.sousTaches.add(sousTache);
    }

    public void retirerSousTache(Tache sousTache) {
        this.sousTaches.remove(sousTache);
    }
}
