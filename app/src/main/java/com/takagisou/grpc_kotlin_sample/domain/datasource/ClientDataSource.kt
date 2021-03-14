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
}

class ClientDataSource : ClientRepository {
    override fun hello(name: String) = flow {
        val host = "10.0.2.2"
        val port = 50051
        val channel = ManagedChannelBuilder
            .forAddress("10.0.2.2", port)
            //.forAddress(host, port)
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
}