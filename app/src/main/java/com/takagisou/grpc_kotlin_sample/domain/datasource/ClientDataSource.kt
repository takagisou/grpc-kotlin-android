package com.takagisou.grpc_kotlin_sample.domain.datasource

import com.takagisou.grpc_kotlin_sample.pb.GreeterGrpcKt
import com.takagisou.grpc_kotlin_sample.pb.Helloworld
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ClientRepository {
    fun hello(name: String): Flow<String>
    //fun endpoints(): Flow<List<EndPointEntity>>
}

class ClientDataSource : ClientRepository {
    override fun hello(name: String) = flow {

        // Run `ngrok tcp 50051` on host machine.
        // server: https://github.com/takagisou/helloworld-tonic
        // not working by 10.0.2.2
        // val host = "8.tcp.ngrok.io"
        // val port = 14605
        val host = "10.0.2.2"
        val port = 50051
        val channel = ManagedChannelBuilder
            .forAddress(host, port)
            .usePlaintext()
            .executor(Dispatchers.Default.asExecutor())
            .build()

        val service = GreeterGrpcKt
            .GreeterCoroutineStub(channel)

        val request = Helloworld
            .HelloRequest
            .newBuilder()
            .setName(name)
            .build()

        emit(service.sayHello(request).message)
    }

    // add https://github.com/moul/pb/blob/master/grpcbin/grpcbin.proto to src/main/proto
//    override fun endpoints() = flow {
//        val host = "grpcb.in"
//        val port = 9000
//
//        val channel = ManagedChannelBuilder
//            .forAddress(host, port)
//            .usePlaintext()
//            .executor(Dispatchers.Default.asExecutor())
//            .build()
//
//        val service = GRPCBinGrpcKt
//            .GRPCBinCoroutineStub(channel)
//
//        val request = Grpcbin.EmptyMessage
//            .newBuilder()
//            .build()
//
//        val response = service.index(request)
//
//        emit(response.endpointsList.map { EndPointEntity(
//            path = it.path,
//            description = it.description
//        )})
//    }
//
}