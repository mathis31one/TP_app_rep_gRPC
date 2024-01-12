package authentification;

import com.proto.authentification.GestionAuthentificationServiceGrpc;
import com.proto.authentification.*;
import io.grpc.stub.StreamObserver;

public class GestionAuthentificationServiceImpl extends GestionAuthentificationServiceGrpc.GestionAuthentificationServiceImplBase{
    private final MetierAuthentification ma;

    public GestionAuthentificationServiceImpl(MetierAuthentification ma) {
        this.ma = ma;
    }

    @Override
    public void testerRessource(AURequest request, StreamObserver<AUDResponse> responseObserver) {
        if(ma.testerRessource(request.getRessource())){
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DONE).build());
        } else {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DENY_DUPLICATED).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void addRessource(AURequest request, StreamObserver<AUDResponse> responseObserver) {
        if(ma.addRessource(request.getRessource())){
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DONE).build());
        } else {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DENY_DUPLICATED).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void delRessource(DGRequest request, StreamObserver<AUDResponse> responseObserver) {
        if (ma.delRessource(request.getRessource())) {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DONE).build());
        } else {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DENY_NOT_FOUND).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void modifyRessource(AURequest request, StreamObserver<AUDResponse> responseObserver) {
        if (ma.modifyRessource(request.getRessource())) {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DONE).build());
        } else {
            responseObserver.onNext(AUDResponse.newBuilder().setCr(TypeCR.DENY_NOT_FOUND).build());
        }
        responseObserver.onCompleted();
    }
}
