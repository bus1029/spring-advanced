package hello.springadvanced.app.v3

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV3 constructor(private val orderService: OrderServiceV3,
                                    private val trace: LogTrace) {
  @GetMapping("/v3/request")
  fun request(itemId: String): String {
    var status: TraceStatus? = null
    try {
      status = trace.begin("OrderController.request()")
      orderService.orderItem(itemId)
      trace.end(status)
      return "ok"
    } catch (e: Exception) {
      status?.let {
        trace.exception(status, e)
      }
      throw e
    }
  }
}