package hello.springadvanced.app.v4

import hello.springadvanced.trace.logtrace.LogTrace
import hello.springadvanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Service

@Service
class OrderServiceV4 constructor(private val orderRepository: OrderRepositoryV4,
                                 private val trace: LogTrace) {

  fun orderItem(itemId: String) {
    val template = object : AbstractTemplate<Unit>(trace) {
      override fun call() {
        orderRepository.save(itemId)
        return
      }
    }
    return template.execute("OrderService.orderItem()")
  }
}