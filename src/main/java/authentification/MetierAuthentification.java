package authentification;

import com.proto.authentification.Utilisateur;

public class MetierAuthentification {

    ListeAuth ressources;

    public MetierAuthentification(ListeAuth la) {
        ressources = la;
    }

    public boolean testerRessource(Utilisateur utilisateur) {
        return ressources.tester(utilisateur.getUser(), utilisateur.getPassword());
    }
    public boolean addRessource(Utilisateur utilisateur) {
        return ressources.creer(utilisateur.getUser(), utilisateur.getPassword());
    }

    public boolean delRessource(Utilisateur utilisateur) {
        return ressources.supprimer(utilisateur.getUser(), utilisateur.getPassword());
    }

    public boolean modifyRessource(Utilisateur utilisateur) {
        return ressources.mettreAJour(utilisateur.getUser(), utilisateur.getPassword());
    }
}
