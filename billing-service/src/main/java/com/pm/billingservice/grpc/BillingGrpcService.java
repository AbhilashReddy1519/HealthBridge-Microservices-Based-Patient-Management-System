package com.pm.billingservice.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

import billing.BillingRequest;
import billing.BillingResponce;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;

@GrpcService
public class BillingGrpcService extends  BillingServiceImplBase {

  private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

  @Override
  public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponce> responseObserver) {
    log.info("createBillingAccount request recieved {} ", billingRequest.toString());

    // Login save to db or calculation
    BillingResponce responce = BillingResponce.newBuilder()
    .setAccountId("12345")
    .setStatus("ACTIVE")
    .build();
    
    responseObserver.onNext(responce);
    responseObserver.onCompleted();
  }
}
