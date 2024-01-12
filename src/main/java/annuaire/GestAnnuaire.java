package annuaire;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GestAnnuaire {
    public static void main(String[] args) {
        try {
            System.err.println("Serveur Annuaire RUNNING");
            MetierAnnuaire m = new MetierAnnuaire();
            // Instanciation du servant Gestionnaire
            GestionRessourcesServiceImpl servant =
                    new GestionRessourcesServiceImpl(m);
            // Initialisation du serveur sur le port 40555
            Server server = ServerBuilder.forPort(40555).addService(servant).build().start();
            // Interception Ctrl C et arrêt processus
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (server != null) {
                    server.shutdown();
                }
            }));
            // Boucle infinie
            server.awaitTermination();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }
}
