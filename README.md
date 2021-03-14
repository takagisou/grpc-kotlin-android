
grpc-kotlin-android sample.

# Server
https://github.com/takagisou/helloworld-tonic

# Usage

- Run [Server](https://github.com/takagisou/helloworld-tonic)

- Install [ngrok](https://ngrok.com/)
- Run `ngrok tcp 50051` on local machine.
- copy [proto files](https://github.com/takagisou/HelloProto/tree/master/proto) to `src/main/proto`.
- set ngork host and port [here](https://github.com/takagisou/grpc_kotlin_sample/blob/master/app/src/main/java/com/takagisou/grpc_kotlin_sample/domain/datasource/ClientDataSource.kt#L22-L23).
- Run app.

# Issue

Host `10.0.2.2` is not working.

```
io.grpc.StatusException: UNIMPLEMENTED
```

Similar error occured on [grpc-kotlin](https://grpc.io/docs/languages/kotlin/quickstart/).

```sh
$ ./client/build/install/client/bin/hello-world-client

Exception in thread "main" io.grpc.StatusException: UNIMPLEMENTED
	at io.grpc.Status.asException(Status.java:549)
	at io.grpc.kotlin.ClientCalls$rpcImpl$1$1$1.onClose(ClientCalls.kt:295)
	at io.grpc.internal.ClientCallImpl.closeObserver(ClientCallImpl.java:413)
	at io.grpc.internal.ClientCallImpl.access$500(ClientCallImpl.java:66)
	at io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl$1StreamClosed.runInternal(ClientCallImpl.java:742)
	at io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl$1StreamClosed.runInContext(ClientCallImpl.java:721)
	at io.grpc.internal.ContextRunnable.run(ContextRunnable.java:37)
	at io.grpc.internal.SerializingExecutor.run(SerializingExecutor.java:123)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:835)
$
```

I dont't know why the error occurs.

# UI

<kbd>
<img src="./static/image.png" width=320 alt="ss">
</kbd>



# cf.

https://github.com/grpc/grpc-kotlin

https://at-sushi.work/blog/12

https://mizunashi-mana.github.io/blog/posts/2020/04/kotlin-android-grpc/

https://stackoverflow.com/a/59555606