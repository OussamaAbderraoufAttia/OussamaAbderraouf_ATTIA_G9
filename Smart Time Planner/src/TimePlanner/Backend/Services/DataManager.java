package TimePlanner.Backend.Services;

import TimePlanner.Backend.Models.*;

import java.util.ArrayList;

public class DataManager {
    private static DataManager instance;
    private Utilisateur utilisateur;
    private ArrayList<Tache> taches ;

    private DataManager() {
        // Private constructor to prevent instantiation
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    public void setTaches(ArrayList<Tache> taches) {
        this.taches = taches;
    }
}

