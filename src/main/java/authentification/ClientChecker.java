package authentification;

import com.proto.authentification.AURequest;
import com.proto.authentification.GestionAuthentificationServiceGrpc;
import com.proto.authentification.Utilisateur;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class ClientChecker {
    public static void main(String[] args) {
        /* Création du channel gRPC qui specifie l'adresse et le port du serveur de reco*/
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 40555)
                .usePlaintext()
                .build();
        /* Creation stub pour invocation synchrone blocante sur le service distant */
        GestionAuthentificationServiceGrpc.GestionAuthentificationServiceBlockingStub RechClient = GestionAuthentificationServiceGrpc.newBlockingStub(channel);

        /* Invocation du service */
        System.out.println("ClientChecker");
        System.out.println("Client TEST du service d'authentification");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Tapez votre identifiant");
                String user = sc.next();
                System.out.println("Tapez votre mot de passe");
                String password = sc.next();
                Utilisateur utilisateur = Utilisateur.newBuilder().setUser(user).setPassword(password).build();
                AURequest reqOne = AURequest.newBuilder().setRessource(utilisateur).build();
                System.out.println("Réponse : ");
                System.out.println(RechClient.testerRessource(reqOne));
            }
        }
    }
}
