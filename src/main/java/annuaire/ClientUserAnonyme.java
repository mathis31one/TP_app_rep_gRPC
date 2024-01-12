package annuaire;

import com.google.protobuf.Empty;
import com.proto.annuaire.DGRequest;
import com.proto.annuaire.GestionRessourcesServiceGrpc;
import com.proto.annuaire.Personne;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;
import java.util.Scanner;

public class ClientUserAnonyme {
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
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Tapez le nom, LIST pour lister ou FIN pour terminer");
                String nom = sc.next();
                if (nom.equals("FIN")) break;
                if (nom.equals("LIST")) {
                    System.out.println("Liste des personnes : ");
                    Empty reqOne = Empty.newBuilder().build();
                    Iterator<Personne> it = RechClient.listRessources(reqOne);
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    continue;
                }
                System.out.println("Tapez le prénom");
                String prenom = sc.next();
                Personne p = Personne.newBuilder().setNom(nom).setPrenom(prenom).build();
                System.out.println("Recherche de la personne : " + p);
                DGRequest reqOne = DGRequest.newBuilder().setRessource(p).build();
                System.out.println("Réponse : ");
                System.out.println(RechClient.getRessource(reqOne));
            }
        }
    }
}
