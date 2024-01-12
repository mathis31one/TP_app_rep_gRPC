package annuaire;

import com.google.protobuf.Empty;
import com.proto.annuaire.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;
import java.util.Scanner;

public class ClientUserGestionnaire {
    public static void main(String[] args) {
        /* Création du channel gRPC qui specifie l'adresse et le port du serveur de reco*/
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 40555)
                .usePlaintext()
                .build();
        /* Creation stub pour invocation synchrone blocante sur le service distant */
        GestionRessourcesServiceGrpc.GestionRessourcesServiceBlockingStub RechClient = GestionRessourcesServiceGrpc.newBlockingStub(channel);

        /* Invocation du service */
        System.out.println("ClientUserAnonyme");
        System.out.println("Client TEST du service Annuaire");
        Scanner sc = new Scanner(System.in);
        boolean fini = false;
        while (!fini) {
            // menu
            System.out.println("+---------------------------------+");
            System.out.println("| 1 - creer une personne          |");
            System.out.println("| 2 - supprimer une personne      |");
            System.out.println("| 3 - mettre à jour une personne  |");
            System.out.println("| 4 - rechercher une personne     |");
            System.out.println("| 5 - lister toutes les personnes |");
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
                case 1 -> { // création d'une personne
                    System.out.println("Tapez le nom");
                    String nom = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    System.out.println("Tapez le prénom");
                    String prenom = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    System.out.println("Tapez le bureau");
                    String bureau = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    System.out.println("Tapez le numéro de téléphone");
                    String notel = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    System.out.println("Tapez l'adresse mail");
                    String mail = sc.next();
                    Personne p = Personne.newBuilder().setNom(nom).setPrenom(prenom).build();
                    InfoRessource info = InfoRessource.newBuilder().setBureau(bureau).setNotel(notel).setEmail(mail).build();
                    Entree entree = Entree.newBuilder().setIndividu(p).setInfo(info).build();
                    AURequest reqOne = AURequest.newBuilder().setRessource(entree).build();
                    System.out.println("Réponse : ");
                    System.out.println(RechClient.addRessource(reqOne));
                }

                case 2 -> {
                    // suppression d'une personne
                    System.out.println("Tapez le nom");
                    String nom = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    System.out.println("Tapez le prénom");
                    String prenom = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    Personne p = Personne.newBuilder().setNom(nom).setPrenom(prenom).build();
                    DGRequest reqOne = DGRequest.newBuilder().setRessource(p).build();
                    System.out.println("Réponse : ");
                    System.out.println(RechClient.delRessource(reqOne));
                }
                case 3 -> { // mise à jour d'une personne
                    System.out.println("Tapez le nom");
                    String nom = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    System.out.println("Tapez le prénom");
                    String prenom = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    System.out.println("Tapez le bureau");
                    String bureau = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    System.out.println("Tapez le numéro de téléphone");
                    String notel = sc.next();
                    sc.nextLine(); // saute le retour à la ligne
                    System.out.println("Tapez l'adresse mail");
                    String mail = sc.next();
                    Personne p = Personne.newBuilder().setNom(nom).setPrenom(prenom).build();
                    InfoRessource info = InfoRessource.newBuilder().setBureau(bureau).setNotel(notel).setEmail(mail).build();
                    Entree entree = Entree.newBuilder().setIndividu(p).setInfo(info).build();
                    AURequest reqOne = AURequest.newBuilder().setRessource(entree).build();
                    System.out.println("Réponse : ");
                    System.out.println(RechClient.modifyRessource(reqOne));
                }
                case 4 -> { // recherche d'une personne
                    System.out.println("Tapez le nom ou FIN pour terminer");
                    String nom = sc.next();
                    System.out.println("Tapez le prénom");
                    String prenom = sc.next();
                    Personne p = Personne.newBuilder().setNom(nom).setPrenom(prenom).build();
                    System.out.println("Recherche de la personne : " + p);
                    DGRequest reqOne = DGRequest.newBuilder().setRessource(p).build();
                    System.out.println("Réponse : ");
                    System.out.println(RechClient.getRessource(reqOne));
                }
                case 5 -> { // lister toutes les personnes
                    System.out.println("Liste des personnes : ");
                    Empty reqOne = Empty.newBuilder().build();
                    RechClient.listRessources(reqOne).forEachRemaining(System.out::println);
                    // Autre solution :
                    for (Iterator<Personne> it = RechClient.listRessources(reqOne); it.hasNext(); ) {
                        Personne personne = it.next();
                        System.out.println(personne);
                    }
                }
            }
        }
    }
}
