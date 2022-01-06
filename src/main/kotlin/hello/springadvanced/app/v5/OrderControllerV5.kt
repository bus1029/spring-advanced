package hello.springadvanced.app.v5

import hello.springadvanced.trace.callback.TraceCallback
import hello.springadvanced.trace.callback.TraceTemplate
import hello.springadvanced.trace.logtrace.LogTrace
import hello.springadvanced.trace.template.AbstractTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV5 constructor(
  private val orderService: OrderServiceV5)
{
  private lateinit var traceTemplate: TraceTemplate

  @Autowired
  constructor(orderService: OrderServiceV5, trace: LogTrace) : this(orderService) {
    this.traceTemplate = TraceTemplate(trace)
  }

  @GetMapping("/v5/request")
  fun request(itemId: String): String {
    return traceTemplate.execute("OrderController.request()", object: TraceCallback<String> {
      override fun call(): String {
        orderService.orderItem(itemId)
        return "ok"
      }
    })
  }
}