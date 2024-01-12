package authentification;

import com.proto.authentification.AURequest;
import com.proto.authentification.GestionAuthentificationServiceGrpc;
import com.proto.authentification.Utilisateur;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class ClientManager {
    public static void main(String[] args) {
        /* Création du channel gRPC qui specifie l'adresse et le port du serveur de reco*/
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 40555)
                .usePlaintext()
                .build();
        /* Creation stub pour invocation synchrone blocante sur le service distant */
        GestionAuthentificationServiceGrpc.GestionAuthentificationServiceBlockingStub RechClient = GestionAuthentificationServiceGrpc.newBlockingStub(channel);

        /* Invocation du service */
        System.out.println("ClientUserManager");
        System.out.println("Client TEST du service Annuaire");
        Scanner sc = new Scanner(System.in);
        boolean fini = false;
        while (!fini) {
            // menu
            System.out.println("+---------------------------------+");
            System.out.println("| 1 - Tester la connection        |");
            System.out.println("| 2 - Ajouter un utilisateur      |");
            System.out.println("| 3 - Supprimer un utilisateur    |");
            System.out.println("| 4 - Modifier un utilisateur     |");
            System.out.println("| 0 - arreter                     |");
            System.out.println("+---------------------------------+");
            // lecture du choix
            int choix = sc.nextInt();
            sc.nextLine(); // saute le retour à la ligne
            // traitement du choix
            switch (choix) {
                case 0 -> {
                    sc.close();
                    fini = true;
                }
                case 1 -> { // Tester la connection
                    System.out.println("Tapez votre identifiant");
                    String user = sc.next();
                    System.out.println("Tapez votre mot de passe");
                    String password = sc.next();
                    Utilisateur utilisateur = Utilisateur.newBuilder().setUser(user).setPassword(password).build();
                    AURequest reqOne = AURequest.newBuilder().setRessource(utilisateur).build();
                    System.out.println("Réponse : ");
                    System.out.println(RechClient.testerRessource(reqOne));
                }

                case 2 -> {
                    // Ajouter un utilisateur
                    System.out.println("Tapez l'identifiant");
                    String user = sc.next();
                    System.out.println("Tapez le mot de passe");
                    String password = sc.next();
                    Utilisateur utilisateur = Utilisateur.newBuilder().setUser(user).setPassword(password).build();
                    AURequest reqOne = AURequest.newBuilder().setRessource(utilisateur).build();
                    System.out.println("Réponse : ");
                    System.out.println(RechClient.addRessource(reqOne));
                }
                case 3 -> { // Supprimer un utilisateur
                    System.out.println("Tapez l'identifiant");
                    String user = sc.next();
                    System.out.println("Tapez le mot de passe");
                    String password = sc.next();
                    Utilisateur utilisateur = Utilisateur.newBuilder().setUser(user).setPassword(password).build();
                    AURequest reqOne = AURequest.newBuilder().setRessource(utilisateur).build();
                    System.out.println("Réponse : ");
                    System.out.println(RechClient.delRessource(reqOne));
                }
                case 4 -> { // Modifier un utilisateur
                    System.out.println("Tapez l'identifiant");
                    String user = sc.next();
                    System.out.println("Tapez le mot de passe");
                    String password = sc.next();
                    Utilisateur utilisateur = Utilisateur.newBuilder().setUser(user).setPassword(password).build();
                    AURequest reqOne = AURequest.newBuilder().setRessource(utilisateur).build();
                    System.out.println("Réponse : ");
                    System.out.println(RechClient.modifyRessource(reqOne));
                }
            }
        }
    }
}
