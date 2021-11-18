package hello.springadvanced.app.v1

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Service

@Service
class OrderServiceV1 constructor(private val orderRepository: OrderRepositoryV1,
                                 private val helloTrace: HelloTraceV1) {

  fun orderItem(itemId: String) {
    var status: TraceStatus? = null
    try {
      status = helloTrace.begin("OrderServiceV1.orderItem()")
      orderRepository.save(itemId)
      helloTrace.end(status)
    } catch (e: Exception) {
      status?.let {
        helloTrace.exception(status, e)
      }
      throw e
    }
  }
}