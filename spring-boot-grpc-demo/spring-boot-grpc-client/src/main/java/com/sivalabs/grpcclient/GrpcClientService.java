package com.sivalabs.grpcclient;


import com.sivalabs.grpc.demo.HelloRequest;
import com.sivalabs.grpc.demo.HelloResponse;
import com.sivalabs.grpc.demo.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService  {

    @GrpcClient("local-grpc-server")
    private HelloServiceGrpc.HelloServiceBlockingStub helloServiceStub;

    public HelloResponse receiveGreeting(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        return helloServiceStub.sayHello(request);
    }

}
