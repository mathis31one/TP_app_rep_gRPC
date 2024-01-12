package annuaire;

import com.google.protobuf.Empty;
import com.proto.annuaire.*;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

public class GestionRessourcesServiceImpl extends GestionRessourcesServiceGrpc.GestionRessourcesServiceImplBase{

    private final MetierAnnuaire metierAnnuaire;

    public GestionRessourcesServiceImpl(MetierAnnuaire metierAnnuaire) {
        this.metierAnnuaire = metierAnnuaire;
    }
    @Override
    public void addRessource(AURequest request, StreamObserver<AUDResponse> responseObserver) {
        if (metierAnnuaire.addRessource(request.getRessource().getIndividu(), request.getRessource().getInfo())) {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DONE).build());
        } else {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DENY_DUPLICATED).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void delRessource(DGRequest request, StreamObserver<AUDResponse> responseObserver) {
        if (metierAnnuaire.delRessource(request.getRessource())) {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DONE).build());
        } else {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DENY_NOT_FOUND).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void modifyRessource(AURequest request, StreamObserver<AUDResponse> responseObserver) {
        if (metierAnnuaire.modifyRessource(request.getRessource().getIndividu(), request.getRessource().getInfo())) {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DONE).build());
        } else {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DENY_NOT_FOUND).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getRessource(DGRequest request, StreamObserver<GResponse> responseObserver) {
        InfoRessource infoRessource = metierAnnuaire.getRessource(request.getRessource());
        if (infoRessource != null) {
            Entree entree = Entree.newBuilder().setIndividu(request.getRessource()).setInfo(infoRessource).build();
            responseObserver.onNext(GResponse.newBuilder().setCr(TypeCR.DONE).setRessource(entree).build());
        } else {
            responseObserver.onNext(GResponse.newBuilder().setCr(TypeCR.DENY_NOT_FOUND).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void listRessources(Empty request, StreamObserver<Personne> responseObserver) {
        for (Iterator<Personne> it = metierAnnuaire.listRessources(); it.hasNext(); ) {
            Personne personne = it.next();
            responseObserver.onNext(personne);
        }
        responseObserver.onCompleted();
    }
}
