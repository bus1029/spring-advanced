package hello.springadvanced.app.v3

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service

@Service
class OrderServiceV3 constructor(private val orderRepository: OrderRepositoryV3,
                                 private val trace: LogTrace) {

  fun orderItem(itemId: String) {
    var status: TraceStatus? = null
    try {
      status = trace.begin("OrderServiceV1.orderItem()")
      orderRepository.save(itemId)
      trace.end(status)
    } catch (e: Exception) {
      status?.let {
        trace.exception(status, e)
      }
      throw e
    }
  }
}