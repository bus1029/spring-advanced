package hello.springadvanced.app.v4

import hello.springadvanced.trace.logtrace.LogTrace
import hello.springadvanced.trace.template.AbstractTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV4 constructor(private val orderService: OrderServiceV4,
                                    private val trace: LogTrace) {
  @GetMapping("/v4/request")
  fun request(itemId: String): String {
    val template = object : AbstractTemplate<String>(trace) {
      override fun call(): String {
        orderService.orderItem(itemId)
        return "ok"
      }
    }
    return template.execute("OrderController.request()")
  }
}