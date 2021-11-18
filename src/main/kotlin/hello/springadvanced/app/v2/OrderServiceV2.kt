package hello.springadvanced.app.v2

import hello.springadvanced.trace.TraceId
import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Service

@Service
class OrderServiceV2 constructor(private val orderRepository: OrderRepositoryV2,
                                 private val helloTrace: HelloTraceV2) {

  fun orderItem(itemId1: TraceId, itemId: String) {
    var status: TraceStatus? = null
    try {
      status = helloTrace.beginSync(itemId1, "OrderServiceV1.orderItem()")
      orderRepository.save(status?.traceId, itemId)
      helloTrace.end(status)
    } catch (e: Exception) {
      status?.let {
        helloTrace.exception(status, e)
      }
      throw e
    }
  }
}