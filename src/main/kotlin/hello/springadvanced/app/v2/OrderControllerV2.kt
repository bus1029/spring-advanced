package hello.springadvanced.app.v2

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.hellotrace.HelloTraceV2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV2 constructor(private val orderService: OrderServiceV2,
                                    private val helloTrace: HelloTraceV2) {
  @GetMapping("/v2/request")
  fun request(itemId: String): String {
    var status: TraceStatus? = null
    try {
      status = helloTrace.begin("OrderController.request()")
      orderService.orderItem(status?.traceId, itemId)
      helloTrace.end(status)
      return "ok"
    } catch (e: Exception) {
      status?.let {
        helloTrace.exception(status, e)
      }
      throw e
    }
  }
}