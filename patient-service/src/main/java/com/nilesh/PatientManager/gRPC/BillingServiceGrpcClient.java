package com.nilesh.PatientManager.gRPC;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceGrpcClient {
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    //localhost:7001/BillingServiceGrpc/CreateBillingAccount
    //aws.grpc:7001/BillingServiceGrpc/CreateBillingAccount
    public BillingServiceGrpcClient(
            @Value("${billing-service.address:localhost}") String serviceAddress,
            @Value("${billing-service.port:7001}") int servicePort
    ){
        log.info("Connecting to Billing Service at {}:{}", serviceAddress, servicePort);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serviceAddress, servicePort)
                .usePlaintext().build();
        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
        log.info("Connected to Billing Service");
    }
    public BillingResponse createBillingAccount(String patientId, String name, String email) {
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();
        log.info("Sending request to Billing Service: {}", request);
        BillingResponse response = blockingStub.createBillingAccount(request);
        log.info("Received response from Billing Service: {}", response);
        return response;
    }
}
