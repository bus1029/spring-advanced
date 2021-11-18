package hello.springadvanced.app.v1

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.hellotrace.HelloTraceV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV1 constructor(private val orderService: OrderServiceV1,
                                    private val helloTrace: HelloTraceV1) {
  @GetMapping("/v1/request")
  fun request(itemId: String): String {
    var status: TraceStatus? = null
    try {
      status = helloTrace.begin("OrderController.request()")
      orderService.orderItem(itemId)
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