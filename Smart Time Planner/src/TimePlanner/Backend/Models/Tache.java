package TimePlanner.Backend.Models;

import java.io.Serializable;
import java.time.LocalDate;

public class Tache implements Serializable {
    private String titre;
    private String description;
    private int dureeTache;
    private String priorite;
    private LocalDate startDate;
    private LocalDate endDate; //date limite
    private EtatRealisation etatRealisation;
    private String categorie;
    private int dureeheur;
    private int dureemin;
    // private Creneau creneauTache;

    // private List<Tache> sousTaches;
    public Tache(String titre, String description, String priorite, LocalDate startDate, LocalDate endDate, String categorie, int dureeheur, int dureemin) {
        this.titre = titre;
        this.description = description;
        this.priorite = priorite;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categorie = categorie;
        this.dureeheur = dureeheur;
        this.dureemin = dureemin;
        this.etatRealisation = EtatRealisation.NOT_REALIZED;
        // this.sousTaches = new ArrayList<>();
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * GETTERS
     */

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getDureeTache() {
        return dureeTache;
    }



    public EtatRealisation getEtatRealisation() {
        return etatRealisation;
    }

    public String getPriorite() {
        return priorite;
    }

    public String getCategorie() {
        return categorie;
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * SETTERS
     */

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDureeTache(int dureeTache) {
        this.dureeTache = dureeTache;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setEtatRealisation(EtatRealisation etatRealisation) {
        this.etatRealisation = etatRealisation;
    }




    public void changerEtatRealisation(EtatRealisation nouvelEtat) {
        this.etatRealisation = nouvelEtat;
    }
}
