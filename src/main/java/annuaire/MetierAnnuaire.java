package annuaire;

import com.proto.annuaire.InfoRessource;
import com.proto.annuaire.Personne;

import java.util.HashMap;
import java.util.Iterator;

public class MetierAnnuaire {
    HashMap<Personne, InfoRessource> ressources;

    public MetierAnnuaire() {
        ressources = new HashMap<>();
    }

    public boolean addRessource(Personne personne, InfoRessource infoRessource) {
        if (ressources.containsKey(personne)) {
            return false;
        } else {
            ressources.put(personne, infoRessource);
            return true;
        }
    }

    public boolean delRessource(Personne personne) {
        if (!ressources.containsKey(personne)) {
            return false;
        } else {
            ressources.remove(personne);
            return true;
        }
    }

    public boolean modifyRessource(Personne personne, InfoRessource infoRessource) {
        if (ressources.containsKey(personne)) {
            ressources.replace(personne, infoRessource);
            return true;
        } else {
            return false;
        }
    }

    public InfoRessource getRessource(Personne personne) {
        return ressources.get(personne);
    }

    public Iterator<Personne> listRessources() {
        return ressources.keySet().iterator();
    }
}
