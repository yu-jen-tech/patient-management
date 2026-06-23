package com.pm.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
        log.info("Create billing account request received: {}", billingRequest.toString());

        // Business Logic goes here (e.g., save to DB via Repository)

        // Build the response using the generated Protobuf Builder
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("acc-998877-xyz") // Hardcoded for now
                .setStatus("ACTIVE")
                .build();

        // Send the response over the network
        responseObserver.onNext(response);

        // Close the connection stream
        responseObserver.onCompleted();
    }
}